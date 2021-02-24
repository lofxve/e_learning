package com.wzd.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wzd.eduservice.entity.EduSubject;
import com.wzd.eduservice.entity.excel.SubjectData;
import com.wzd.eduservice.entity.subject.SubjectNestedVo;
import com.wzd.eduservice.entity.subject.SubjectVo;
import com.wzd.eduservice.listener.SubjectExcelListener;
import com.wzd.eduservice.mapper.EduSubjectMapper;
import com.wzd.eduservice.service.EduSubjectService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author wzd
 * @since 2021-02-20
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {
    @Autowired
    private EduSubjectMapper subjectMapper;

    /**
     * @return void
     * @Author lofxve
     * @Description // 添加课程分类
     * @Date 17:24 2021/2/20
     * @Param [file]
     **/
    @Override
    public void saveSubject(MultipartFile file, EduSubjectService subjectService) {
        try {
            InputStream inputStream = file.getInputStream();
            // 使用EasyExcel进行读取表格
            EasyExcel.read(inputStream, SubjectData.class, new SubjectExcelListener(subjectService)).sheet().doRead();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<SubjectNestedVo> nestedList() {
        // 获取一级分类
        QueryWrapper<EduSubject> queryWrapperOne = new QueryWrapper<>();
        queryWrapperOne.eq("parent_id", "0");
        queryWrapperOne.orderByAsc("sort", "id");
        List<EduSubject> oneSubjects = subjectMapper.selectList(queryWrapperOne);

        // 获取二级分类
        QueryWrapper<EduSubject> queryWrapperTwo = new QueryWrapper<>();
        queryWrapperTwo.ne("parent_id", "0");
        queryWrapperTwo.orderByAsc("sort", "id");
        List<EduSubject> TwoSubjects = subjectMapper.selectList(queryWrapperTwo);

        // 返回值组装
        ArrayList<SubjectNestedVo> subjectNestedVoArrayList = new ArrayList<>();
        for (EduSubject oneSubject : oneSubjects) {
            // 创建一级分类
            SubjectNestedVo subjectNestedVo = new SubjectNestedVo();
            BeanUtils.copyProperties(oneSubject, subjectNestedVo);

            // 创建二级分类
            ArrayList<SubjectVo> subjectVos = new ArrayList<>();
            for (EduSubject twoSubject : TwoSubjects) {
                if (twoSubject.getParentId().equals(oneSubject.getId())){
                    SubjectVo subjectVo = new SubjectVo();
                    BeanUtils.copyProperties(twoSubject, subjectVo);
                    subjectVos.add(subjectVo);
                }
            }
            subjectNestedVo.setChildren(subjectVos);
            subjectNestedVoArrayList.add(subjectNestedVo);
        }
        return subjectNestedVoArrayList;
    }
}
