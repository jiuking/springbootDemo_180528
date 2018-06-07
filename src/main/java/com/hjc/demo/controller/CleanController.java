package com.hjc.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author : Administrator
 * @date : 2018/6/7 0007 16:34
 * @description : 整洁的页面Controller
 */
@Controller
public class CleanController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CleanController.class);

    @RequestMapping("index")
    public String index() {
        LOGGER.info("index");
        return "index";
    }
}
