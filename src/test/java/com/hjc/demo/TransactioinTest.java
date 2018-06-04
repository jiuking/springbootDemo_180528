package com.hjc.demo;

import com.hjc.demo.service.HelloWorldService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author : Administrator
 * @date : 2018/6/4 0004 11:06
 * @description :
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TransactioinTest {
    @Autowired
    private HelloWorldService helloWorldService;

    @Test
    public void test() {
//        helloWorldService.helloWorld();
        helloWorldService.queryUser(2);
    }
}
