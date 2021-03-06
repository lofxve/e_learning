package com.wzd.ucenterservice.controller;


import com.wzd.baseservice.exceptionHandler.BaseException;
import com.wzd.commonutils.JwtUtils;
import com.wzd.commonutils.R;
import com.wzd.commonutils.vo.commentvo.UcenterMemberVo;
import com.wzd.commonutils.vo.order.UcenterMemberOrder;
import com.wzd.ucenterservice.entity.UcenterMember;
import com.wzd.ucenterservice.entity.vo.RegisterVo;
import com.wzd.ucenterservice.service.UcenterMemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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
        return R.ok().data("token", token);
    }

    @ApiOperation(value = "会员注册")
    @PostMapping("register")
    public R register(@RequestBody RegisterVo registerVo) {
        memberService.register(registerVo);
        return R.ok();
    }

    @ApiOperation(value = "根据token获取登录信息")
    @GetMapping("getLoginInfo")
    public R getLoginInfo(HttpServletRequest request) {
        try {
            String memberId = JwtUtils.getMemberIdByJwtToken(request);
            UcenterMember loginInfoVo = memberService.getById(memberId);
            return R.ok().data("item", loginInfoVo);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BaseException(20001, "error");
        }
    }
    @ApiOperation(value = "根据token字符串获取用户信息")
    @PostMapping("getInfoUc/{id}")
    public UcenterMemberVo getInfoUc(@PathVariable String id) {
        //根据用户id获取用户信息
        UcenterMember ucenterMember = memberService.getById(id);
        UcenterMemberVo memberVo = new UcenterMemberVo();
        BeanUtils.copyProperties(ucenterMember,memberVo);
        return memberVo;
    }

    @ApiOperation(value = "Order根据token字符串获取用户信息")
    @PostMapping("getUserInfoOrder/{id}")
    public UcenterMemberOrder getUserInfoOrder(@PathVariable String id) {
        //根据用户id获取用户信息
        UcenterMember ucenterMember = memberService.getById(id);
        UcenterMemberOrder memberVo = new UcenterMemberOrder();
        BeanUtils.copyProperties(ucenterMember,memberVo);
        return memberVo;
    }
}

