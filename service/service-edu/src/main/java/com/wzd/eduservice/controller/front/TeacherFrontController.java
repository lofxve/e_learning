package com.wzd.eduservice.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzd.commonutils.R;
import com.wzd.eduservice.entity.EduCourse;
import com.wzd.eduservice.entity.EduTeacher;
import com.wzd.eduservice.service.EduCourseService;
import com.wzd.eduservice.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @ClassName TeacherFrontController
 * @Author lofxve
 * @Date 2021/3/3 15:39
 * @Version 1.0
 */
@CrossOrigin //跨域
@RestController
@Api(tags = "前端老师页面")
@RequestMapping("/eduservice/teacherfront")
public class TeacherFrontController {

    @Autowired
    private EduTeacherService teacherService;

    @Autowired
    private EduCourseService courseService;

    @ApiOperation(value = "分页讲师列表")
    @GetMapping(value = "getTeacherFrontList/{page}/{limit}")
    public R getTeacherFrontList(@ApiParam(name = "page", value = "当前页码", required = true)
                                 @PathVariable Long page,
                                 @ApiParam(name = "limit", value = "每页记录数", required = true)
                                 @PathVariable Long limit) {
        Page<EduTeacher> teacherPage = new Page<>(page, limit);
        Map<String, Object> map = teacherService.pageTeacher(teacherPage);
        return R.ok().data(map);
    }

    @ApiOperation(value = "讲师详情")
    @GetMapping(value = "getTeacherFrontInfo/{teacherId}")
    public R getTeacherFrontInfo(@ApiParam(name = "teacherId", value = "老师id", required = true)
                                 @PathVariable Long teacherId) {
        // 查询讲师基本信息
        EduTeacher teacher = teacherService.getById(teacherId);
        // 查询讲师课程信息
        QueryWrapper<EduCourse> courseQueryWrapper = new QueryWrapper<>();
        courseQueryWrapper.eq("teacher_id", teacherId);
        List<EduCourse> courses = courseService.list(courseQueryWrapper);
        return R.ok().data("teacher", teacher).data("courses", courses);
    }
}
