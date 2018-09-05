package com.gdtel.common.interceptor;

import com.gdtel.common.base.exception.CommonException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by gdxieyue@gmail.com
 * 2017-12-06 14:49
 */
public abstract class AbstractInterceptor implements HandlerInterceptor {

    private Logger logger = LogManager.getLogger(getClass());

    @Value("${user.authUrl}")
    private String login_url;

    @Value("${user.authOpen:false}")
    private Boolean authOpen;

    @Value("${user.loginOpen:false}")
    private Boolean loginOpen;

    public abstract boolean abstractLoginHandle(HttpServletRequest request, HttpServletResponse response, Object arg)
            throws CommonException;

    public abstract boolean abstractMockLogin(HttpServletRequest request, HttpServletResponse response, Object arg)
            throws CommonException;

    public abstract boolean abstractCheckAuth(HttpServletRequest request, HttpServletResponse response, Object arg)
            throws CommonException;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg) throws Exception {
        HttpSession session = request.getSession();
        if (session == null) {
            logger.error("session is null");
        }
        logger.info("sessionId:" + session.getId());
        try {
            if (loginOpen) {
                this.abstractLoginHandle(request, response, arg);
            } else {
                this.abstractMockLogin(request, response, arg);
            }

            if (!authOpen) {
                return true;
            } else {
                return this.abstractCheckAuth(request, response, arg);
            }
        } catch (Exception err) {
            return false;
        }
    }

}
