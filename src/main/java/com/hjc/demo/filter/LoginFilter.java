package com.hjc.demo.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @author : Hjc
 * @date : 2018/6/1 0001 17:33
 * @description : loginFilter 跳转
 */
/*@WebFilter(urlPatterns = {"/login"})*/
public class LoginFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(LoginFilter.class);
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logger.info("login enter ");
        filterChain.doFilter(servletRequest, servletResponse);
        logger.info("login end");
    }

    @Override
    public void destroy() {

    }
}
