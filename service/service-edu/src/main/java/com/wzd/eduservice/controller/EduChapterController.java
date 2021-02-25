package com.wzd.eduservice.controller;


import com.wzd.commonutils.R;
import com.wzd.eduservice.entity.capter.ChapterVo;
import com.wzd.eduservice.service.EduChapterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程章节
 * </p>
 *
 * @author wzd
 * @since 2021-02-24
 */
@CrossOrigin //跨域
@RestController
@Api(tags = "课程章节")
@RequestMapping("/admin/eduservice/chapter")
public class EduChapterController {
    @Autowired
    private EduChapterService chapterService;

    @ApiOperation(value = "获取课程章节小结")
    @GetMapping("getChapterVideo/{courseId}")
    public R getChapterVideo(@ApiParam(name = "courseId", value = "课程id", required = true)
                             @PathVariable String courseId) {
        List<ChapterVo> chapterVoList = chapterService.getChapterVideo(courseId);
        return R.ok().data("items", chapterVoList);
    }
}

