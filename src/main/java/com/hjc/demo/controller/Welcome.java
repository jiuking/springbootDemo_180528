package com.hjc.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class Welcome {

    // ：后面无所谓。还可以@Value("#{configProperties['t1.msgname']}")
    // 参考 https://blog.csdn.net/zengdeqing2012/article/details/50736119
//    没有值则默认必须添加：冒号，及后面的值，例如：test。否则报错。注销@Value之后message的初始值才起作用
    @Value("${welcome.message:test1}")
    private String message = "hello world";

    @RequestMapping("welcome")
    public String welcome(Map<String, Object> model) {
        model.put("message", message);
        return "welcome";
    }
}
