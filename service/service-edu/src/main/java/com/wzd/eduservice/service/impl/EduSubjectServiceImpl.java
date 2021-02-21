package com.wzd.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.wzd.eduservice.entity.EduSubject;
import com.wzd.eduservice.entity.excel.SubjectData;
import com.wzd.eduservice.listener.SubjectExcelListener;
import com.wzd.eduservice.mapper.EduSubjectMapper;
import com.wzd.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

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
    /**
     * @Author lofxve
     * @Description // 添加课程分类
     * @Date 17:24 2021/2/20
     * @Param [file]
     * @return void
     **/
    @Override
    public void saveSubject(MultipartFile file,EduSubjectService subjectService) {
        try {
            InputStream inputStream = file.getInputStream();
            // 使用EasyExcel进行读取表格
            EasyExcel.read(inputStream, SubjectData.class,new SubjectExcelListener(subjectService)).sheet().doRead();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
