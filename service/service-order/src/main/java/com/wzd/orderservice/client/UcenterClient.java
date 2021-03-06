package com.wzd.orderservice.client;

import com.wzd.commonutils.vo.order.UcenterMemberOrder;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @ClassName UcenterClient
 * @Author lofxve
 * @Date 2021/3/6 14:33
 * @Version 1.0
 */
@FeignClient("service-ucenter")
@Component
public interface UcenterClient {
    @ApiOperation(value = "Order根据token字符串获取用户信息")
    @PostMapping("/ucenterservice/member/getUserInfoOrder/{id}")
    public UcenterMemberOrder getUserInfoOrder(@PathVariable("id") String id);
}
