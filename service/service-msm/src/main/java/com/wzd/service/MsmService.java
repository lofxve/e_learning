package com.wzd.service;

/**
 * @ClassName MsmService
 * @Author lofxve
 * @Date 2021/3/1 15:56
 * @Version 1.0
 */
public interface MsmService {
    boolean sendMsm(String code, String phone);
}
