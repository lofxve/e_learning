package com.wzd.eduservice.controller;


import com.wzd.commonutils.R;
import com.wzd.eduservice.entity.subject.SubjectNestedVo;
import com.wzd.eduservice.service.EduSubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author wzd
 * @since 2021-02-20
 */
@CrossOrigin //跨域
@RestController
@Api(tags = "课程分类管理")
@RequestMapping("/admin/eduservice/subject")
public class EduSubjectController {
    @Autowired
    private EduSubjectService subjectService;

    @PostMapping("addSubject")
    @ApiOperation(value = "添加课程")
    public R addSubject (MultipartFile file){
        subjectService.saveSubject(file,subjectService);
        return R.ok();
    }

    @ApiOperation(value = "嵌套数据列表")
    @GetMapping("nestedList")
    public R nestedList(){
        List<SubjectNestedVo> subjectNestedVoList = subjectService.nestedList();
        return R.ok().data("items", subjectNestedVoList);
    }
}

