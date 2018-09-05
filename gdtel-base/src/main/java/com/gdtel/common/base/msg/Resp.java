package com.gdtel.common.base.msg;

import lombok.Getter;
import lombok.Setter;

/**
 * @author gdxieyue@gmail.com
 * @date 2018-08-20
 */
@Getter
@Setter
public class Resp<T> extends BaseResponse {
    // 返回对象
    private T data;

    public Resp data(T data) {
        this.setData(data);
        return this;
    }

    protected Resp() {
    }

    protected Resp(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Resp(T data) {
        super();
        this.code = SUCCESS;
        this.data = data;
    }

    public Resp(T data, String msg) {
        super();
        this.code = SUCCESS;
        this.data = data;
        this.msg = msg;
    }

    public Resp(Throwable e) {
        super();
        this.code = FAIL;
        this.msg = e.getMessage();
    }
}
