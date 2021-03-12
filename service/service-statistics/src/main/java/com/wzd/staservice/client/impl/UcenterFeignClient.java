package com.wzd.staservice.client.impl;

import com.wzd.baseservice.exceptionHandler.BaseException;
import com.wzd.commonutils.R;
import com.wzd.staservice.client.UcenterClient;
import org.springframework.stereotype.Component;

/**
 * @ClassName UcenterFeignClient
 * @Author lofxve
 * @Date 2021/3/12 13:53
 * @Version 1.0
 */
@Component
public class UcenterFeignClient implements UcenterClient {

    @Override
    public R countRegister(String day) {
        throw new BaseException(20001, "远程调用失败");
    }
}
