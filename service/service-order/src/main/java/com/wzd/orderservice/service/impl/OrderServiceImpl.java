package com.wzd.orderservice.service.impl;

import com.wzd.orderservice.entity.Order;
import com.wzd.orderservice.mapper.OrderMapper;
import com.wzd.orderservice.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author wzd
 * @since 2021-03-06
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

}
