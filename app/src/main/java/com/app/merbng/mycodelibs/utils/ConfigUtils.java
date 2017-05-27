package com.app.merbng.mycodelibs.utils;

import android.content.Context;


/**
 * Created by ght on 2017/2/13.
 */
public final class ConfigUtils {
    private static final String SP_LAST_CHECK_VERSION_NAME = "lastCheckVersionName"; //记录上次的更新检查到的版本
    private static final String SP_LAST_TIME_MILLIS = "lastTimeMillis"; //记录上次的更新检查时间

    private static final String SP_FIRST_SHOW_MAIN_CREATE_RIPPLE = "firstShowMainCreateRipple"; //是否第一次显示首页创建按钮的博文效果

    private static final String SP_VERSION_CODE = "VersionCode"; //版本管理

    private static final String CURRENT_USER_ID_KEY = "currentUserId"; //当前用户id
    private static final String CURRENT_USER_SINA_TOKEN = "CurrentUserSinaToken";

    // 手机常量
    private static final String PHONE_WIDTH = "phoneWidth"; //手机宽度
    private static final String PHONE_HEIGHT = "phoneHeight"; //手机高度
    private static final String PHONE_STATUS_BAR_HEIGHT = "phoneStatusBarHeight"; //状态栏高度
    private static final String PHONE_TITLE_BAR_HEIGHT = "phoneTitleBarHeight"; //标题栏高度


    private static final String TAG_LOGIN = "isLogin"; //是否登录标签
    private static final String SP_ADV = "advertisement"; //是否显示广告
    private static final String SP_HAS_GUIDE = "has_guide"; //已经引导


    private static final String SP_USER_IMG = "user_img"; //记录用户头像url
    private static final String SP_PHONE_NUM = "phonenum"; //记录的手机号
    private static final String SP_AUTO_PLAY = "autoplay"; //是否显示自动播放视频
    private static final String SP_DIS_PREVIEW_IMAGE = "disablePreviewImages"; //不显示预览图片

    private static final String USER_BEAN = "user_bean";

    /**
     * 工具类不能实例化.
     */
    private ConfigUtils() {
    }

    /**
     * 获取上次检查更新的时间.
     * 如2.0.0
     *
     * @param mContext 上下文
     * @return 默认为0
     */
    public static long getLastTimeMillis(Context mContext) {
        return SharedPrefUtils.getLong(mContext, SP_LAST_TIME_MILLIS);
    }

    /**
     * 设置上一次检查更新的版本名称.
     * 如2.0.0
     *
     * @param mContext             上下文
     * @param lastCheckVersionName 版本名称
     */
    public static void putLastCheckVersionName(Context mContext, String lastCheckVersionName) {
        SharedPrefUtils.putAny(mContext, SP_LAST_CHECK_VERSION_NAME, lastCheckVersionName);
    }

    /**
     * 获取上一次检查更新的版本名称.
     *
     * @param mContext 上下文
     * @return 默认为"" 不是null
     */
    public static String getLastCheckVersionName(Context mContext) {
        return SharedPrefUtils.getString(mContext, SP_LAST_CHECK_VERSION_NAME);
    }

    /**
     * 设置上次检查更新的版本号.
     * 如11
     *
     * @param mContext 上下文
     * @return 默认情况的为""
     */
    public static String getVersionCode(Context mContext) {
        return SharedPrefUtils.getString(mContext, SP_VERSION_CODE);
    }

    /**
     * 获取上一次检查更新的版本号.
     *
     * @param mContext 上下文
     * @param value    版本号
     */
    public static void setVersionCode(Context mContext, String value) {
        SharedPrefUtils.putAny(mContext, SP_VERSION_CODE, value);
    }

    /**
     * 获取当前登录用户的id.
     *
     * @param mContext 上下文
     * @return 默认为""
     */
    public static String getCurrentUserId(Context mContext) {
        return SharedPrefUtils.getString(mContext, CURRENT_USER_ID_KEY);
    }

