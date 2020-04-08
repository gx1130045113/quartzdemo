package com.example.demo;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName MyScheduler
 * @Description: TODO
 * @Author 有丶优秀的少年
 * @Date 2020/3/24
 * @Version V1.0
 **/
public class MyScheduler {
    public static void main(String[] args) throws SchedulerException, InterruptedException {
        //创建调度器scheduler
        StdSchedulerFactory stdSchedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = stdSchedulerFactory.getScheduler();
        //创建jobdetail实例
        JobDetail jobDetail = JobBuilder.newJob(PrintWordsJob.class)
                .withIdentity("job1", "group1")
                .build();
        //构建trigger实例
        SimpleTrigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "triggerGroup1")
                .startNow()//立即生效
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds(1)
                        .repeatForever())
                .build();//一直执行

        //4、执行
        scheduler.scheduleJob(jobDetail, trigger);
        System.out.println("--------scheduler start ! ------------");
        scheduler.start();

        //睡眠
        TimeUnit.MINUTES.sleep(1);
        scheduler.shutdown();
        System.out.println("--------scheduler shutdown ! ------------");

    }
}
