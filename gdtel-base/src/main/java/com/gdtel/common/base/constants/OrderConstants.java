package com.gdtel.common.base.constants;

import org.springframework.stereotype.Component;

/**
 * @author gdxieyue@gmail.com
 * @since 2018-01-14 01:50
 */
@Component
public class OrderConstants {

    public static final Integer CONFIGURE_ORDER = 90000;
    public static final Integer SERVICE_ORDER = 80000;
    public static final Integer SCHEDULE_ORDER = 50000;
    public static final Integer CONTROLLER_ORDER = 40000;
    public static final Integer INTERCEPT_ORDER = 1000;

    private OrderConstants() {
        //ignore
    }
}