package com.hjc.demo.filter;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @author : Administrator
 * @date : 2018/6/1 0001 14:30
 * @description : Jwt filter
 */
/*@WebFilter(urlPatterns = {"/welcome"})
@Order(3)*/
public class ThirdDemoFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(ThirdDemoFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("third filter");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logger.info("third doFilter");
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {
        logger.info("third destroy");
    }
}
