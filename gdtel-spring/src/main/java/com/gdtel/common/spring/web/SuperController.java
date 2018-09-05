package com.gdtel.common.spring.web;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.gdtel.common.base.constants.OrderConstants;
import com.gdtel.common.base.enums.CommonError;
import com.gdtel.common.base.exception.CommonException;
import com.gdtel.common.base.exception.ServiceException;
import com.gdtel.common.base.service.AbstractInit;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author gdxieyue@gmail.com
 * @since 2018-01-14
 */
@Log4j2
public class SuperController<T extends Model> extends AbstractInit {

    @Autowired
    protected HttpServletRequest request;

    @Autowired
    protected IService<T> baseService;

    public SuperController() {
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

    @GetMapping("/list")
    public Page<T> list(Page page, T entity) throws CommonException {
        this.checkList(entity);
        EntityWrapper ew = new EntityWrapper<T>();
        return baseService.selectPage(page, ew);
    }

    @GetMapping("/byid")
    public T selectByPrimaryKey(String id) throws CommonException {
        this.checkSelect(id);
        return this.baseService.selectById(id);
    }

    protected void checkSelect(String id) throws CommonException {
        if (id == null) {
            throw new CommonException(CommonError.COMMON_ERROR);
        }
    }

    @PostMapping("/add")
    public T add(T entity) throws CommonException {
        this.checkAdd(entity);
        baseService.insert(entity);
        return entity;
    }

    public void checkAdd(T entity) throws CommonException {
        if (entity == null) {
            throw new ServiceException(CommonError.ENTIRY_NULL);
        }
    }

    @PostMapping("/del")
    public void delete(String id) throws CommonException {
        this.checkDelete(id);
        this.baseService.deleteById(id);
    }

    public void checkDelete(String id) throws CommonException {
        if (id == null) {
            throw new CommonException(CommonError.ENTIRY_NULL);
        }
    }

    @PostMapping("/upd")
    public void update(T entity) throws CommonException {
        this.checkUpdate(entity);
        this.baseService.updateById(entity);
    }

    public void checkUpdate(T entity) throws CommonException {
        if (entity == null) {
            throw new CommonException(CommonError.ENTIRY_NULL);
        }
    }
}
