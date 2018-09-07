package com.gdtel.common.base.enums;

/**
 * Created by gdxieyue@gmail.com
 * 2017-12-10 21:22
 */
public enum CommonError implements IErrorEnum {

    UNIQUE_KEY(10001, "主键约束错误"),
    COMMON_ERROR(00001, "通用异常");

    public int code;
    public String message;

    CommonError(int code, String message){
        this.code = code;
        this.message = message;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
