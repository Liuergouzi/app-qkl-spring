package com.example.demo.config;

import com.example.demo.service.CronService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;

import java.util.Date;


/**
 * 轮子哥
 * 定时推送应用状态栏通知
 */
@Configuration
@EnableScheduling
public class DynamicScheduleTask implements SchedulingConfigurer {

    private final CronService cronService;
    private final static String EXCHANGE_NAME = "app_msg_push_exchange";
    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public DynamicScheduleTask(CronService cronService, RabbitTemplate rabbitTemplate) {
        this.cronService = cronService;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {

        taskRegistrar.addTriggerTask(
                () -> rabbitTemplate.convertAndSend(EXCHANGE_NAME, "app.msg", cronService.getMessage())
                ,
                this::nextExecutionTime
        );
    }

    private Date nextExecutionTime(TriggerContext triggerContext) {
        String cron = cronService.getTime();
        if (cron == null) {
            System.out.println("错误，定时任务字符串为空");
        }
        return new CronTrigger(cron).nextExecutionTime(triggerContext);
    }
}

