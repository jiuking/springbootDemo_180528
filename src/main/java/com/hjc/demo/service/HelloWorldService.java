package com.hjc.demo.service;

/**
 * @author : Administrator
 * @date : 2018/5/31 0031 15:47
 * @description : HelloWorld Service类
 */
public interface HelloWorldService {

    String helloWorld();

    /**
     * 添加用户
     * @return 返回添加状态
     */
    int addUser();

    /**
     * 查询用户
     */
    void queryUser(Integer integer);
}
