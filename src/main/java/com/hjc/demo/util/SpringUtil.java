package com.hjc.demo.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author : Administrator
 * @date : 2018/6/4 0004 14:38
 * @description : Spring获取bean类
 */
@Component
public class SpringUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringUtil.applicationContext = applicationContext;
    }

    public static <T> T getBean(String beanName) {
        if (applicationContext.containsBean(beanName)) {
            return (T) applicationContext.getBean(beanName);
        }else {
            return null;
        }
    }

    public static <T> Map<String, T> getBeanOfType(Class<T> baseType) {
        return applicationContext.getBeansOfType(baseType);
    }
}
