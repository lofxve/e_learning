package com.wzd;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @ClassName OrderApplication
 * @Author lofxve
 * @Date 2021/3/6 10:14
 * @Version 1.0
 */
@EnableSwagger2
@EnableFeignClients // 远程调用
@EnableDiscoveryClient // 服务注册
@SpringBootApplication
@MapperScan("com.wzd.orderservice.mapper")
public class OrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }
}
