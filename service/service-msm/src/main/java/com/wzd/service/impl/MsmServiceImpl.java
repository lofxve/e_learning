package com.wzd.service.impl;

import com.wzd.service.MsmService;
import com.wzd.utils.HttpUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName MsmServiceImpl
 * @Author lofxve
 * @Date 2021/3/1 15:56
 * @Version 1.0
 */
@Service
public class MsmServiceImpl implements MsmService {
    @Override
    public boolean sendMsm(String code, String phone) {
        if (StringUtils.isEmpty(phone)) {
            return false;
        }
        int send = send(phone, code);
        if (send == 200) {
            return true;
        } else {
            return false;
        }
    }

    public int send(String phone, String code) {
        int statusCode = 20000;
        String host = "https://gyytz.market.alicloudapi.com";
        String path = "/sms/smsSend";
        String method = "POST";
        String appcode = "90ec6ef405084653abcbdb26789f36c8";
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        Map<String, String> querys = new HashMap<String, String>();
        querys.put("mobile", phone);
        querys.put("param", "**code**:" + code);
        querys.put("smsSignId", "2e65b1bb3d054466b82f0c9d125465e2");
        querys.put("templateId", "81e8a442ea904694a37d2cec6ea6f2bc");
        Map<String, String> bodys = new HashMap<String, String>();
        try {
            /**
             * 重要提示如下:
             * HttpUtils请从
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
             * 下载
             *
             * 相应的依赖请参照
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
             */
            HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
            // System.out.println(response.toString());
            statusCode = response.getStatusLine().getStatusCode();
            // System.out.println("********************************" + statusCode);
            //获取response的body
            // System.out.println(EntityUtils.toString(response.getEntity()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusCode;
    }
}
