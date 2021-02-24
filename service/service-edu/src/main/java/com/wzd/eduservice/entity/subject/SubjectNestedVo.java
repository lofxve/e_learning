package com.wzd.eduservice.entity.subject;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName SubjectNestedVo
 * @Author lofxve
 * @Date 2021/2/24 10:36
 * @Version 1.0
 */
@Data
public class SubjectNestedVo {

    private String id;
    private String title;
    private List<SubjectVo> children = new ArrayList<>();
}
