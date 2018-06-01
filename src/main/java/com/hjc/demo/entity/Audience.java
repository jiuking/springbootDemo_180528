package com.hjc.demo.entity;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author : Administrator
 * @date : 2018/6/1 0001 15:49
 * @description : Auditence实体类
 */
@Data
@ConfigurationProperties(prefix = "audience")
@Component
public class Audience {
    private String clientId;
    private String base64Secret;
    private String name;
    private int expiresSecond;
}
