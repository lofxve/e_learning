package com.wzd.eduservice.controller;


import com.wzd.commonutils.R;
import com.wzd.eduservice.entity.EduChapter;
import com.wzd.eduservice.entity.capter.ChapterVo;
import com.wzd.eduservice.service.EduChapterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.poi.openxml4j.opc.PackagingURIHelper;
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

    @ApiOperation(value = "添加课程章节")
    @PostMapping("addChapter")
    public R addChapter(@ApiParam(name = "EduChapter", value = "章节", required = true)
                        @RequestBody EduChapter chapter) {
        chapterService.save(chapter);
        return R.ok();
    }

    @ApiOperation(value = "根据id获取课程章节")
    @GetMapping("getChapter/{chapterId}")
    public R getChapter(@ApiParam(name = "chapterId", value = "章节id", required = true)
                        @PathVariable String chapterId) {
        EduChapter chapter = chapterService.getById(chapterId);
        return R.ok().data("items", chapter);
    }

    @ApiOperation(value = "修改课程章节")
    @PostMapping("updateChapter")
    public R updateChapter(@ApiParam(name = "courseId", value = "课程id", required = true)
                           @RequestBody EduChapter chapter) {
        chapterService.updateById(chapter);
        return R.ok();
    }

    @ApiOperation(value = "删除课程章节")
    @DeleteMapping("deleteChapter/{chapterId}")
    public R deleteChapter(@ApiParam(name = "chapterId", value = "章节id", required = true)
                           @PathVariable String chapterId) {
        boolean flag = chapterService.deleteChapter(chapterId);
        if (flag) {
            return R.ok();
        } else {
            return R.error();
        }
    }
}

