package com.wzd.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wzd.eduservice.entity.EduCourse;
import com.wzd.eduservice.entity.frontvo.CourseQueryVo;
import com.wzd.eduservice.entity.frontvo.CourseWebVo;
import com.wzd.eduservice.entity.vo.CourseInfoVo;
import com.wzd.eduservice.entity.vo.CoursePublishVo;
import com.wzd.eduservice.entity.vo.CourseQuery;

import java.util.List;
import java.util.Map;

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

    CourseInfoVo getCourseInfoById(String courseId);

    void updateCourseInfo(CourseInfoVo courseInfoVo);

    CoursePublishVo getCoursePublishVoById(String id);

    boolean publishCourseById(String id);

    void pageQuery(Page<EduCourse> pageParam, CourseQuery courseQuery);

    boolean removeCourseById(String id);

    List<EduCourse> getIndexCourse();

    Map<String, Object> pageListWeb(Page<EduCourse> pageParam, CourseQueryVo courseQuery);

    /**
     * @return com.wzd.eduservice.entity.frontvo.CourseWebVo
     * @Author lofxve
     * @Description // 获取课程基本信息
     * @Date 12:03 2021/3/4
     * @Param [courseId]
     **/
    CourseWebVo getBaseCourseInfo(String courseId);

    /**
     * 更新课程浏览数
     *
     * @param id
     */
    void updatePageViewCount(String id);
}
