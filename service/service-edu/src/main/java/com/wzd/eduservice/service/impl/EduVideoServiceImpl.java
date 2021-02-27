package com.wzd.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wzd.eduservice.client.VodClient;
import com.wzd.eduservice.entity.EduVideo;
import com.wzd.eduservice.mapper.EduVideoMapper;
import com.wzd.eduservice.service.EduVideoService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author wzd
 * @since 2021-02-24
 */
@Service
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideo> implements EduVideoService {

    @Autowired
    private VodClient vodClient;

    @Override
    public boolean removeByCourseId(String courseId) {
        // 查询所有视频id
        QueryWrapper<EduVideo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id", courseId);
        // 查询单个字段
        queryWrapper.select("video_source_id");
        List<EduVideo> videos = baseMapper.selectList(queryWrapper);
        // 获取id列表
        List<String> videoIds = videos.stream()
                // 过滤空id
                .filter(video -> !StringUtils.isEmpty(video.getVideoSourceId()))
                // 获取id
                .map(video -> video.getVideoSourceId())
                .collect(Collectors.toList());
        if (videoIds.size() > 0) {
            // 远程调用服务
            vodClient.deleteBatch(videoIds);
        }

        QueryWrapper<EduVideo> videoQueryWrapper = new QueryWrapper<>();
        videoQueryWrapper.eq("course_id", courseId);
        Integer count = baseMapper.delete(videoQueryWrapper);

        return null != count && count > 0;
    }
}
