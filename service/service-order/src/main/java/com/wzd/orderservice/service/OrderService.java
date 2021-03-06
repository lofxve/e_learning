package com.wzd.orderservice.service;

import com.wzd.orderservice.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 订单 服务类
 * </p>
 *
 * @author wzd
 * @since 2021-03-06
 */
public interface OrderService extends IService<Order> {

    String createdOrder(String courseId, String memberIdByJwtToken);
}
