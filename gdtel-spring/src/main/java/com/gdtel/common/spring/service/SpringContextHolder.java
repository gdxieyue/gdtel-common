package com.gdtel.common.spring.service;

import lombok.Getter;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author gdxieyue@gmail.com
 * @since 2018-01-14
 */
@Lazy(false)
@Service("springContextHolder")
public class SpringContextHolder implements ApplicationContextAware {

    @Getter
    private static ApplicationContext applicationContext;

    @Getter
    private static Set<String> beanSet = new HashSet<String>();

    @Getter
    private static String[] beanNames;


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if(SpringContextHolder.applicationContext == null) {
            SpringContextHolder.applicationContext = applicationContext;
        }
        SpringContextHolder.beanNames = applicationContext.getBeanDefinitionNames();
        beanSet.addAll(Arrays.asList(beanNames));
        System.out.println("---------------------------------------------------------------------");
        System.out.println("========ApplicationContext配置成功,在普通类可以通过调用applicationContext对象");
        System.out.println("---------------------------------------------------------------------");
    }

    public static boolean existBean(String name) {
        getApplicationContext();
        return beanSet.contains(name);
    }

    //获取applicationContext
    public static ApplicationContext getApplicationContext() {
        if (applicationContext == null) {
            throw new IllegalStateException("applicaitonContext未注入");
        }
        return applicationContext;
    }

    /**
     * 通过name获取 Bean.
     * @param name
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(String name) {
        return (T) applicationContext.getBean(name);
    }

    //通过class获取Bean.
    public static <T> T getBean(Class<T> clazz){
        return getApplicationContext().getBean(clazz);
    }

    //通过name,以及Clazz返回指定的Bean
    public static <T> T getBean(String name,Class<T> clazz){
        return getApplicationContext().getBean(name, clazz);
    }
}
