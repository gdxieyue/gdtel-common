package com.gdtel.common.base.exception;

import com.gdtel.common.base.enums.IErrorEnum;
import lombok.Getter;
import lombok.Setter;

/**
 * @author gdxieyue@gmail.com
 * @since 2018-01-13 20:58
 */
public class CommonException extends Exception {

    @Getter
    @Setter
    private int code;

    @Getter
    @Setter
    private String extendMsg;

    @Getter
    private IErrorEnum errorEnum;

    public CommonException(IErrorEnum errorEnum) {
        super(errorEnum.getMessage());
        this.errorEnum = errorEnum;
        this.code = errorEnum.getCode();
    }

    public CommonException(IErrorEnum errorEnum, String extendMsg) {
        super(errorEnum.getMessage());
        this.errorEnum = errorEnum;
        this.code = errorEnum.getCode();
        this.extendMsg = errorEnum.getMessage() + "," + extendMsg;
    }
}
