package com.gdtel.common.spring.config;

import com.gdtel.common.base.exception.CommonException;
import com.gdtel.common.base.service.AbstractInit;
import com.gdtel.common.base.service.InitService;
import com.gdtel.common.spring.service.SpringContextHolder;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.core.config.Order;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ApplicationContextEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @author gdxieyue@gmail.com
 * @since 2018-01-14
 */
@Log4j2
@Component
@Order(value = 0)
public class OnStartedListener implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        log.info("onApplicationEvent");
        if (((ApplicationContextEvent) event).getApplicationContext().getParent() == null) {
            try {
                this.initSystem();
            } catch (CommonException e) {
                log.error("", e);
            }
        }

    }

    public void initSystem() throws CommonException {
        String beans[] = SpringContextHolder.getBeanNames();
        log.info("bean length:" + beans.length);
        Map<Integer, List<Object>> beanMap = new HashMap<Integer, List<Object>>();
        for (String bean : beans) {
            Object obj = SpringContextHolder.getBean(bean);
            if (obj instanceof InitService) {
                InitService initService = (InitService) obj;
                Integer key = initService.getOrder();
                List<Object> beanList = beanMap.get(key);
                if (beanList == null) {
                    beanList = new ArrayList<Object>();
                    beanMap.put(key, beanList);
                }
                beanList.add(initService);
            } else if (obj instanceof AbstractInit) {
                AbstractInit abstractInit = (AbstractInit) obj;
                Integer key = abstractInit.getOrder();

                List<Object> beanList = beanMap.get(key);
                if (beanList == null) {
                    beanList = new ArrayList<Object>();
                    beanMap.put(key, beanList);
                }
                beanList.add(abstractInit);
            }
        }
        if (beanMap.isEmpty()) {
            return;
        }

        /**
         * 按优先级从大到小执行初始化
         */
        Set<Integer> keySet = beanMap.keySet();
        Integer[] keyArr = keySet.toArray(new Integer[]{});
        Arrays.sort(keyArr);
        for (int i = keyArr.length - 1; i >= 0; i--) {
            Integer IKey = keyArr[i];
            List<Object> beanList = beanMap.get(IKey);
            for (Object obj : beanList) {
                if (obj instanceof InitService) {
                    InitService initService = (InitService) obj;
                    initService.init();
                } else if (obj instanceof AbstractInit) {
                    AbstractInit abstractInit = (AbstractInit) obj;
                    abstractInit.init();
                }
            }
        }
    }

}
