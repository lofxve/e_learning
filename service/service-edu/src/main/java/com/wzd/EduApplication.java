package com.wzd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @ClassName EduApplication
 * @Author lofxve
 * @Date 2021/1/30 10:10
 * @Version 1.0
 */
@EnableCaching // 激活缓存
@EnableFeignClients // 服务调用
@EnableDiscoveryClient //nacos注册
@SpringBootApplication
public class EduApplication {
    public static void main(String[] args) {
        SpringApplication.run(EduApplication.class, args);
    }
}
