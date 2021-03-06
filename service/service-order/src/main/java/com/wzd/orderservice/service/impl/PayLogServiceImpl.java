package com.wzd.orderservice.service.impl;

import com.wzd.orderservice.entity.PayLog;
import com.wzd.orderservice.mapper.PayLogMapper;
import com.wzd.orderservice.service.PayLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 支付日志表 服务实现类
 * </p>
 *
 * @author wzd
 * @since 2021-03-06
 */
@Service
public class PayLogServiceImpl extends ServiceImpl<PayLogMapper, PayLog> implements PayLogService {

}
