package com.app.merbng.mycodelibs;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;

import com.app.merbng.mycodelibs.music.PlayerService;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.zhy.changeskin.SkinManager;

import java.util.LinkedList;
import java.util.List;


/**
 * User: wxp
 * Date: 2015-12-01
 * Time: 15:21
 */
public class MyCodeLibApplication extends Application {
    public static Context mContext;
    private static MyCodeLibApplication mInstance = null;
    //  存放打开的activity
    private List<Activity> allActivities = new LinkedList<>();
    public static String WXapp_id = "wxdf6d515d366bfd4d";
    public static IWXAPI api;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        mInstance = this;
//        换肤
        SkinManager.getInstance().init(this);
//        注册微信登陆
        api = WXAPIFactory.createWXAPI(mContext, WXapp_id, true);
        api.registerApp(WXapp_id);
        startService(new Intent(this, PlayerService.class));
    }
     /**
     * 将此activity添加到集合里，方便统一关闭
     * 这个方法是为了切换登录用户而同时关闭多个页面
     */
    public void addActivity(Activity activity) {
        allActivities.add(activity);
    }

    /**
     * 当Activity结束时从当前集合中移除
     *
     * @param activity 被移除的activity
     */
    public void removeActivity(Activity activity) {
        if (allActivities.contains(activity)) {
            allActivities.remove(activity);
            // activity.finish();
        }
    }

    /**
     * 从当前集合中移除指定的activity
     */
    public void removeSpecActivity(Activity activity) {
        if (allActivities.contains(activity)) {
            allActivities.remove(activity);
        }
    }

    /**
     * 退出应用结束所有页面
     */
    public void exitActivity() {
        try {
            for (Activity activity : allActivities) {
                if (activity != null)
                    activity.finish();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        System.gc();
    }

    public synchronized static MyCodeLibApplication getmInstance() {
        return mInstance;
    }

    public static Context getmContext() {
        return mContext;
    }
}