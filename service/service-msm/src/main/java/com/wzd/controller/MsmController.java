package com.wzd.controller;

import com.wzd.commonutils.ConstantUtil;
import com.wzd.commonutils.R;
import com.wzd.commonutils.RandomUtil;
import com.wzd.service.MsmService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName MsmController
 * @Author lofxve
 * @Date 2021/3/1 15:57
 * @Version 1.0
 */
@CrossOrigin
@RestController
@Api(tags = "阿里云短信")
@RequestMapping("/edumsm/msm")
public class MsmController {

    @Autowired
    private MsmService msmService;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @ApiOperation("发送验证码")
    @GetMapping("send/{phone}")
    public R sendMsm(@PathVariable String phone) {
        // 从redis中获取验证码
        String redisCode = redisTemplate.opsForValue().get(ConstantUtil.SMS_CODE + phone);
        if (!StringUtils.isEmpty(redisCode)) {
            return R.ok();
        }
        // 随机生成验证码
        String code = RandomUtil.getFourBitRandom();

        // 阿里云发送验证码
        // TODO: 2021/3/1 模拟应用短信发送，实际应用再打开
//        boolean isSend = msmService.sendMsm(code,phone);
        boolean isSend = true;
        if (isSend) {
            // 将验证码放入redis，并设置有效时间
            redisTemplate.opsForValue().set(ConstantUtil.SMS_CODE + phone, code, 5, TimeUnit.MINUTES);
            return R.ok();
        } else {
            return R.error().message("发送短信失败");
        }
    }
}
