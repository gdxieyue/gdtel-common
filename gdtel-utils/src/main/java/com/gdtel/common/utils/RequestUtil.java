package com.gdtel.common.utils;

import com.google.gson.Gson;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Log4j2
public class RequestUtil {
    private RequestUtil() {
        //do nothing
    }

    public static String getClientIp(HttpServletRequest request) {

        String ip = new String();
        StringBuffer sb = new StringBuffer();
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-Ip");
            sb.append("X-Real-Ip = {}" + ip);
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Forwarded-For");
            sb.append("X-Forwarded-For = {}" + ip);
        }

        if (StringUtils.isNotBlank(ip)) {
            ip = ip.split(",")[0];
            sb.append("ip is " + ip);
        }
        if (sb.length() > 0) {
            log.debug(sb.toString());
        }
        return ip;
    }

    public static void log(HttpServletRequest request) {
        if (request == null) {
            return;
        }

        String url = request.getRequestURL().toString();
        String method = request.getMethod();
        String uri = request.getRequestURI();
        String queryString = request.getQueryString();
        Enumeration<String> attrs = request.getParameterNames();
        Map<String, Object> attrMap = new HashMap<String, Object>();
        while (attrs != null && attrs.hasMoreElements()) {
            String key = attrs.nextElement();
            if ("password".equals(key)) {
                continue;
            }
            String value = request.getParameter(key);

            if (StringUtils.isBlank(value)) {
                request.setAttribute(key, null);
            }

            attrMap.put(key, value);
        }

        log.info("请求开始, 各个参数, url: {}, method: {}, uri: {}, params: {}, attrMap: {}", url, method, uri, queryString, new Gson().toJson(attrMap));
    }
}
