package com.hjc.demo.filter;

import com.hjc.demo.common.Constants;
import com.hjc.demo.entity.Audience;
import com.hjc.demo.util.JwtHelper;
import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.security.auth.login.LoginException;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author : Hjc
 * @date : 2018/6/1 0001 15:28
 * @description : Jwt Filter过滤请求路径
 */
@WebFilter(urlPatterns = {"/*"})
public class JwtFilter implements Filter {

    @Autowired
    private Audience audience;

    private static final Logger logger = LoggerFactory.getLogger(JwtFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    /**
     * 校验登录Token是否存在，合法。
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String authHeader = req.getHeader("authorization");
        if ("OPTIONS".equals(req.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
            filterChain.doFilter(servletRequest,servletResponse);
        }else {
            if (authHeader == null || !authHeader.startsWith("bearer;")) {
                //未登录跳转到login页面。
                response.sendRedirect("/login");
                filterChain.doFilter(servletRequest,servletResponse);
            }
            final String token = authHeader.substring(7);
            try {
                if (audience == null) {
                    BeanFactory factory = WebApplicationContextUtils.getRequiredWebApplicationContext(req.getServletContext());
                    audience = (Audience) factory.getBean("audience");
                }
                final Claims claims = JwtHelper.parseJWT(token, audience.getBase64Secret());
                if (claims == null) {
                    response.sendRedirect("/welcome");
                    return;
                }
                req.setAttribute(Constants.CLAIMS, claims);
            }catch (final Exception e) {
                throw new ServletException();
            }
            filterChain.doFilter(servletRequest,servletResponse);
        }
        logger.info("jwt token filter 过滤");
    }

    @Override
    public void destroy() {

    }
}
