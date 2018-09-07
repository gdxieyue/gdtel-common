package com.gdtel.common.base.service;

import com.gdtel.common.base.exception.CommonException;

/**
 * @author gdxieyue@gmail.com
 * @since 2018-01-13 20:56
 */
public interface InitService {

    int getOrder();

    void init() throws CommonException;
}
