package com.wzd.ucenterservice.controller;

import com.google.gson.Gson;
import com.wzd.baseservice.exceptionHandler.BaseException;
import com.wzd.commonutils.JwtUtils;
import com.wzd.ucenterservice.entity.UcenterMember;
import com.wzd.ucenterservice.service.UcenterMemberService;
import com.wzd.ucenterservice.utils.ConstantPropertiesUtil;
import com.wzd.ucenterservice.utils.HttpClientUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;

/**
 * @ClassName WxApiController
 * @Author lofxve
 * @Date 2021/3/2 17:06
 * @Version 1.0
 */
@Api(tags = "微信登录")
@CrossOrigin
@Controller//注意这里没有配置 @RestController
@RequestMapping("/api/ucenter/wx")
public class WxApiController {
    //1. 第三方发起微信授权登录请求，微信用户允许授权第三方应用后，微信会拉起应用或重定向到第三方网站，并且带上授权临时票据code参数；
    //2. 通过code参数加上AppID和AppSecret等，通过API换取access_token；
    //3. 通过access_token进行接口调用，获取用户基本数据资源或帮助用户实现基本操作。

    @Autowired
    private UcenterMemberService memberService;

    // TODO: 2021/3/2 测试使用回调函数，正常登录之后，直接在微信重定向的网址写，这里测试使用，仅在本地写，获取用户信息

    /**
     * @return
     * @Author lofxve
     * @Description // 2. 通过code参数加上AppID和AppSecret等，通过API换取access_token；
     * @Date 17:48 2021/3/2
     * @Param
     **/
    @GetMapping("callback")
    public String callback(String code, String state, HttpSession session) {
        //得到授权临时票据code
//        System.out.println(code);
//        System.out.println(state);

        //向认证服务器发送请求换取access_token
        String baseAccessTokenUrl = "https://api.weixin.qq.com/sns/oauth2/access_token" +
                "?appid=%s" +
                "&secret=%s" +
                "&code=%s" +
                "&grant_type=authorization_code";

        String accessTokenUrl = String.format(baseAccessTokenUrl,
                ConstantPropertiesUtil.WX_OPEN_APP_ID,
                ConstantPropertiesUtil.WX_OPEN_APP_SECRET,
                code);

        String result = null;
        try {
            result = HttpClientUtils.get(accessTokenUrl);
//            System.out.println("accessToken=============" + result);
        } catch (Exception e) {
            throw new BaseException(20001, "获取access_token失败");
        }

        //解析json字符串
        Gson gson = new Gson();
        HashMap map = gson.fromJson(result, HashMap.class);
        String accessToken = (String) map.get("access_token");
        String openid = (String) map.get("openid");


        //查询数据库当前用用户是否曾经使用过微信登录
        UcenterMember member = memberService.getByOpenid(openid);
        if (member == null) {
//            System.out.println("新用户注册");

            //访问微信的资源服务器，获取用户信息
            String baseUserInfoUrl = "https://api.weixin.qq.com/sns/userinfo" +
                    "?access_token=%s" +
                    "&openid=%s";
            String userInfoUrl = String.format(baseUserInfoUrl, accessToken, openid);
            String resultUserInfo = null;
            try {
                resultUserInfo = HttpClientUtils.get(userInfoUrl);
//                System.out.println("resultUserInfo==========" + resultUserInfo);
            } catch (Exception e) {
                throw new BaseException(20001, "获取用户信息失败");
            }

            //解析json
            HashMap<String, Object> mapUserInfo = gson.fromJson(resultUserInfo, HashMap.class);
            String nickname = (String) mapUserInfo.get("nickname");
            String headimgurl = (String) mapUserInfo.get("headimgurl");

            //向数据库中插入一条记录
            member = new UcenterMember();
            member.setNickname(nickname);
            member.setOpenid(openid);
            member.setAvatar(headimgurl);
            memberService.save(member);
        }

        // 生成jwt
        String token = JwtUtils.getJwtToken(member.getId(), member.getNickname());

        //因为端口号不同存在跨域问题，cookie不能跨域，所以这里使用url重写
        return "redirect:http://localhost:3000?token=" + token;
    }

    /**
     * @return java.lang.String
     * @Author lofxve
     * @Description // 请求微信oauth2.0授权登录，获取二维码
     * @Date 17:08 2021/3/2
     * @Param [session]
     **/
    @ApiOperation("获取二维码")
    @GetMapping("login")
    public String genQrConnect(HttpSession session) {
        // 微信开放平台授权baseUrl
        String baseUrl = "https://open.weixin.qq.com/connect/qrconnect" +
                "?appid=%s" +
                "&redirect_uri=%s" +
                "&response_type=code" +
                "&scope=snsapi_login" +
                "&state=%s" +
                "#wechat_redirect";
        // 回调地址
        String redirectUrl = ConstantPropertiesUtil.WX_OPEN_REDIRECT_URL; //获取业务服务器重定向地址
        try {
            redirectUrl = URLEncoder.encode(redirectUrl, "UTF-8"); //url编码
        } catch (UnsupportedEncodingException e) {
            throw new BaseException(20001, e.getMessage());
        }

        // 防止csrf攻击（跨站请求伪造攻击）
        //String state = UUID.randomUUID().toString().replaceAll("-", "");//一般情况下会使用一个随机数
        String state = "eightOClock";//为了让大家能够使用我搭建的外网的微信回调跳转服务器，这里填写你在ngrok的前置域名
//        System.out.println("state = " + state);

        // 采用redis等进行缓存state 使用sessionId为key 30分钟后过期，可配置
        //键："wechar-open-state-" + httpServletRequest.getSession().getId()
        //值：satte
        //过期时间：30分钟

        //生成qrcodeUrl
        String qrcodeUrl = String.format(
                baseUrl,
                ConstantPropertiesUtil.WX_OPEN_APP_ID,
                redirectUrl,
                state);

        // 重定向到请求的地址中
        return "redirect:" + qrcodeUrl;
    }
}
