package com.wzd.staservice.schedul;

import com.wzd.staservice.service.StatisticsDailyService;

import com.wzd.staservice.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;


/**
 * @ClassName SchedulTask
 * @Author lofxve
 * @Date 2021/3/12 15:43
 * @Version 1.0
 */
@Component
public class SchedulTask {
    @Autowired
    private StatisticsDailyService statisticsDailyService;

    /**
     * 每天七点到二十三点每五秒执行一次
     */
//    @Scheduled(cron = "0/5 * * * * ?")
//    public void test() {
//        System.out.println("=============================task=====================");
//    }

    /**
     * 每天凌晨1点执行定时
     */
    @Scheduled(cron = "0 0 1 * * ?")
    public void createStatisticsByDay() {
        //获取上一天的日期
        String day = DateUtil.formatDate(DateUtil.addDays(new Date(), -1));
        statisticsDailyService.createStatisticsByDay(day);
    }
}
