package com.wzd.test.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.Map;

/**
 * @ClassName ExcelListener
 * @Author lofxve
 * @Date 2021/2/20 17:05
 * @Version 1.0
 */
public class ExcelListener extends AnalysisEventListener<DataDome> {
    //读取excel表头信息
    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        System.out.println("表头信息：" + headMap);
    }

    // 一行一行读取
    @Override
    public void invoke(DataDome dataDome, AnalysisContext analysisContext) {
        System.out.println("****"+dataDome);
    }


    // 读取完成之后
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
