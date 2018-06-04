package com.hjc.demo.service.impl;

import com.hjc.demo.entity.User;
import com.hjc.demo.mapper.UserMapper;
import com.hjc.demo.service.HelloWorldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRES_NEW)
    @Override
    public int addUser() {
        User user = new User();
        user.setUserId(2);
        user.setUserName("张三");
        user.setPhone("13800000");
        user.setPassword("mima");
        userMapper.insert(user);

        int a = 1/0;
        return 1;
    }

//    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
    @Override
    public void queryUser(Integer integer) {
//        ((HelloWorldService)SpringUtil.getBean("helloWorldServiceImpl")).addUser();
        addUser();
        User user = userMapper.selectByPrimaryKey(integer);

        System.out.println(user.getUserName());
    }
}
