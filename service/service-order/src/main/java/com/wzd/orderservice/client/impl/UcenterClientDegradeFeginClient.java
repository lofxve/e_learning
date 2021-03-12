package com.wzd.orderservice.client.impl;

import com.wzd.baseservice.exceptionHandler.BaseException;
import com.wzd.commonutils.vo.order.UcenterMemberOrder;
import com.wzd.orderservice.client.UcenterClient;
import org.springframework.stereotype.Component;

/**
 * @ClassName UcenterClientDegradeFeginClient
 * @Author lofxve
 * @Date 2021/3/6 19:41
 * @Version 1.0
 */
@Component
public class UcenterClientDegradeFeginClient implements UcenterClient {
    @Override
    public UcenterMemberOrder getUserInfoOrder(String id) {
        throw new BaseException(20001, "远程调用失败");
    }
}
