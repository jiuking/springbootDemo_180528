package com.hjc.demo.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @author : Administrator
 * @date : 2018/6/1 0001 14:51
 * @description : webFilter 路径添加/*
 */
/*@WebFilter(urlPatterns = {"/welcome/*"})
@Order(4)*/
public class FourthFilter implements Filter {
    private final static Logger logger = LoggerFactory.getLogger(FourthFilter.class);
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("fourth filter init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logger.info("fourth doFilter");
        filterChain.doFilter(servletRequest,servletResponse);
        logger.info("fourth doFilter info");
    }

    @Override
    public void destroy() {

    }
}
