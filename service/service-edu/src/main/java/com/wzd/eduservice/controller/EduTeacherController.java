package com.wzd.eduservice.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzd.commonutils.R;
import com.wzd.eduservice.entity.EduTeacher;
import com.wzd.eduservice.entity.vo.TeacherQuery;
import com.wzd.eduservice.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * @Author lofxve
 * @Description // 讲师管理
 * @Date 9:54 2021/2/18
 * @Param
 * @return
 **/
@CrossOrigin //跨域
@RestController
@Api(tags = "讲师管理")
@RequestMapping("/admin/eduservice/teacher")
public class EduTeacherController {
    @Autowired
    private EduTeacherService eduTeacherService;

    @GetMapping("findAll")
    @ApiOperation(value = "所有讲师列表")
    public R findAllTeacher() {
        List<EduTeacher> list = eduTeacherService.list(null);
        return R.ok().data("items", list);
    }

    @DeleteMapping("{id}")
    @ApiOperation(value = "根据ID删除讲师")
    public R removeTeacherById(@ApiParam(name = "id", value = "讲师ID", required = true) @PathVariable String id) {
        boolean flag = eduTeacherService.removeById(id);
        if (flag) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    @PostMapping("teachers/{page}/{limit}")
    @ApiOperation(value = "讲师条件分页查询")
    public R pageTeacherListCondition(@ApiParam(name = "page", value = "当前页码", required = true)
                                      @PathVariable Long page,
                                      @ApiParam(name = "limit", value = "每页记录数", required = true)
                                      @PathVariable Long limit,
                                      @RequestBody(required = false) TeacherQuery teacherQuery) {
        // 创建page对象
        Page<EduTeacher> eduTeacherPage = new Page<>(page, limit);

        // 多条件组合查询
        QueryWrapper<EduTeacher> eduTeacherQueryWrapper = new QueryWrapper<>();

        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();

        if (!StringUtils.isEmpty(name)) {
            eduTeacherQueryWrapper.like("name", name);
        }
        if (!StringUtils.isEmpty(level)) {
            eduTeacherQueryWrapper.eq("level", level);
        }
        if (!StringUtils.isEmpty(begin)) {
            eduTeacherQueryWrapper.ge("gmt_create", begin);
        }

        if (!StringUtils.isEmpty(end)) {
            eduTeacherQueryWrapper.le("gmt_create", end);
        }

        // 根据创建时间降序排列
        eduTeacherQueryWrapper.orderByDesc("gmt_create");

        // 调用方法实现分页
        eduTeacherService.page(eduTeacherPage, eduTeacherQueryWrapper);

        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        stringObjectHashMap.put("total", eduTeacherPage.getTotal());
        stringObjectHashMap.put("row", eduTeacherPage.getRecords());

        return R.ok().data(stringObjectHashMap);
    }

    @PostMapping("addTeacher")
    @ApiOperation(value = "添加讲师")
    public R addTeacher(@RequestBody EduTeacher eduTeacher) {
        boolean save = eduTeacherService.save(eduTeacher);
        if (save) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    @GetMapping("getTeacher/{id}")
    @ApiOperation(value = "根据ID查询讲师")
    public R getTeacherById(@ApiParam(name = "id", value = "讲师ID", required = true)
                            @PathVariable String id) {
        EduTeacher teacher = eduTeacherService.getById(id);
        return R.ok().data("teacher", teacher);
    }

    @PostMapping("updateTeacher")
    @ApiOperation(value = "根据ID修改讲师")
    public R updateTeacher(@RequestBody EduTeacher eduTeacher) {
        boolean flag = eduTeacherService.updateById(eduTeacher);
        if (flag) {
            return R.ok();
        } else {
            return R.error();
        }
    }
}

