package com.wzd.eduservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wzd.eduservice.entity.EduCourse;
import com.wzd.eduservice.entity.vo.CoursePublishVo;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author wzd
 * @since 2021-02-24
 */
public interface EduCourseMapper extends BaseMapper<EduCourse> {
    CoursePublishVo selectCoursePublishVoById(String id);
}
