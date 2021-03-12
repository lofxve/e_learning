package com.wzd;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @ClassName StaApplication
 * @Author lofxve
 * @Date 2021/3/12 11:19
 * @Version 1.0
 */
@EnableScheduling // 定时任务
@EnableSwagger2
@SpringBootApplication
@MapperScan("com.wzd.staservice.mapper")
@EnableDiscoveryClient  // 服务注册
@EnableFeignClients // 服务发现
public class StaApplication {
    public static void main(String[] args) {
        SpringApplication.run(StaApplication.class, args);
    }
}
