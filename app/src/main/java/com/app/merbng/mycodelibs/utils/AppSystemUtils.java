package com.app.merbng.mycodelibs.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.webkit.WebView;

import com.app.merbng.mycodelibs.BuildConfig;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Created by zx on 2016/7/13.
 */
public class AppSystemUtils {
    public static String getCurrentRunningActivity(Context mContext) {
        ActivityManager mActivityManager = (ActivityManager) mContext.getSystemService(Context.ACTIVITY_SERVICE);
        //获得当前正在运行的activity
        List<ActivityManager.RunningTaskInfo> appList3 = mActivityManager.getRunningTasks(1000);
        for (ActivityManager.RunningTaskInfo running : appList3) {
            return running.baseActivity.getClassName();
        }
        return null;
    }

    /**
     * 解决webview 内存泄露的问题
     * 下面的方法很大程度上可以避免这种情况
     */
    public void releaseAllWebViewCallback() {
        if (android.os.Build.VERSION.SDK_INT < 16) {
            try {
                Field field = WebView.class.getDeclaredField("mWebViewCore");
                field = field.getType().getDeclaredField("mBrowserFrame");
                field = field.getType().getDeclaredField("sConfigCallback");
                field.setAccessible(true);
                field.set(null, null);
            } catch (NoSuchFieldException e) {
                if (BuildConfig.DEBUG) {
                    e.printStackTrace();
                }
            } catch (IllegalAccessException e) {
                if (BuildConfig.DEBUG) {
                    e.printStackTrace();
                }
            }
        }else {
            try {
                Field sConfigCallback = Class.forName("android.webkit.BrowserFrame").getDeclaredField("sConfigCallback");
                if (sConfigCallback != null) {
                    sConfigCallback.setAccessible(true);
                    sConfigCallback.set(null, null);
                }
            } catch (NoSuchFieldException e) {
                if (BuildConfig.DEBUG) {
                    e.printStackTrace();
                }
            } catch (ClassNotFoundException e) {
                if (BuildConfig.DEBUG) {
                    e.printStackTrace();
                }
            } catch (IllegalAccessException e) {
                if (BuildConfig.DEBUG) {
                    e.printStackTrace();
                }
            }
        }
    }
    /**获取应用名称
     * @param mContext
     * @return
     */
    public static String getAppName(Context mContext) {
        PackageManager packageManager = null;
        ApplicationInfo applicationInfo = null;
        String applicationName = null;
        try {
            packageManager = mContext.getApplicationContext().getPackageManager();
            applicationInfo = packageManager.getApplicationInfo(mContext.getPackageName(), 0);
            applicationName = (String) packageManager.getApplicationLabel(applicationInfo);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return applicationName;
    }
}
