package com.hjc.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @MapperScan("com.hjc.demo.mapper") 将项目中对应的mapper类的路径加进来就可以了
 *
 * @author Hjc
 * @date 2018-05-31 16:42:50
 */
@SpringBootApplication
@MapperScan("com.hjc.demo.mapper")
public class DemoApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(DemoApplication.class);
	}

	/*@Bean
	public FilterRegistrationBean jwtFilter() {
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		filterRegistrationBean.setFilter(new JwtFilter());
		List<String> urlPatterns = new ArrayList<>();
		urlPatterns.add("/helloWorld");
		//添加需要拦截的url
		filterRegistrationBean.addUrlPatterns(urlPatterns.toArray(new String[urlPatterns.size()]));
		return filterRegistrationBean;
	}*/
}
