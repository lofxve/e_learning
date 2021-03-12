package com.wzd.staservice.controller;


import com.wzd.commonutils.R;
import com.wzd.staservice.service.StatisticsDailyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 前端控制器
 * </p>
 *
 * @author wzd
 * @since 2021-03-12
 */
@Api("统计分析")
@CrossOrigin
@RestController
@RequestMapping("/admin/staservice/daily")
public class StatisticsDailyController {
    @Autowired
    private StatisticsDailyService statisticsDailyService;

    @ApiOperation("统计每一天的注册人数")
    @PostMapping("countRegister/{day}")
    public R countRegister(@PathVariable String day) {
        statisticsDailyService.createStatisticsByDay(day);
        return R.ok();
    }

    @ApiOperation("图表显示")
    @GetMapping("chartShowsDay/{begin}/{end}/{type}")
    public R chartShowsDay(@PathVariable String begin,@PathVariable String end,@PathVariable String type) {
        Map map = statisticsDailyService.chartShowsDay(begin,end,type);
        return R.ok().data(map);
    }
}

