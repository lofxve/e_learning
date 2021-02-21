package com.wzd.eduservice.controller;


import com.wzd.commonutils.R;
import com.wzd.eduservice.service.EduSubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
@Api(tags = "课程管理")
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
}

