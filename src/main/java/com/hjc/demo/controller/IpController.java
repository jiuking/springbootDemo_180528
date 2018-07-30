package com.hjc.demo.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.hjc.demo.util.ClientOSInfoUtil;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : Administrator
 * @date : 2018/6/7 0007 17:18
 * @description : 展示登录ip的归属地址
 */
@RestController
public class IpController {

    @RequestMapping("getIp")
    public Map getIp(HttpServletRequest request) {
        String ip = (String) request.getAttribute("ip");
        String os = request.getHeader("user-agent");
        System.out.println(System.getProperty("os.name"));
//        os = ClientOSInfoUtil.getClientOS(os);
        System.out.println(os);
        HashMap<String, String> map = new HashMap<>();
        map.put("os", os);
        map.put("ip", ip);
        return map;
    }
}
