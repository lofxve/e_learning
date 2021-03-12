package com.wzd.eduservice.client.impl;

import com.wzd.baseservice.exceptionHandler.BaseException;
import com.wzd.eduservice.client.OrderClient;
import org.springframework.stereotype.Component;

/**
 * @ClassName OrderFile
 * @Author lofxve
 * @Date 2021/3/6 19:21
 * @Version 1.0
 */
@Component
public class OrderFile implements OrderClient {
    @Override
    public boolean isBuyCourse(String memberid, String id) {
        throw new BaseException(20001, "远程调用异常");
    }
}
