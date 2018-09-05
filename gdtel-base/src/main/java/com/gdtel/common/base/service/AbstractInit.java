package com.gdtel.common.base.service;

import com.gdtel.common.base.exception.CommonException;

/**
 * @author gdxieyue@gmail.com
 * @since 2018-01-14
 */
public abstract class AbstractInit {

    public abstract int getOrder();

    public abstract void init() throws CommonException;
}
