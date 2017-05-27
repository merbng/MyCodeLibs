package com.app.merbng.mycodelibs.utils;

import android.text.TextUtils;

import com.app.merbng.mycodelibs.MyCodeLibApplication;
import com.app.merbng.mycodelibs.beans.UserBean;
import com.google.gson.Gson;

/**
 * Created by ght on 2017/2/17.
 * 用户工具类
 */

public final class UserUtils {
    /**
     * 隐藏构造器.
     */
    private UserUtils() {
    }

    private static UserBean user;
    /**
     * 当前用户ID.
     */
    private static String currentUserId = null;


    /**
     * 获取当前用户.
     *
     * @return TQUser
     */
    public static UserBean getCurrentUser() {
        if (null == user) {
            String jsonString = ConfigUtils.getUserBeanString(MyCodeLibApplication.getmInstance());
            if (TextUtils.isEmpty(jsonString)) {
                return null;
            }
            Gson gson = new Gson();
            user = gson.fromJson(jsonString, UserBean.class);
        }
        return user;
    }

    /**
     * 获取当前用户ID.
     *
     * @return userId
     */
    public static String getCurrentId() {
        if (TextUtils.isEmpty(currentUserId)) {
            String str = ConfigUtils.getCurrentUserId(MyCodeLibApplication.getmInstance());
            if (!TextUtils.isEmpty(str)) {
                currentUserId = str;
            }
        }
        return currentUserId;
    }

    /**
     * 检测一个userid是不是自己的id.
     *
     * @param userId 待检测的id
     * @return true/false
     */
    public static boolean isMe(String userId) {
        return !TextUtils.isEmpty(userId) && userId.equals(getCurrentId());
//        return false;
    }

}
