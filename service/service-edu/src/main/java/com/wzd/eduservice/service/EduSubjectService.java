package com.wzd.eduservice.service;

import com.wzd.eduservice.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wzd.eduservice.entity.subject.SubjectNestedVo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author wzd
 * @since 2021-02-20
 */
public interface EduSubjectService extends IService<EduSubject> {

    void saveSubject(MultipartFile file,EduSubjectService subjectService);

    List<SubjectNestedVo> nestedList();
}
