package com.wzd.eduservice.service.impl;

import com.wzd.baseservice.exceptionHandler.BaseException;
import com.wzd.eduservice.entity.EduCourse;
import com.wzd.eduservice.entity.EduCourseDescription;
import com.wzd.eduservice.entity.vo.CourseInfoVo;
import com.wzd.eduservice.mapper.EduCourseDescriptionMapper;
import com.wzd.eduservice.mapper.EduCourseMapper;
import com.wzd.eduservice.service.EduCourseDescriptionService;
import com.wzd.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author wzd
 * @since 2021-02-24
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {
    @Autowired
    private EduCourseDescriptionService courseDescriptionService;

    @Override
    public String saveCourseInfo(CourseInfoVo courseInfoVo) {
        // 添加课程基本信息
        EduCourse course = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo, course);
        boolean save = this.save(course);
        if (!save) {
            throw new BaseException(20001, "添加课程失败");
        }

        // 添加课程简介
        EduCourseDescription courseDescription = new EduCourseDescription();
        courseDescription.setId(course.getId());
        courseDescription.setDescription(courseInfoVo.getDescription());
        boolean save1 = courseDescriptionService.save(courseDescription);
        if (!save1) {
            throw new BaseException(20001, "添加课程简介失败");
        }

        return course.getId();
    }
}
