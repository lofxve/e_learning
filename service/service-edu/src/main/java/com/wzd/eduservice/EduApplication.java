package com.wzd.eduservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @ClassName EduApplication
 * @Author lofxve
 * @Date 2021/1/30 10:10
 * @Version 1.0
 */
@SpringBootApplication
@ComponentScan("com.wzd")
public class EduApplication {
    public static void main(String[] args) {
        SpringApplication.run(EduApplication.class, args);
    }
}
