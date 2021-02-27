package com.wzd.eduservice.controller;


import com.wzd.baseservice.exceptionHandler.BaseException;
import com.wzd.commonutils.R;
import com.wzd.eduservice.client.VodClient;
import com.wzd.eduservice.entity.EduVideo;
import com.wzd.eduservice.service.EduVideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程小结
 * </p>
 *
 * @author wzd
 * @since 2021-02-24
 */
@CrossOrigin //跨域
@RestController
@Api(tags = "课程视频")
@RequestMapping("/admin/eduservice/video")
public class EduVideoController {

    @Autowired
    private EduVideoService videoService;

    @Autowired
    private VodClient vodClient;

    @ApiOperation(value = "添加小结")
    @PostMapping("addVideo")
    public R addVideo(@ApiParam(name = "EduVideo", value = "小结", required = true)
                      @RequestBody EduVideo video) {
        boolean save = videoService.save(video);
        if (save) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    @ApiOperation(value = "修改小结")
    @PostMapping("updateVideo")
    public R updateVideo(@ApiParam(name = "EduVideo", value = "小结", required = true)
                         @RequestBody EduVideo video) {
        boolean update = videoService.updateById(video);
        if (update) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    @ApiOperation(value = "获取小结")
    @GetMapping("getVideo/{videoId}")
    public R getVideo(@ApiParam(name = "videoId", value = "小结ID", required = true)
                      @PathVariable String videoId) {
        EduVideo video = videoService.getById(videoId);
        return R.ok().data("items", video);
    }

    @ApiOperation(value = "删除小结")
    @DeleteMapping("deleteVideo/{videoId}")
    public R deleteVideo(@ApiParam(name = "videoId", value = "小结ID", required = true)
                         @PathVariable String videoId) {
        try {
            EduVideo video = videoService.getById(videoId);
            String videoSourceId = video.getVideoSourceId();
            // 如果存在视频id
            if (!StringUtils.isEmpty(videoSourceId)) {
                // 远程调用
                R r = vodClient.deleteVideoById(videoSourceId);
                if (r.getCode() == 20001) {
                    throw new BaseException(r.getCode(), r.getMessage()+"删除视频失败，服务宕机...");
                }
            }
        } catch (BaseException e) {
            // 视频id允许为空
            e.printStackTrace();
        }

        boolean b = videoService.removeById(videoId);
        if (b) {
            return R.ok();
        } else {
            return R.error();
        }
    }
}

