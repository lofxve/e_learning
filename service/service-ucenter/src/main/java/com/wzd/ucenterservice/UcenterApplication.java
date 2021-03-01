package com.wzd.ucenterservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @ClassName UcenterApplication
 * @Author lofxve
 * @Date 2021/3/1 21:34
 * @Version 1.0
 */
@SpringBootApplication
@MapperScan("com.wzd.ucenterservice.mapper")
public class UcenterApplication {
    public static void main(String[] args) {
        SpringApplication.run(UcenterApplication.class, args);
    }
}
