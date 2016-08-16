package com.app.merbng.mycodelibs.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.PowerManager;
import android.util.DisplayMetrics;
import android.webkit.WebView;
import android.widget.Toast;

import com.app.merbng.mycodelibs.BuildConfig;
import com.app.merbng.mycodelibs.Constent;

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
        } else {
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

    /**
     * 获取应用名称
     *
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

    public static int getPhoneWidth(Activity mContext) {
        // 获得手机宽高
        DisplayMetrics dm = new DisplayMetrics();
        mContext.getWindowManager().getDefaultDisplay().getMetrics(dm);
        Constent.PHONE_WHITH = dm.widthPixels;
        Constent.PHONE_HEIGHT = dm.heightPixels;
        return Constent.PHONE_WHITH;
    }

    public static int getPhoneHight(Activity mContext) {
        // 获得手机宽高
        DisplayMetrics dm = new DisplayMetrics();
        mContext.getWindowManager().getDefaultDisplay().getMetrics(dm);
        Constent.PHONE_HEIGHT = dm.heightPixels;
        return Constent.PHONE_HEIGHT;
    }

    /**
     * 检测屏幕是否开启
     *
     * @param context 上下文
     * @return 是否屏幕开启
     */
    public static boolean isScreenOn(Context context) {
        Context appContext = context.getApplicationContext();
        PowerManager pm = (PowerManager) appContext.getSystemService(Context.POWER_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT_WATCH) {
            return pm.isInteractive();
        } else {
            // noinspection all
            return pm.isScreenOn();
        }
    }
    /**
     * 获取进程名称
     *
     * @param context 上下文
     * @return 进程名称
     */
    public static String getProcessName(Context context) {
        int pid = android.os.Process.myPid();
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> infos = manager.getRunningAppProcesses();
        if (infos != null) {
            for (ActivityManager.RunningAppProcessInfo processInfo : infos) {
                if (processInfo.pid == pid) {
                    return processInfo.processName;
                }
            }
        }
        return null;
    }

    /**给我评分
     * @param mContext
     */
    public static void giveMePingFen(Context mContext){
        try {
            Uri uri = Uri.parse("market://details?id=" + mContext.getPackageName());
            Intent intent_pingfen = new Intent(Intent.ACTION_VIEW, uri);
            intent_pingfen.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mContext.startActivity(intent_pingfen);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(mContext, "Couldn't launch the market !",
                    Toast.LENGTH_SHORT).show();
        }
    }
}
