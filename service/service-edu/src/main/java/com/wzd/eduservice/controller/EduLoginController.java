package com.wzd.eduservice.controller;

import com.wzd.commonutils.R;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName EduLoginController
 * @Author lofxve
 * @Date 2021/2/17 16:35
 * @Version 1.0
 */
@CrossOrigin
@RestController
@Api(tags = "登录管理")
@RequestMapping("/eduservice/user")
public class EduLoginController {
    /**
     * @Author lofxve
     * @Description // 登录
     * @Date 16:37 2021/2/17
     * @Param []
     * @return com.wzd.commonutils.R
     **/
    @PostMapping("login")
    public R login() {
        return R.ok().data("token","admin");
    }
    /**
     * @Author lofxve
     * @Description // 信息
     * @Date 16:37 2021/2/17
     * @Param []
     * @return com.wzd.commonutils.R
     **/
    @GetMapping("info")
    public R info() {
        return R.ok().data("roles","[admin]").data("name","admin").data("avatar","https://e-learning-lofxve.oss-cn-beijing.aliyuncs.com/ff5382f902e7257cddc4461a10c9c46.jpg");
    }
}
