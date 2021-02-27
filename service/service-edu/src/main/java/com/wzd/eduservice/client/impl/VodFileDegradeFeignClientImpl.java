package com.wzd.eduservice.client.impl;

import com.wzd.commonutils.R;
import com.wzd.eduservice.client.VodClient;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @ClassName VodFileDegradeFeignClientImpl
 * @Author lofxve
 * @Date 2021/2/27 19:39
 * @Version 1.0
 */
@Component
public class VodFileDegradeFeignClientImpl implements VodClient {
    // 执行出错之后执行
    @Override
    public R deleteVideoById(String videoId) {
        return R.error().message("删除视频出错");
    }

    @Override
    public R deleteBatch(List<String> videoIdList) {
        return R.error().message("删除多个视频出错");
    }
}
