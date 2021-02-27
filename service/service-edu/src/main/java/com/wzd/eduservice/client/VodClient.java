package com.wzd.eduservice.client;

import com.wzd.commonutils.R;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @ClassName VodClient
 * @Author lofxve
 * @Date 2021/2/27 17:36
 * @Version 1.0
 */
@FeignClient("service-vod")
@Component
public interface VodClient {
    // 定义调用方法的路径
    // @PathVariable("videoId") 要指定参数名称
    @DeleteMapping("/admin/eduvod/video/deleteVideo/{videoId}")
    public R deleteVideoById(@PathVariable("videoId") String videoId);

    @ApiOperation(value = "删除多个视频")
    @DeleteMapping("/admin/eduvod/video/deleteBatch")
    public R deleteBatch(@ApiParam(name = "videoIdList", value = "云端视频id", required = true)
                         @RequestParam("videoIdList") List<String> videoIdList);
}
