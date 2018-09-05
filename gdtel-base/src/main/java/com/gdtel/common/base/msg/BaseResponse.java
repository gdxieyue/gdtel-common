package com.gdtel.common.base.msg;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class BaseResponse implements Serializable {

    protected static final int SUCCESS = 200;
    protected static final int FAIL = -1;
    protected static final long serialVersionUID = 1L;

    // 错误代码
    protected int code;
    // 错误提示
    protected String msg;

    public BaseResponse(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public BaseResponse() {
    }
}
