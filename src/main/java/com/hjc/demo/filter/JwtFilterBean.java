package com.hjc.demo.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * @author : Hjc
 * @date : 2018/6/1 0001 13:39
 * @description : Json Web Token Filter Bean类
 */
public class JwtFilterBean extends GenericFilterBean {

    private static final Logger logger = LoggerFactory.getLogger(JwtFilterBean.class);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logger.info("generic filter bean");
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
