package com.wzd.commonutils;

//常量定义
public class ConstantUtil {

    //===================================redis中使用===================================//
    //短信验证码
    public static final String SMS_CODE = "sms:";
    //token
    public static final String USER_TOKEN = "token:";
    //动态点赞
    public static final String MOVEMENT_LIKE = "movement_like:{}_{}";

    public static final String VIDEO_LIKE = "video_like:{}_{}";
    //动态喜欢
    public static final String MOVEMENT_LOVE = "movement_love:{}_{}";
    //访问用户
    public static final String VISITORS = "visitors:";
    //关注用户
    public static final String FOCUS_USER = "focus_user:{}_{}";
    // 用户最后一次访问时间
    public static final String LAST_ACCESS_TIME = "last_access_time:";

    //===================================mongodb中使用===================================//
    //视频大数据id表名
    public static final String VIDEO_ID = "video";
    //动态大数据id表名
    public static final String MOVEMENT_ID = "movement";
    //推荐动态大数据id表名
    public static final String RECOMMEND_MOVEMENT_ID = "recommend_movement";
    //用户大数据id表名
    public static final String USER_ID = "user";


    //我的动态表名前缀
    public static final String MOVEMENT_MINE = "movement_mine_";
    //好友动态表名前缀
    public static final String MOVEMENT_FRIEND = "movement_friend_";

    //============================================================================//
    //初始化密码
    public static final String INIT_PASSWORD = "123456";
    //jwt加密盐
    public static final String JWT_SECRET = "eightOClockOnlineEducation";
}
