package com.wzd.eduservice.controller;


import com.wzd.commonutils.R;
import com.wzd.eduservice.entity.vo.CourseInfoVo;
import com.wzd.eduservice.service.EduCourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("addCourseInfo")
    @ApiOperation(value = "添加课程信息")
    public R addCourseInfo(@ApiParam(name = "CourseInfoVo", value = "课程基本信息", required = true)
                           @RequestBody CourseInfoVo courseInfoVo) {
        String courseId = courseService.saveCourseInfo(courseInfoVo);
        if(!StringUtils.isEmpty(courseId)){
            return R.ok().data("courseId", courseId);
        }else{
            return R.error().message("保存失败");
        }
    }

}

