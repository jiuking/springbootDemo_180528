package com.hjc.demo.controller;

import com.hjc.demo.entity.User;
import com.hjc.demo.mapper.UserMapper;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : Hjc
 * @date : 2018/6/5 0005 15:33
 * @description : swagger2实例
 */
@RestController
public class Swagger2DemoController {


    @Autowired
    private UserMapper userMapper;

    @ApiOperation(value = "测试", notes = "根据url的id来获取用户详细信息")
    @ApiImplicitParam(name = "id", value = "用户Id",required=true, dataType = "Integer", paramType = "path")
    @RequestMapping(value = "user/{id}", method = RequestMethod.GET)
    public Object test(@PathVariable(value = "id") Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }
}
