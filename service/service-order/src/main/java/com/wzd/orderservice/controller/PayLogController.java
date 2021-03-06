package com.wzd.orderservice.controller;


import com.wzd.commonutils.R;
import com.wzd.orderservice.service.PayLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 支付日志表 前端控制器
 * </p>
 *
 * @author wzd
 * @since 2021-03-06
 */
@Api("支付日志表")
@CrossOrigin
@RestController
@RequestMapping("/orderservice/paylog")
public class PayLogController {
    @Autowired
    private PayLogService payLogService;

    @ApiOperation("生成微信支付二维码")
    @GetMapping("createNative/{orderNo}")
    public R createNative(@PathVariable String orderNo) {
        Map map = payLogService.createNative(orderNo);
        return R.ok().data(map);
    }

    @ApiOperation("查询订单状态")
    @GetMapping("/queryPayStatus/{orderNo}")
    public R getPayStatus(@PathVariable String orderNo) {
        //调用查询接口
        Map<String, String> map = payLogService.queryPayStatus(orderNo);
        if (map == null) {//出错
            return R.error().message("支付出错");
        }
        if (map.get("trade_state").equals("SUCCESS")) {//如果成功
            //更改订单状态
            payLogService.updateOrderStatus(map);
            return R.ok().message("支付成功");
        }
        return R.ok().code(25000).message("支付中");
    }

}

