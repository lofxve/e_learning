package com.wzd.eduservice.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wzd.commonutils.R;
import com.wzd.eduservice.entity.EduCourse;
import com.wzd.eduservice.entity.EduTeacher;
import com.wzd.eduservice.service.EduCourseService;
import com.wzd.eduservice.service.EduTeacherService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName IndexFrontController
 * @Author lofxve
 * @Date 2021/2/28 21:24
 * @Version 1.0
 */
@CrossOrigin //跨域
@RestController
@Api(tags = "前端页面数据")
@RequestMapping("/eduservice/indexfront")
public class IndexFrontController {

    @Autowired
    private EduCourseService courseService;
    @Autowired
    private EduTeacherService teacherService;

    @GetMapping("index")
    public R index() {
        //获取前8条热门课程
        QueryWrapper<EduCourse> courseQueryWrapper = new QueryWrapper<>();
        courseQueryWrapper.orderByDesc("view_count");
        courseQueryWrapper.last("LIMIT 8");
        List<EduCourse> courses = courseService.list(courseQueryWrapper);

        //查询前4条名师
        QueryWrapper<EduTeacher> teacherQueryWrapper = new QueryWrapper<>();
        teacherQueryWrapper.orderByDesc("ID");
        teacherQueryWrapper.last("LIMIT 4");
        List<EduTeacher> teachers = teacherService.list(teacherQueryWrapper);
        return R.ok().data("teachers", teachers).data("courses", courses);
    }
}
