package com.hjc.demo.controller;

import com.hjc.demo.service.HelloWorldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Hjc
 * @Description: helloWorld 基础测试
 * @Date: 15:50 2018/5/31 0031
 */
@RestController
public class HelloWorldController {

    @Autowired
    private HelloWorldService helloWorldService;

    @GetMapping("helloWorld")
    public String helloWorld() {
        return "helloWorld2";
    }

    @RequestMapping("welcome1")
    public String welcome() {
        return "welcome";
    }

    @RequestMapping("hellWorldByService")
    public String hellWorldByService() {
        return helloWorldService.helloWorld();
    }
}
