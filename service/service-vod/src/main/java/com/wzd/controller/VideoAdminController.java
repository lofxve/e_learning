package com.wzd.controller;

import com.wzd.commonutils.R;
import com.wzd.service.VideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ClassName VideoAdminController
 * @Author lofxve
 * @Date 2021/2/26 22:11
 * @Version 1.0
 */
@Api("阿里云视频点播微服务")
@CrossOrigin //跨域
@RestController
@RequestMapping("/admin/eduvod/video")
public class VideoAdminController {

    @Autowired
    private VideoService videoService;

    @PostMapping("upload")
    public R uploadVideo(
            @ApiParam(name = "file", value = "文件", required = true)
            @RequestParam("file") MultipartFile file) throws Exception {

        String videoId = videoService.uploadVideo(file);
        return R.ok().message("视频上传成功").data("videoId", videoId);
    }
}
