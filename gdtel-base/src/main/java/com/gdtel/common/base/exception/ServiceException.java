package com.gdtel.common.base.exception;

import com.gdtel.common.base.enums.IErrorEnum;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by gdxieyue@gmail.com
 * 2017-12-10 21:59
 */
public class ServiceException extends RuntimeException {

    @Getter
    @Setter
    private int code;

    @Getter
    @Setter
    private String extendMsg;

    @Getter
    private IErrorEnum errorEnum;

    public ServiceException(IErrorEnum errorEnum) {
        super(errorEnum.getMessage());
        this.errorEnum = errorEnum;
        this.code = errorEnum.getCode();
    }

    public ServiceException(IErrorEnum errorEnum, String extendMsg) {
        super(errorEnum.getMessage());
        this.errorEnum = errorEnum;
        this.code = errorEnum.getCode();
        this.extendMsg = errorEnum.getMessage() + "," + extendMsg;
    }
}
