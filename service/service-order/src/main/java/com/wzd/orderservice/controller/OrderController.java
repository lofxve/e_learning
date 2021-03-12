package com.wzd.orderservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wzd.baseservice.exceptionHandler.BaseException;
import com.wzd.commonutils.JwtUtils;
import com.wzd.commonutils.R;
import com.wzd.orderservice.entity.Order;
import com.wzd.orderservice.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 订单 前端控制器
 * </p>
 *
 * @author wzd
 * @since 2021-03-06
 */
@CrossOrigin
@RestController
@Api("订单前端控制器")
@RequestMapping("/orderservice/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @ApiOperation("生成订单")
    @PostMapping("createOrter/{courseId}")
    public R createOrter(@PathVariable String courseId, HttpServletRequest httpServletRequest) {
        String memberIdByJwtToken = JwtUtils.getMemberIdByJwtToken(httpServletRequest);
        if (memberIdByJwtToken == null) {
            throw new BaseException(20001, "请先登录");
        }
        String orderNo = orderService.createdOrder(courseId,memberIdByJwtToken);
        return R.ok().data("orderNo", orderNo);
    }

    @ApiOperation("根据订单id查询订单信息")
    @PostMapping("getOrderById/{orderNo}")
    public R getOrderById(@PathVariable String orderNo) {
        QueryWrapper<Order> orderQueryWrapper = new QueryWrapper<>();
        orderQueryWrapper.eq("order_no", orderNo);
        Order order = orderService.getOne(orderQueryWrapper);
        return R.ok().data("item", order);
    }

    @GetMapping("isBuyCourse/{memberid}/{id}")
    public boolean isBuyCourse(@PathVariable String memberid,
                               @PathVariable String id) {
        //订单状态是1表示支付成功
        int count = orderService.count(new QueryWrapper<Order>().eq("member_id", memberid).eq("course_id", id).eq("status", 1));
        if(count>0) {
            return true;
        } else {
            return false;
        }
    }
}

