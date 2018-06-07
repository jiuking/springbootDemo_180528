package com.hjc.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author : Administrator
 * @date : 2018/6/7 0007 17:18
 * @description : 展示登录ip的归属地址
 */
@RestController
public class IpController {

    @RequestMapping("getIp")
    public String getIp(HttpServletRequest request) {
        String ip = (String) request.getAttribute("ip");
        return ip;
    }
}
