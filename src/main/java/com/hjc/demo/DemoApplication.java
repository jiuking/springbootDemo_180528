package com.hjc.demo;

import com.hjc.demo.filter.JwtFilter;
import com.hjc.demo.interceptor.URLInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.ImportResource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * @MapperScan("com.hjc.demo.mapper") 将项目中对应的mapper类的路径加进来就可以了
 *
 * @author Hjc
 * @date 2018-05-31 16:42:50
 */
@EnableTransactionManagement
@SpringBootApplication
@EnableSwagger2
//@EnableAspectJAutoProxy(proxyTargetClass = true,exposeProxy = true)//配置代理为cglib代理，默认使用 的是jdk动态代理
@MapperScan("com.hjc.demo.mapper")
@ImportResource("classpath:applicationContext.xml")
public class DemoApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	//看起来可以注释掉，没有影响在tomcat容器中运行，只需要在pom.xml中配成war就可以了
	/*@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(DemoApplication.class);
	}*/

	@Bean
	public Object testBean(PlatformTransactionManager platformTransactionManager){
		System.out.println(">>>>>>>>>>" + platformTransactionManager.getClass().getName());
		return new Object();
	}

	//拦截helloWorld请求路径，Filter配置跳转路径
	@Bean
	public FilterRegistrationBean jwtFilter() {
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		filterRegistrationBean.setFilter(new JwtFilter());
		List<String> urlPatterns = new ArrayList<>();
		urlPatterns.add("/helloWorld");
		//添加需要拦截的urlFilter中配置的
//		拦截所有的url 不仅仅是helloWorld 在Jwt
//		filterRegistrationBean.addUrlPatterns(urlPatterns.toArray(new String[urlPatterns.size()]));
		return filterRegistrationBean;
	}
}
