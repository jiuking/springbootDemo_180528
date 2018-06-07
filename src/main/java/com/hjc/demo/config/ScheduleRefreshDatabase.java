package com.hjc.demo.config;

import com.hjc.demo.mapper.SchedulerCronConfigMapper;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author : Hjc
 * @date : 2018/6/5 0005 11:18
 * @description : 定时查库，并更新任务执行时间
 */
//@Configuration
//@EnableScheduling
//@Component
public class ScheduleRefreshDatabase {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduleRefreshDatabase.class);

    @Autowired
    @Lazy
    private SchedulerCronConfigMapper repository;

    @Resource(name = "jobDetail")
    private JobDetail jobDetail;

    @Resource(name = "jobTrigger")
    private CronTrigger cronTrigger;

    @Resource(name = "scheduler")
    private Scheduler scheduler;

    /**
     *  每隔5s查库，并根据查询结果决定是否重新设置定时任务
     *  @throws SchedulerException
     */
    @Scheduled(fixedRate = 5000)
    public void scheduleUpdateCronTrigger() throws SchedulerException {
        CronTrigger trigger = (CronTrigger) scheduler.getTrigger(cronTrigger.getKey());
        // 当前Trigger使用的
        String currentCron = trigger.getCronExpression();
        // 从数据库查询出来的
        String searchCron = repository.getSchedulerCronConfigById(1L).getCron();
        LOGGER.info("当前定时任务表达式：{}",currentCron);
        LOGGER.info("新的定时任务表达式：{}",searchCron);
        if (currentCron.equals(searchCron)) {
            // 如果当前使用的cron表达式和从数据库中查询出来的cron表达式一致，则不刷新任务
            LOGGER.info(jobDetail.getKey().getGroup());
            LOGGER.info(jobDetail.getKey().getName());
        } else {
            // 表达式调度构建器
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(searchCron);
            // 按新的cronExpression表达式重新构建trigger
            trigger = (CronTrigger) scheduler.getTrigger(cronTrigger.getKey());
            trigger = trigger.getTriggerBuilder().withIdentity(cronTrigger.getKey())
                    .withSchedule(scheduleBuilder).build();
            // 按新的trigger重新设置job执行
            scheduler.rescheduleJob(cronTrigger.getKey(), trigger);
        }
    }
}
