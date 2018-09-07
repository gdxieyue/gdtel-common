package com.gdtel.common.spring.service.impl;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.service.IService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.gdtel.common.base.constants.OrderConstants;
import com.gdtel.common.base.exception.CommonException;
import com.gdtel.common.base.service.InitService;
import lombok.extern.log4j.Log4j2;

/**
 * @author gdxieyue@gmail.com
 * @since 2018-01-14
 */
@Log4j2
public class BaseServiceImpl<M extends BaseMapper<T>, T> extends ServiceImpl<M, T> implements IService<T>, InitService {

    @Override
    public int getOrder() {
        return OrderConstants.SERVICE_ORDER;
    }

    @Override
    public void init() throws CommonException {
        this.initDictService();
        this.initConfigureService();
    }

    public void initDictService() throws CommonException {
        log.info("BaseServiceImpl:----initDictService");
    }

    public void initConfigureService() throws CommonException {
        log.info("BaseServiceImpl:----initConfigureService");
    }

}