    /**
     * 设置当前登录用户的id.
     *
     * @param mContext 上下文
     * @param value    用户id
     */
    public static void setCurrentUserId(Context mContext, String value) {
        SharedPrefUtils.putAny(mContext, CURRENT_USER_ID_KEY, value);
    }

    /**
     * 获取屏幕宽度.
     *
     * @param mContext 上下文
     * @return 默认为0
     */
    public static int getPhoneWidth(Context mContext) {
        return SharedPrefUtils.getInt(mContext, PHONE_WIDTH);
    }

    /**
     * 设置屏幕宽度.
     *
     * @param mContext 上下文
     * @param value    屏幕宽度
     */
    public static void setPhoneWidth(Context mContext, int value) {
        SharedPrefUtils.putInt(mContext, PHONE_WIDTH, value);
    }

    /**
     * 获取屏幕高度.
     *
     * @param mContext 上下文
     * @return 默认为0
     */
    public static int getPhoneHeight(Context mContext) {
        return SharedPrefUtils.getInt(mContext, PHONE_HEIGHT);
    }

    /**
     * 设置屏幕高度.
     *
     * @param mContext 上下文
     * @param value    屏幕宽度
     */
    public static void setPhoneHeight(Context mContext, int value) {
        SharedPrefUtils.putInt(mContext, PHONE_HEIGHT, value);
    }

    /**
     * 获取手机的标题栏高度.
     *
     * @param mContext 上下文
     * @return 默认0
     */
    public static int getPhoneTitleBarHeight(Context mContext) {
        return SharedPrefUtils.getInt(mContext, PHONE_TITLE_BAR_HEIGHT);
    }

    /**
     * 设置手机标题栏高度.
     *
     * @param mContext 上下文
     * @param value    标题栏高度
     */
    public static void setPhoneTitleBarHeight(Context mContext, int value) {
        SharedPrefUtils.putInt(mContext, PHONE_TITLE_BAR_HEIGHT, value);
    }

    /**
     * 获取手机状态栏高度.
     *
     * @param mContext 上下文
     * @return 默认0
     */
    public static int getPhoneStatusBarHeight(Context mContext) {
        return SharedPrefUtils.getInt(mContext, PHONE_STATUS_BAR_HEIGHT);
    }

    /**
     * 设置手机状态栏高度.
     *
     * @param mContext 上下文
     * @param value    状态栏高度
     */
    public static void setPhoneStatusBarHeight(Context mContext, int value) {
        SharedPrefUtils.putInt(mContext, PHONE_STATUS_BAR_HEIGHT, value);
    }

    /**
     * 获取当前登录用户信息.
     *
     * @param mContext 上下文
     * @return 当前登录用户信息 默认“”  正常情况为json数据
     */
    public static String getUserBeanString(Context mContext) {
        return SharedPrefUtils.getString(mContext, USER_BEAN);
    }

    /**
     * 设置登录用户信息.
     *
     * @param mContext 上下文
     * @param value    json字符串
     */
    public static void setUserBeanString(Context mContext, String value) {
        SharedPrefUtils.putAny(mContext, USER_BEAN, value);
    }

    /**
     * 清除所有sharepre.
     *
     * @param context 上下文
     */
    public static void clearAll(Context context) {
        context.getApplicationContext().getSharedPreferences(SharedPrefUtils.SP_NAME, Context.MODE_PRIVATE)
                .edit().clear().apply();
    }

    /**
     * 已经显示过引导页.
     *
     * @param context 上下文
     * @return 默认false 未显示
     */
    public static boolean alreadyGuide(Context context) {
        return SharedPrefUtils.getBoolean(context, SP_HAS_GUIDE);
//        return true;
    }

    /**
     * 设置已经显示过引导页.
     *
     * @param context  上下文
     * @param hasGuide true显示过
     */
    public static void setAlreadyGuide(Context context, boolean hasGuide) {
        SharedPrefUtils.putBoolean(context, SP_HAS_GUIDE, hasGuide);
    }

    /**
     * 设置是否显示广告.
     *
     * @param context 上下文
     * @param hasAdv  true有广告
     */
    public static void setAdv(Context context, boolean hasAdv) {
        SharedPrefUtils.putBoolean(context, SP_ADV, hasAdv);
    }

