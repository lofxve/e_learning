package com.wzd.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wzd.baseservice.exceptionHandler.BaseException;
import com.wzd.eduservice.entity.EduChapter;
import com.wzd.eduservice.entity.EduVideo;
import com.wzd.eduservice.entity.capter.ChapterVo;
import com.wzd.eduservice.entity.capter.VideoVo;
import com.wzd.eduservice.mapper.EduChapterMapper;
import com.wzd.eduservice.mapper.EduVideoMapper;
import com.wzd.eduservice.service.EduChapterService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author wzd
 * @since 2021-02-24
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {

    @Autowired
    private EduVideoMapper videoMapper;

    @Override
    public List<ChapterVo> getChapterVideo(String courseId) {
        // 查询所有章节
        QueryWrapper<EduChapter> chapterVoQueryWrapper = new QueryWrapper<>();
        chapterVoQueryWrapper.eq("course_id", courseId);
        chapterVoQueryWrapper.orderByAsc("sort", "id");
        List<EduChapter> chapters = baseMapper.selectList(chapterVoQueryWrapper);
        // 查询所有小结
        QueryWrapper<EduVideo> videoQueryWrapper = new QueryWrapper<>();
        videoQueryWrapper.eq("course_id", courseId);
        videoQueryWrapper.orderByAsc("sort", "id");
        List<EduVideo> videos = videoMapper.selectList(videoQueryWrapper);

        // 创建组装值
        ArrayList<ChapterVo> chapterVos = new ArrayList<>();
        // 遍历章节
        for (EduChapter chapter : chapters) {
            ChapterVo chapterVo = new ChapterVo();
            BeanUtils.copyProperties(chapter, chapterVo);
            // 遍历小结
            ArrayList<VideoVo> videoVos = new ArrayList<>();
            for (EduVideo video : videos) {
                if (video.getCourseId().equals(chapter.getCourseId()) && video.getChapterId().equals(chapter.getId())) {
                    VideoVo videoVo = new VideoVo();
                    BeanUtils.copyProperties(video, videoVo);
                    videoVos.add(videoVo);
                }
            }
            chapterVo.setChildren(videoVos);

            chapterVos.add(chapterVo);
        }
        return chapterVos;
    }

    /**
     * @return void
     * @Author lofxve
     * @Description // 如果章节下有小结，则不能进行删除
     * @Date 18:20 2021/2/25
     * @Param [chapterId]
     **/
    @Override
    public boolean deleteChapter(String chapterId) {
        // 查询这个章节下的小结
        QueryWrapper<EduVideo> videoQueryWrapper = new QueryWrapper<>();
        videoQueryWrapper.eq("chapter_id", chapterId);
        Integer count = videoMapper.selectCount(videoQueryWrapper);
        if (count > 0) {
            throw new BaseException(20001, "该章节下含有小结不能进行删除");
        } else {
            // 删除章节
            int i = baseMapper.deleteById(chapterId);
            return i > 0;
        }
    }

    @Override
    public void removeByCourseId(String courseId) {
        QueryWrapper<EduChapter> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id", courseId);
        baseMapper.delete(queryWrapper);
    }
}
