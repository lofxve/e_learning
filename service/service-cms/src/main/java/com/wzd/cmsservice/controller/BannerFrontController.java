package com.wzd.cmsservice.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wzd.cmsservice.entity.CrmBanner;
import com.wzd.cmsservice.service.CrmBannerService;
import com.wzd.commonutils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName BannerFrontController
 * @Author lofxve
 * @Date 2021/2/28 18:18
 * @Version 1.0
 */
@RestController
@RequestMapping("/cmsservice/banner")
@Api("网站首页Banner列表")
@CrossOrigin //跨域
public class BannerFrontController {

    @Autowired
    private CrmBannerService bannerService;

    @ApiOperation(value = "获取首页banner")
    @GetMapping("getAllBanner")
    public R index() {
        List<CrmBanner> list = bannerService.getAllBanner();
        return R.ok().data("bannerList", list);
    }
}
