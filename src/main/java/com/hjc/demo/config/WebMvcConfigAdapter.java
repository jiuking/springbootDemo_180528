package com.hjc.demo.config;


import com.hjc.demo.interceptor.URLInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author : Hjc
 * @date : 2018/6/7 0007 14:06
 * @description : 配置Interception类
 *
 * 继承WebMvcConfigrationSupport会让Spring-boot对mvc的自动配置失效的，应该实现WebMvcConfigurer接口
 */
@Configuration
public class WebMvcConfigAdapter implements WebMvcConfigurer {

    /**
     * 把我们的拦截器注入为bean
     */
    @Bean
    public HandlerInterceptor getMyInterceptor(){
        return new URLInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // addPathPatterns 用于添加拦截规则, 这里假设拦截 /url 后面的全部链接
        // excludePathPatterns 用户排除拦截
        registry.addInterceptor(getMyInterceptor()).addPathPatterns("/**");
//        super.addInterceptors(registry);
    }

}
