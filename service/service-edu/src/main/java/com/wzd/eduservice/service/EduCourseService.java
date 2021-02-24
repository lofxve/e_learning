package com.wzd.eduservice.service;

import com.wzd.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wzd.eduservice.entity.vo.CourseInfoVo;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author wzd
 * @since 2021-02-24
 */
public interface EduCourseService extends IService<EduCourse> {

    String saveCourseInfo(CourseInfoVo courseInfoVo);
}
