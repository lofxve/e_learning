package com.wzd.eduservice.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzd.commonutils.R;
import com.wzd.eduservice.entity.EduCourse;
import com.wzd.eduservice.entity.vo.CourseInfoVo;
import com.wzd.eduservice.entity.vo.CoursePublishVo;
import com.wzd.eduservice.entity.vo.CourseQuery;
import com.wzd.eduservice.service.EduCourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程管理
 * </p>
 *
 * @author wzd
 * @since 2021-02-24
 */
@CrossOrigin //跨域
@RestController
@Api(tags = "课程管理")
@RequestMapping("/admin/eduservice/course")
public class EduCourseController {
    @Autowired
    private EduCourseService courseService;

    @ApiOperation(value = "分页课程列表")
    @GetMapping("queryCourseByPage/{page}/{limit}")
    public R pageQuery(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,
            @ApiParam(name = "courseQuery", value = "查询对象", required = false)
             CourseQuery courseQuery) {

        Page<EduCourse> pageParam = new Page<>(page, limit);
        courseService.pageQuery(pageParam, courseQuery);
        List<EduCourse> records = pageParam.getRecords();

        long total = pageParam.getTotal();

        return R.ok().data("total", total).data("rows", records);
    }

    @PostMapping("addCourseInfo")
    @ApiOperation(value = "添加课程信息")
    public R addCourseInfo(@ApiParam(name = "CourseInfoVo", value = "课程基本信息", required = true)
                           @RequestBody CourseInfoVo courseInfoVo) {
        String courseId = courseService.saveCourseInfo(courseInfoVo);
        if (!StringUtils.isEmpty(courseId)) {
            return R.ok().data("courseId", courseId);
        } else {
            return R.error().message("保存失败");
        }
    }

    @GetMapping("getCourseInfo/{courseId}")
    @ApiOperation(value = "根据课程id查询课程信息")
    public R getCourseInfo(@ApiParam(name = "courseId", value = "课程ID", required = true)
                           @PathVariable String courseId) {
        CourseInfoVo course = courseService.getCourseInfoById(courseId);
        return R.ok().data("itmes", course);
    }

    @PostMapping("updateCourseInfo")
    @ApiOperation(value = "修改课程信息")
    public R updateCourseInfo(@ApiParam(name = "CourseInfoVo", value = "课程基本信息", required = true)
                              @RequestBody CourseInfoVo courseInfoVo) {
        courseService.updateCourseInfo(courseInfoVo);
        return R.ok();
    }

    @ApiOperation(value = "根据ID获取课程发布信息")
    @GetMapping("getCoursePublishVoById/{id}")
    public R getCoursePublishVoById(
            @ApiParam(name = "id", value = "课程ID", required = true)
            @PathVariable String id) {
        CoursePublishVo courseInfoForm = courseService.getCoursePublishVoById(id);
        return R.ok().data("item", courseInfoForm);
    }

    @ApiOperation(value = "根据id发布课程")
    @PostMapping("publishCourseById/{id}")
    public R publishCourseById(
            @ApiParam(name = "id", value = "课程ID", required = true)
            @PathVariable String id) {
        boolean b = courseService.publishCourseById(id);
        if (b) {
            return R.ok();
        } else {
            return R.error();
        }
    }
}

