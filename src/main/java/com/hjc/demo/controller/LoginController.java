package com.hjc.demo.controller;

import com.hjc.demo.entity.Audience;
import com.hjc.demo.util.JwtHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * @author : Administrator
 * @date : 2018/6/1 0001 16:51
 * @description : 登录Controller
 */
@RestController
public class LoginController {

    @Autowired
    private Audience audience;

    @RequestMapping("/login")
    public String login(HttpServletResponse response) {
        String jwtToken = JwtHelper.createJWT("",
                "",
                "",
                audience.getClientId(),
                audience.getName(),
                audience.getExpiresSecond()*1000,
                audience.getBase64Secret());
        String token = "bearer;" + jwtToken;
        response.setHeader("authorization",token);
        return "login";
    }
}
