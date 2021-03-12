package com.wzd.eduservice.client;

import com.wzd.eduservice.client.impl.OrderFile;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @ClassName OrderClient
 * @Author lofxve
 * @Date 2021/3/6 19:20
 * @Version 1.0
 */
@Component
@FeignClient(value = "service-order", fallback = OrderFile.class)
public interface OrderClient {
    //查询订单信息
    @GetMapping("/orderservice/order/isBuyCourse/{memberid}/{id}")
    public boolean isBuyCourse(@PathVariable("memberid") String memberid, @PathVariable("id") String id);
}
