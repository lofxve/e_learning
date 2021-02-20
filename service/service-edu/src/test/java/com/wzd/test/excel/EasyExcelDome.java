package com.wzd.test.excel;

import com.alibaba.excel.EasyExcel;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName EasyExcelDome
 * @Author lofxve
 * @Date 2021/2/20 16:44
 * @Version 1.0
 */
public class EasyExcelDome {
    private static List<DataDome> getDate(){
        ArrayList<DataDome> dataDomes = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            dataDomes.add(DataDome.builder().id(Long.valueOf(i)).name("张三"+i).build());
        }
        return dataDomes;
    }
    public static void main(String[] args) {
        // 实现写操作
        String filename = "D:\\write.xlsx";

//        EasyExcel.write(filename, DataDome.class).sheet("学生列表").doWrite(getDate());

        // 读操作
        EasyExcel.read(filename, DataDome.class,new ExcelListener()).sheet().doRead();
    }
}
