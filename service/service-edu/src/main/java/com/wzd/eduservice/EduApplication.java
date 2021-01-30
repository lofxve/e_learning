package com.wzd.eduservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @ClassName EduApplication
 * @Author lofxve
 * @Date 2021/1/30 10:10
 * @Version 1.0
 */
@EnableSwagger2
@SpringBootApplication
@ComponentScan("com.wzd")
public class EduApplication {
    public static void main(String[] args) {
        SpringApplication.run(EduApplication.class, args);
    }
}