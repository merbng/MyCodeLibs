package com.app.merbng.mycodelibs;

import android.os.Environment;

/**
 * Created by zx on 2016/8/4.
 */
public class Constent {

    /**
     * 分享下载
     */
    public static final String SHARE_APP = "http://www.upshequ.com/download/download.html";

    public static final String SHARE_URL = "http://www.upshequ.com/share/share.html?topicId=";

    public static final String PLATFORM_QQ = "qq";
    public static final String PLATFORM_QZONE = "qzone";
    public static final String PLATFORM_SINA = "sina";
    public static final String PLATFORM_WEIXIN = "weixin";
    public static final String PLATFORM_WEIXIN_QUAN = "pengyouquan";
    /*新浪微博分享内容字数*/
    public static final int SINA_CONTENT_CNT = 75;
    public static String DIALOG_KEY="dialog_key";
    public static int PHONE_WHITH;
    public static int PHONE_HEIGHT;
    /*三方分享*/
//    分享的url
    public static final String USERCARDFOLDERDETAIL_SHARE = "https://api.mystarcloud.com/1.0/share/cards?userCardId=";
    public static final String SHARE = "https://github.com/Merbn/MyCodeLibs";

    public static final String SYSTEM_SHARE = "SystemShare";
    public static final String platform_QQ = "qq";
    public static final String platform_Qzone = "qzone";
    public static final String platform_Sina = "sina";
    public static final String platform_Weixin = "weixin";
    public static final String platform_WeixinQuan = "pengyouquan";
    /*QQ分享*/
    public static final String APPID_QQ = "";
    /*新浪微博分享*/
    public static final String SINA_APP_KEY = ""; // 应用的APP_KEY,正式版
    public static final String SINA_REDIRECT_URL = "https://api.weibo.com/oauth2/default.html";// 应用的回调页
    public static final String SINA_SCOPE = "all";
    /*微信分享*/

    public static final String SECRET = "";
    public static final String WXAPP_ID = "";
    // 第三方注册
    public static String OTHERREGISTER = "https://api.mystarcloud.com/1.0/register/platform";
    // 第三方登陆
    public static String OTHERLOGIN = "https://api.mystarcloud.com/1.0/login/platform";
    // 第三方用户头像上传接口
    public static String OTHERUSERHEARDIMG = "https://api.mystarcloud.com/1.0/oss/uploadImageFromUrl";
    //  微信登陆--刷新Token
    public static String WXREFRESHTOKEN = "https://api.weixin.qq.com/sns/oauth2/refresh_token";
    //  微信登陆--获取Token
    public static String WXGETTOKEN = "https://api.weixin.qq.com/sns/oauth2/access_token";
    //微信登陆--获取用户信息
    public static String WXUSERINFOURL = "https://api.weixin.qq.com/sns/userinfo";
    //微信登陆--检验授权凭证（access_token）是否有效
    public static String WXCHECKTOKEN = "https://api.weixin.qq.com/sns/auth";

    public final static String DOWNLOAD_IMAGE_PATH = getPath() + "/mystarcloud/cache/image/";// 下载图片位置

    /**
     * 获取图片存储路径
     */
    public static String getPath() {
        return Environment.getExternalStorageDirectory().getAbsolutePath();
    }
}
