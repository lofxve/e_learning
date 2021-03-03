package com.wzd.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzd.eduservice.entity.EduTeacher;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author wzd
 * @since 2021-01-30
 */
public interface EduTeacherService extends IService<EduTeacher> {

    List<EduTeacher> getIndexTeacher();

    Map<String, Object> pageTeacher(Page<EduTeacher> teacherPage);
}