    /**
     * 获取是否有广告.
     *
     * @return 默认没有
     */
    public static boolean hasAdv() {
        return false;
    }

    /**
     * 设置登录状态.
     *
     * @param context 上下文
     * @param isLogin true 已经登录
     */
    public static void putIsLogin(Context context, boolean isLogin) {
        SharedPrefUtils.putBoolean(context, TAG_LOGIN, isLogin);
    }

    /**
     * 获取登录状态.
     *
     * @param context 上下文
     * @return 默认FALSE 未登录
     */
    public static boolean isLogin(Context context) {
        return SharedPrefUtils.getBoolean(context, TAG_LOGIN);
    }

    /**
     * 获取上次登录过的用户的头像.
     *
     * @param context 上下文
     * @return 默认为""
     */
    public static String getUserImg(Context context) {
        return SharedPrefUtils.getString(context, SP_USER_IMG);
    }

    /**
     * 设置上次登录过的用户头像.
     *
     * @param context    上下文
     * @param userImgUrl 用户头像URL
     */
    public static void setUserImg(Context context, String userImgUrl) {
        SharedPrefUtils.putAny(context, SP_USER_IMG, userImgUrl);
    }

    /**
     * 获取上次登录过的用户手机号.
     *
     * @param context 上下文
     * @return 默认为""
     */
    public static String getPhoneNum(Context context) {
        return SharedPrefUtils.getString(context, SP_PHONE_NUM);
    }

    /**
     * 设置上次登录过的用户手机号.
     *
     * @param context     上下文
     * @param phoneNumber 手机号
     */
    public static void setPhoneNum(Context context, String phoneNumber) {
        SharedPrefUtils.putAny(context, SP_PHONE_NUM, phoneNumber);
    }

    /**
     * 获取当前用户的新浪token.
     *
     * @param applicationContext 上下文
     * @return 默认为""
     */
    public static String getCurrentUserSinaToken(Context applicationContext) {
        return SharedPrefUtils.getString(applicationContext, CURRENT_USER_SINA_TOKEN);
    }

    /**
     * 获取当前用户的新浪token.
     *
     * @param applicationContext 上下文
     * @param tokenjson          token
     */
    public static void setCurrentUserSinaToken(Context applicationContext, String tokenjson) {
        SharedPrefUtils.putAny(applicationContext, CURRENT_USER_SINA_TOKEN, tokenjson);
    }

    /**
     * 设置自动播放状态.
     *
     * @param applicationContext 上下文
     * @param autoplay           三种可选状态的字符串
     */
    public static void setAutoplay(Context applicationContext, String autoplay) {
        SharedPrefUtils.putAny(applicationContext, SP_AUTO_PLAY, autoplay);
    }

    /**
     * 获取自动播放的状态.
     *
     * @param applicationContext 上下文
     * @return 三种可选状态的字符串
     */
    public static String autoplay(Context applicationContext) {
        return SharedPrefUtils.getString(applicationContext, SP_AUTO_PLAY);
    }


    /**
     * 获取是否不允许预览图片.
     *
     * @param context 上下文
     * @return 默认false 允许
     */
    public static boolean disablePreviewImages(Context context) {
        return SharedPrefUtils.getBoolean(context, SP_DIS_PREVIEW_IMAGE);
    }

    /**
     * 设置是否不允许预览图片.
     *
     * @param context              上下文
     * @param disablePreviewImages true不允许预览 false允许
     */
    public static void setDisablePreviewImages(Context context, boolean disablePreviewImages) {
        SharedPrefUtils.putBoolean(context, SP_DIS_PREVIEW_IMAGE, disablePreviewImages);
    }
    /**
     * 返回是否是第一次展示波纹效果
     *
     * @param context
     * @return
     */
    public static boolean getIsFirstShowMainCreateRipple(Context context) {
        return SharedPrefUtils.getInt(context, SP_FIRST_SHOW_MAIN_CREATE_RIPPLE) == 1;
    }
}
