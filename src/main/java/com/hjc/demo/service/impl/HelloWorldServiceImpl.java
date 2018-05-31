package com.hjc.demo.service.impl;

import com.hjc.demo.entity.User;
import com.hjc.demo.mapper.UserMapper;
import com.hjc.demo.service.HelloWorldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author : Hjc
 * @date : 2018/5/31 0031 15:48
 * @description : HelloWorld Service实现类
 */
@Service
public class HelloWorldServiceImpl implements HelloWorldService{

    @Autowired
    private UserMapper userMapper;

    @Override
    public String helloWorld() {
        User user = userMapper.selectByPrimaryKey(1);
        System.out.println(user.getUserName());
        return user.getUserName();
    }
}
