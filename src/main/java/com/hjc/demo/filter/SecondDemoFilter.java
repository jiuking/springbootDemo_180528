package com.hjc.demo.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @author : Administrator
 * @date : 2018/6/1 0001 14:16
 * @description : Jwt filter 另一个过滤类
 */
/*@WebFilter(urlPatterns = {"/helloWorld"})
@Order(2)*/
public class SecondDemoFilter implements Filter{

    private static final Logger logger = LoggerFactory.getLogger(SecondDemoFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("second init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logger.info("filter second");
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {
        logger.info("second destroy");
    }
}
