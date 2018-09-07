package com.gdtel.common.exception.json;

import com.alibaba.fastjson.JSON;
import com.gdtel.common.base.exception.ServiceException;

public class ResultUtil {
    /**
     * 执行成功
     *
     * @return
     */
    public synchronized static String success() {
        return success(null, null);
    }

    /**
     * 执行成功
     *
     * @return
     */
    public synchronized static String success(Object obj) {
        return success(obj, null);
    }

    /**
     * 转化执行成功, 并把成功的结果以JSON对象传给前端
     *
     * @param obj             转化的对象
     * @param filterPropNames 需要过滤的字段
     */
    public synchronized static String success(Object obj, String[] filterPropNames) {
        return success(obj, true, filterPropNames);
    }

    /**
     * 拼凑结果
     *
     * @param obj
     * @param isRefDetect
     * @param filterPropNames
     * @return
     */
    public synchronized static String success(Object obj, boolean isRefDetect, String[] filterPropNames) {
        ResultBodyInfo<Object> body = new ResultBodyInfo<>(0, "", obj);
        return JSON.toJSONString(body);
    }

    /**
     * 返回错误对象
     *
     * @param code    错误代码
     * @param message 错误信息
     */
    public synchronized static String error(int code, String message) {
        return error(code, message, null);
    }

    public synchronized static String error(ServiceException e) {
        return error(e.getCode(), e.getExtendMsg() == null ? e.getMessage() : e.getMessage() + "," + e.getExtendMsg(), null);
    }

    public synchronized static String error(int code, String message, Object obj) {
        ResultBodyInfo<Object> body = new ResultBodyInfo<>(code, message, obj);
        return JSON.toJSONString(body);
    }
}