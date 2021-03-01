package com.wzd.ucenterservice.controller;


import com.wzd.commonutils.R;
import com.wzd.ucenterservice.entity.UcenterMember;
import com.wzd.ucenterservice.service.UcenterMemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author wzd
 * @since 2021-03-01
 */
@CrossOrigin //跨域
@Api(tags = "用户中心")
@RestController
@RequestMapping("/ucenterservice/member")
public class UcenterMemberController {
    @Autowired
    private UcenterMemberService memberService;

    @ApiOperation("登录")
    @PostMapping("login")
    public R loginUser(@RequestBody UcenterMember member) {
        String token = memberService.login(member);
        return R.ok().data("toke",token);
    }
}

