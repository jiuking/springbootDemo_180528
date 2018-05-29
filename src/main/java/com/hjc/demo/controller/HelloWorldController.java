package com.hjc.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {


    @GetMapping("helloWorld")
    public String helloWorld() {
        return "helloWorld2";
    }

    @RequestMapping("welcome1")
    public String welcome() {
        return "welcome";
    }
}
