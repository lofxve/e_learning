package com.wzd.test.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Builder;
import lombok.Data;

/**
 * @ClassName DataDome
 * @Author lofxve
 * @Date 2021/2/20 16:48
 * @Version 1.0
 */
@Data
@Builder
public class DataDome {
    @ExcelProperty(value = "学生编号", index = 0)
    private Long id;
    @ExcelProperty(value = "学生姓名", index = 0)
    private String name;
}
