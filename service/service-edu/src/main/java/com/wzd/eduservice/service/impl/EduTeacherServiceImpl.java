package com.wzd.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wzd.eduservice.entity.EduTeacher;
import com.wzd.eduservice.mapper.EduTeacherMapper;
import com.wzd.eduservice.service.EduTeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author wzd
 * @since 2021-01-30
 */
@Service
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher> implements EduTeacherService {

    @Cacheable(value = "teacher", key = "'selectIndexList'")
    @Override
    public List<EduTeacher> getIndexTeacher() {
        //查询前4条名师
        QueryWrapper<EduTeacher> teacherQueryWrapper = new QueryWrapper<>();
        teacherQueryWrapper.orderByDesc("ID");
        teacherQueryWrapper.last("LIMIT 4");
        return baseMapper.selectList(teacherQueryWrapper);
    }

    @CacheEvict(value = "teacher", allEntries = true)
    @Override
    public boolean save(EduTeacher entity) {
        return super.save(entity);
    }

    @CacheEvict(value = "teacher", allEntries = true)
    @Override
    public boolean updateById(EduTeacher entity) {
        return super.updateById(entity);
    }

    @CacheEvict(value = "teacher", allEntries = true)
    @Override
    public boolean removeById(Serializable id) {
        return super.removeById(id);
    }
}
