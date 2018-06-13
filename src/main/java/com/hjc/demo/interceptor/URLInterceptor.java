package com.hjc.demo.interceptor;

import com.hjc.demo.common.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.InetAddress;

/**
 * @author : Hjc
 * @date : 2018/6/7 0007 11:46
 * @descrnewIption : URLInterceptor url过滤器，记录登录访问端newIp地址
 */
public class URLInterceptor implements HandlerInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(URLInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String ip = InetAddress.getLocalHost().getHostAddress();
        String newIp = request.getHeader("X-Forwarded-For");
        if (newIp == null || newIp.length() == 0 || "unknown".equalsIgnoreCase(newIp)) {
            newIp = request.getHeader("Proxy-Client-IP");
        }
        if (newIp == null || newIp.length() == 0 || "unknown".equalsIgnoreCase(newIp)) {
            newIp = request.getHeader("WL-Proxy-Client-IP");
        }
        if (newIp == null || newIp.length() == 0 || "unknown".equalsIgnoreCase(newIp)) {
            newIp = request.getHeader("HTTP_CLIENT_IP");
        }
        if (newIp == null || newIp.length() == 0 || "unknown".equalsIgnoreCase(newIp)) {
            newIp = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (newIp == null || newIp.length() == 0 || "unknown".equalsIgnoreCase(newIp)) {
            newIp = request.getRemoteAddr();
        }
        LOGGER.info("ip地址{},{}" , ip , newIp);
        if (request.getAttribute(Constants.REQ_ATTR_IP) == null) {
            request.setAttribute(Constants.REQ_ATTR_IP, newIp);
        }
        return true;
    }
}
