package com.hjc.demo.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author : Hjc
 * @date : 2018/6/5 0005 10:45
 * @description : 定时任务
 */
@Configuration
@EnableScheduling
public class ScheduleTask {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduleTask.class);

    public void sayHello() {
        LocalDateTime localDateTime = LocalDateTime.now();
        String now = localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        LOGGER.info("say hello quartz!{}",now);
    }
}
