package com.wzd.cmsservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @ClassName CmsApplication
 * @Author lofxve
 * @Date 2021/2/28 18:07
 * @Version 1.0
 */
@EnableSwagger2
@SpringBootApplication
@MapperScan("com.wzd.cmsservice.mapper")
public class CmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(CmsApplication.class, args);
    }
}
