package com.wzd.eduservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wzd.eduservice.entity.EduVideo;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author wzd
 * @since 2021-02-24
 */
public interface EduVideoService extends IService<EduVideo> {
    boolean removeByCourseId(String courseId);
}
