package com.app.merbng.mycodelibs.utils;

import android.app.ActivityManager;
import android.content.Context;

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
}
