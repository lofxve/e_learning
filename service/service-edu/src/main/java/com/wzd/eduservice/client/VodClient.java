package com.wzd.eduservice.client;

import com.wzd.commonutils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
}
