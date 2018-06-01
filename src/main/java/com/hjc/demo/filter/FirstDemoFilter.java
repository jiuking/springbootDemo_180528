package com.hjc.demo.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author : Administrator
 * @date : 2018/6/1 0001 14:03
 * @description : Jwt filter过滤类
 * Order 注释设置过滤路径先后顺序无意义，因设置 urlPatterns的路径，区分过滤顺序
 */
/*@WebFilter(urlPatterns = {"/*"})
@Order(1)*/
public class FirstDemoFilter implements Filter{

    private static final Logger logger = LoggerFactory.getLogger(FirstDemoFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("order first");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String requestURI = req.getRequestURI();
        logger.info("过滤到的请求--->"+requestURI);
        filterChain.doFilter(servletRequest,servletResponse);
//        response.sendRedirect(req.getContextPath() + "/welcome");
    }

    @Override
    public void destroy() {
        logger.info("first destroy");
    }
}
