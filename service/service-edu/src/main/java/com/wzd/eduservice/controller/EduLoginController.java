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
        return R.ok().data("roles","[admin]").data("name","admin").data("avatar","https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fa1.att.hudong.com%2F62%2F02%2F01300542526392139955025309984.jpg&refer=http%3A%2F%2Fa1.att.hudong.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1616143324&t=f62611da32d017a10e04b2e81c2530a9");
    }
}
