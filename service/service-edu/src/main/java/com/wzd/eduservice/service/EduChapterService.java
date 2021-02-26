package com.wzd.eduservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wzd.eduservice.entity.EduChapter;
import com.wzd.eduservice.entity.capter.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author wzd
 * @since 2021-02-24
 */
public interface EduChapterService extends IService<EduChapter> {

    List<ChapterVo> getChapterVideo(String courseId);

    boolean deleteChapter(String chapterId);

    void removeByCourseId(String courseId);
}
