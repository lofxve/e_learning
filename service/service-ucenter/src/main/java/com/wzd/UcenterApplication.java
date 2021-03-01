package com.wzd;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @ClassName UcenterApplication
 * @Author lofxve
 * @Date 2021/3/1 21:34
 * @Version 1.0
 */
@EnableSwagger2
@SpringBootApplication
@MapperScan("com.wzd.ucenterservice.mapper")
public class UcenterApplication {
    public static void main(String[] args) {
        SpringApplication.run(UcenterApplication.class, args);
    }
}
