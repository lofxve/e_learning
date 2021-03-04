package com.wzd.eduservice.controller.front;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzd.commonutils.R;
import com.wzd.eduservice.entity.EduCourse;
import com.wzd.eduservice.entity.capter.ChapterVo;
import com.wzd.eduservice.entity.frontvo.CourseQueryVo;
import com.wzd.eduservice.entity.frontvo.CourseWebVo;
import com.wzd.eduservice.service.EduChapterService;
import com.wzd.eduservice.service.EduCourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @ClassName CourseFrontController
 * @Author lofxve
 * @Date 2021/3/4 11:07
 * @Version 1.0
 */
@CrossOrigin //跨域
@RestController
@Api(tags = "前端课程页面")
@RequestMapping("/eduservice/coursefront")
public class CourseFrontController {
    @Autowired
    private EduCourseService courseService;

    @Autowired
    private EduChapterService chapterService;

    @ApiOperation(value = "分页课程列表")
    @PostMapping(value = "courseFrontPageList/{page}/{limit}")
    public R pageList(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,

            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,

            @ApiParam(name = "courseQuery", value = "查询对象", required = false)
            @RequestBody(required = false) CourseQueryVo courseQuery) {
        Page<EduCourse> pageParam = new Page<EduCourse>(page, limit);
        Map<String, Object> map = courseService.pageListWeb(pageParam, courseQuery);
        return R.ok().data(map);
    }

    @ApiOperation(value = "课程详情")
    @PostMapping(value = "getFrontCourseInfo/{courseId}")
    public R pageList(
            @ApiParam(name = "courseId", value = "课程id", required = true)
            @PathVariable String courseId) {
        // 查询课程基本信息
        CourseWebVo courseWebVo = courseService.getBaseCourseInfo(courseId);

        // 查询课程小结和章节
        List<ChapterVo> chapterVideo = chapterService.getChapterVideo(courseId);
        System.out.println("================================");
        System.out.println(chapterVideo.toString());
        return R.ok().data("course", courseWebVo).data("chapterVoList", chapterVideo);
    }
}
