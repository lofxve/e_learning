package com.wzd.orderservice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wzd.commonutils.vo.order.CourseWebOrder;
import com.wzd.commonutils.vo.order.UcenterMemberOrder;
import com.wzd.orderservice.client.EduClient;
import com.wzd.orderservice.client.UcenterClient;
import com.wzd.orderservice.entity.Order;
import com.wzd.orderservice.mapper.OrderMapper;
import com.wzd.orderservice.service.OrderService;
import com.wzd.orderservice.utils.OrderNoUtil;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private EduClient eduClient;

    @Autowired
    private UcenterClient ucenterClient;

    @Override
    public String createdOrder(String courseId, String memberId) {
        // 课程信息
        CourseWebOrder courseInfoById = eduClient.getCourseInfoById(courseId);
        // 用户信息
        UcenterMemberOrder userInfoOrder = ucenterClient.getUserInfoOrder(memberId);
        Order order = new Order();
        order.setOrderNo(OrderNoUtil.getOrderNo());
        order.setOrderNo(OrderNoUtil.getOrderNo());
        order.setCourseId(courseId);
        order.setCourseTitle(courseInfoById.getTitle());
        order.setCourseCover(courseInfoById.getCover());
        order.setTeacherName(courseInfoById.getTeacherName());
        order.setTotalFee(courseInfoById.getPrice());
        order.setMemberId(memberId);
        order.setMobile(userInfoOrder.getMobile());
        order.setNickname(userInfoOrder.getNickname());
        order.setStatus(0); // 订单状态 0未支付 1已经支付
        order.setPayType(1);
        baseMapper.insert(order);
        return order.getOrderNo();
    }
}
