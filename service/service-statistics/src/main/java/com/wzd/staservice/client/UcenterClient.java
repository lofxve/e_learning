package com.wzd.staservice.client;

import com.wzd.commonutils.R;
import com.wzd.staservice.client.impl.UcenterFeignClient;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @ClassName UcenterClient
 * @Author lofxve
 * @Date 2021/3/12 13:49
 * @Version 1.0
 */
@Component
@FeignClient(value = "service-ucenter", fallback = UcenterFeignClient.class)
public interface UcenterClient {

    @ApiOperation(value = "获取某天的注册人数")
    @GetMapping("/ucenterservice/member/countRegister/{day}")
    public R countRegister(@PathVariable("day") String day);
}
