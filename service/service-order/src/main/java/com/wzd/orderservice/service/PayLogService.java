package com.wzd.orderservice.service;

import com.wzd.orderservice.entity.PayLog;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 支付日志表 服务类
 * </p>
 *
 * @author wzd
 * @since 2021-03-06
 */
public interface PayLogService extends IService<PayLog> {

    Map createNative(String orderNo);
}
