package com.app.merbng.mycodelibs;

/**
 * Created by zx on 2016/8/4.
 */
public class Constent {
    public static final String WXAPP_ID="";
    // 第三方注册
    public static String OTHERREGISTER =  "https://api.mystarcloud.com/1.0/register/platform";
    // 第三方登陆
    public static String OTHERLOGIN =  "https://api.mystarcloud.com/1.0/login/platform";
    // 第三方用户头像上传接口
    public static String OTHERUSERHEARDIMG = "https://api.mystarcloud.com/1.0/oss/uploadImageFromUrl";
    //  微信登陆--刷新Token
    public static String WXREFRESHTOKEN = "https://api.weixin.qq.com/sns/oauth2/refresh_token";
    //  微信登陆--获取Token
    public static String WXGETTOKEN = "https://api.weixin.qq.com/sns/oauth2/access_token";
    //微信登陆--获取用户信息
    public static  String WXUSERINFOURL = "https://api.weixin.qq.com/sns/userinfo";
    //微信登陆--检验授权凭证（access_token）是否有效
    public static  String WXCHECKTOKEN = "https://api.weixin.qq.com/sns/auth";
}
