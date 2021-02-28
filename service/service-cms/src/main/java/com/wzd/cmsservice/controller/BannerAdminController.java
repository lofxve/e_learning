package com.wzd.cmsservice.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzd.cmsservice.entity.CrmBanner;
import com.wzd.cmsservice.service.CrmBannerService;
import com.wzd.commonutils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 首页banner表 前端控制器
 * </p>
 *
 * @author wzd
 * @since 2021-02-28
 */
@Api("网站首页后台Banner管理")
@RestController
@RequestMapping("/admin/cmsservice/banner")
@CrossOrigin
public class BannerAdminController {

    @Autowired
    private CrmBannerService bannerService;

    @ApiOperation(value = "获取Banner分页列表")
    @GetMapping("{page}/{limit}")
    public R index(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,

            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit) {

        Page<CrmBanner> pageParam = new Page<>(page, limit);
        bannerService.page(pageParam,null);
        return R.ok().data("items", pageParam.getRecords()).data("total", pageParam.getTotal());
    }

    @ApiOperation(value = "获取Banner")
    @GetMapping("get/{id}")
    public R get(@PathVariable String id) {
        CrmBanner banner = bannerService.getById(id);
        return R.ok().data("item", banner);
    }

    @ApiOperation(value = "新增Banner")
    @PostMapping("save")
    public R save(@RequestBody CrmBanner banner) {
        bannerService.save(banner);
        return R.ok();
    }

    @ApiOperation(value = "修改Banner")
    @PutMapping("update")
    public R updateById(@RequestBody CrmBanner banner) {
        bannerService.updateById(banner);
        return R.ok();
    }

    @ApiOperation(value = "删除Banner")
    @DeleteMapping("remove/{id}")
    public R remove(@PathVariable String id) {
        bannerService.removeById(id);
        return R.ok();
    }
}
