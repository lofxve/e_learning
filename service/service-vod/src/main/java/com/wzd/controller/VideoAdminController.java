package com.wzd.controller;

import com.wzd.commonutils.R;
import com.wzd.service.VideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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

    @DeleteMapping("deleteVideo/{videoId}")
    public R removeVideo(@ApiParam(name = "videoId", value = "云端视频id", required = true)
                         @PathVariable String videoId) {

        videoService.removeVideo(videoId);
        return R.ok().message("视频删除成功");
    }

    @ApiOperation(value = "删除多个视频")
    @DeleteMapping("deleteBatch")
    public R deleteBatch(@ApiParam(name = "videoIdList", value = "云端视频id", required = true)
                         @RequestParam("videoIdList") List<String> videoIdList) {
        videoService.deleteBatch(videoIdList);
        return R.ok();
    }
}
