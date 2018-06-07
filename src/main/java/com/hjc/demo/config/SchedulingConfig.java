package com.hjc.demo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * 定时任务配置类
 *
 * @author Hjc
 * @date 2018-06-02 16:59:00
 */
@Configuration
//@EnableScheduling//启动定时任务
public class SchedulingConfig {

    private static final Logger logger = LoggerFactory.getLogger(SchedulingConfig.class);

    @Scheduled(cron = "0/20 * * * * ?")
    public void scheduled() {
        logger.info("----->定时任务20s执行一次<-----");
    }

}
