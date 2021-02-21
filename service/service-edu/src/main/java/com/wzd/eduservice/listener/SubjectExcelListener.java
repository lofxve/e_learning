package com.wzd.eduservice.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wzd.baseservice.exceptionHandler.BaseException;
import com.wzd.eduservice.entity.EduSubject;
import com.wzd.eduservice.entity.excel.SubjectData;
import com.wzd.eduservice.service.EduSubjectService;

import java.util.Map;

/**
 * @ClassName SubjectExcelListener
 * @Author lofxve
 * @Date 2021/2/20 17:32
 * @Version 1.0
 */
public class SubjectExcelListener extends AnalysisEventListener<SubjectData> {

    // 不能交给spring管理，不能注入其他对象，所以使用构造函数进行注入
    public EduSubjectService subjectService;

    public SubjectExcelListener() {
    }

    public SubjectExcelListener(EduSubjectService subjectService) {
        this.subjectService = subjectService;
    }

    //读取excel表头信息
    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        // System.out.println("表头信息：" + headMap);
    }

    // 对表格每一行进行操作
    @Override
    public void invoke(SubjectData subjectData, AnalysisContext analysisContext) {
        if (subjectData == null) {
            throw new BaseException(20001, "文件数据为空");
        }
        // 添加以及分类
        EduSubject eduSubject = this.existOneSubject(subjectService, subjectData.getOneSubjectName());
        if (eduSubject == null) {
            EduSubject eduSubjectOne = new EduSubject();
            eduSubjectOne.setTitle(subjectData.getOneSubjectName());
            eduSubjectOne.setParentId("0");
            subjectService.save(eduSubjectOne);
        }

        String pid = eduSubject.getId();
        EduSubject eduSubject1 = this.existTwoSubject(subjectService, subjectData.getTwoSubjectName(), pid);
        if (eduSubject1 == null) {
            EduSubject eduSubjectTwo = new EduSubject();
            eduSubjectTwo.setTitle(subjectData.getTwoSubjectName());
            eduSubjectTwo.setParentId(pid);
            subjectService.save(eduSubjectTwo);
        }
    }

    //判断二级分类是否重复
    private EduSubject existTwoSubject(EduSubjectService subjectService, String name, String pid) {
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title", name);
        wrapper.eq("parent_id", pid);
        EduSubject eduSubject = subjectService.getOne(wrapper);
        return eduSubject;
    }

    //判断一级分类是否重复
    private EduSubject existOneSubject(EduSubjectService subjectService, String name) {
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title", name);
        wrapper.eq("parent_id", "0");
        EduSubject eduSubject = subjectService.getOne(wrapper);
        return eduSubject;
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
