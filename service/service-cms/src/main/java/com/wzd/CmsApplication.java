package com.wzd;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @ClassName CmsApplication
 * @Author lofxve
 * @Date 2021/2/28 18:07
 * @Version 1.0
 */
@EnableCaching // 激活缓存
@EnableSwagger2
@SpringBootApplication
@MapperScan("com.wzd.cmsservice.mapper")
public class CmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(CmsApplication.class, args);
    }
}
