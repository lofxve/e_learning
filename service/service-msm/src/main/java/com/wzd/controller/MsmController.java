package com.wzd.controller;

import com.wzd.commonutils.R;
import com.wzd.commonutils.RandomUtil;
import com.wzd.service.MsmService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName MsmController
 * @Author lofxve
 * @Date 2021/3/1 15:57
 * @Version 1.0
 */
@CrossOrigin
@RestController
@Api("阿里云短信")
@RequestMapping("/edumsm/msm")
public class MsmController {

    @Autowired
    private MsmService msmService;

    @GetMapping("send/{phone}")
    public R sendMsm(@PathVariable String phone) {
        // 随机生成验证码
        String code = RandomUtil.getFourBitRandom();

        // 阿里云发送验证码
        // TODO: 2021/3/1 模拟应用短信发送，实际应用再打开
//        boolean isSend = msmService.sendMsm(code,phone);
        boolean isSend = true;
        if (isSend) {
            return R.ok();
        } else {
            return R.error().message("发送短信失败");
        }
    }
}
