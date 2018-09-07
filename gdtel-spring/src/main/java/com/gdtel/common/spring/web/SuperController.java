package com.gdtel.common.spring.web;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.gdtel.common.base.constants.OrderConstants;
import com.gdtel.common.base.enums.CommonError;
import com.gdtel.common.base.exception.CommonException;
import com.gdtel.common.base.exception.ServiceException;
import com.gdtel.common.base.service.AbstractInit;
import com.gdtel.common.spring.constants.UrlConstants;
import com.gdtel.common.spring.service.SpringContextHolder;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.ParameterizedType;

/**
 * @author gdxieyue@gmail.com
 * @since 2018-01-14
 */
@Log4j2
public class SuperController<T> extends AbstractInit {

    @Autowired
    public HttpServletRequest request;

    private Class<T> clazz;

    @Autowired
    private IService<T> baseService;

    public SuperController() {
        clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        log.debug("SuperController init");
    }

    @Override
    public int getOrder() {
        return OrderConstants.CONTROLLER_ORDER;
    }

    @Override
    public void init() throws CommonException {
        this.initBaseService();
    }

    public void initBaseService() throws CommonException {
        log.info("SuperController:----initBaseService");
    }

    protected void checkList(T entity) throws ServiceException {
        if (entity == null) {
            throw new ServiceException(CommonError.COMMON_ERROR);
        }
    }

    @GetMapping(UrlConstants.ALL_LIST)
    public Page<T> allList(Page page, T entity) throws CommonException {
        this.checkList(entity);
        EntityWrapper ew = new EntityWrapper<T>();
        return baseService.selectPage(page,ew);
    }
}
