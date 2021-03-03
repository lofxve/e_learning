package com.wzd.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzd.eduservice.entity.EduTeacher;
import com.wzd.eduservice.mapper.EduTeacherMapper;
import com.wzd.eduservice.service.EduTeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Override
    public Map<String, Object> pageTeacher(Page<EduTeacher> teacherPage) {
        QueryWrapper<EduTeacher> eduTeacherServiceQueryWrapper = new QueryWrapper<>();
        eduTeacherServiceQueryWrapper.orderByDesc("id");
        baseMapper.selectPage(teacherPage, eduTeacherServiceQueryWrapper);

        List<EduTeacher> records = teacherPage.getRecords();
        long current = teacherPage.getCurrent();
        long pages = teacherPage.getPages();
        long size = teacherPage.getSize();
        long total = teacherPage.getTotal();
        boolean hasNext = teacherPage.hasNext();
        boolean hasPrevious = teacherPage.hasPrevious();

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("items", records);
        map.put("current", current);
        map.put("pages", pages);
        map.put("size", size);
        map.put("total", total);
        map.put("hasNext", hasNext);
        map.put("hasPrevious", hasPrevious);

        return map;
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
