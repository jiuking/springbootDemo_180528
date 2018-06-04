package com.hjc.TransactionTest;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author : Administrator
 * @date : 2018/6/4 0004 10:19
 * @description : 反射类
 */
public class BookFacadeProxy implements InvocationHandler {

    private Object target;

    /**
     * 绑定委托对象并返回一个代理类
     *
     * @param target
     * @return
     */
    public Object bind(Object target) {
        this.target = target;
        // 取得代理对象
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this); // 要绑定接口(这是一个缺陷，cglib弥补了这一缺陷)
    }

    /**
     * 调用方法
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("invoke");
        Object result;
        Annotation annotation = target.getClass()
                .getDeclaredMethod(method.getName(), method.getParameterTypes())
                .getAnnotation(Transaction.class);
        if (annotation == null) {
            result = method.invoke(target, args);
            return result;
        } else {
            System.out.println("事务开始");
            result = method.invoke(target, args);
            System.out.println("事务结束");
            return result;
        }
    }
}
