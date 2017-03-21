package com.app.merbng.mycodelibs.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.PowerManager;
import android.support.v4.app.ActivityOptionsCompat;
import android.transition.Explode;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.widget.Toast;

import com.app.merbng.mycodelibs.BuildConfig;
import com.app.merbng.mycodelibs.Constent;
import com.app.merbng.mycodelibs.R;
import com.app.merbng.mycodelibs.activitys.SplishActivity;
import com.app.merbng.mycodelibs.model.MyAppInfo;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zx on 2016/7/13.
 */
public class AppSystemUtils {

    public static void openActivity(Activity activity, Class<?> target) {
        ActivityOptionsCompat aoc;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//5.0以上
            Explode explode = new Explode();
            explode.setDuration(1000);
            activity.getWindow().setExitTransition(explode);
            activity.getWindow().setEnterTransition(explode);
            aoc = ActivityOptionsCompat.makeSceneTransitionAnimation(activity);
            activity.startActivity(new Intent(activity, target), aoc.toBundle());
        } else {
            activity.startActivity(new Intent(activity, target));
        }
    }

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

    /**
     * 给我评分
     *
     * @param mContext
     */
    public static void giveMePingFen(Context mContext) {
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

    public static final String ACTION_ADD_SHORTCUT = "com.android.launcher.action.INSTALL_SHORTCUT";

    /**
     * 添加快捷方式
     *
     * @param name
     */
    private void addShortcut(Context mContext, String name) {
        Intent addShortcutIntent = new Intent(ACTION_ADD_SHORTCUT);

        // 不允许重复创建
        addShortcutIntent.putExtra("duplicate", false);// 经测试不是根据快捷方式的名字判断重复的
        // 应该是根据快链的Intent来判断是否重复的,即Intent.EXTRA_SHORTCUT_INTENT字段的value
        // 但是名称不同时，虽然有的手机系统会显示Toast提示重复，仍然会建立快链
        // 屏幕上没有空间时会提示
        // 注意：重复创建的行为MIUI和三星手机上不太一样，小米上似乎不能重复创建快捷方式

        // 名字
        addShortcutIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME, name);

        // 图标
        addShortcutIntent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE,
                Intent.ShortcutIconResource.fromContext(mContext,
                        R.drawable.ic_action_cancel));

        // 设置关联程序
        Intent launcherIntent = new Intent(Intent.ACTION_MAIN);
        launcherIntent.setClass(mContext, SplishActivity.class);
        launcherIntent.addCategory(Intent.CATEGORY_LAUNCHER);

        addShortcutIntent
                .putExtra(Intent.EXTRA_SHORTCUT_INTENT, launcherIntent);

        // 发送广播
        mContext.sendBroadcast(addShortcutIntent);
    }

    public static final String ACTION_REMOVE_SHORTCUT = "com.android.launcher.action.UNINSTALL_SHORTCUT";

    /**
     * 移除快捷方式
     *
     * @param mContext
     * @param name
     */
    private void removeShortcut(Context mContext, String name) {
        // remove shortcut的方法在小米系统上不管用，在三星上可以移除
        Intent intent = new Intent(ACTION_REMOVE_SHORTCUT);

        // 名字
        intent.putExtra(Intent.EXTRA_SHORTCUT_NAME, name);

        // 设置关联程序
        Intent launcherIntent = new Intent(mContext,
                SplishActivity.class).setAction(Intent.ACTION_MAIN);

        intent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, launcherIntent);

        // 发送广播
        mContext.sendBroadcast(intent);
    }

    /**
     * 隐藏键盘
     *
     * @param context
     * @param v
     */
    public static void hideKeyBoard(Context context, View v) {

        InputMethodManager imm = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }

    /**获取手机已安装APP
     * @param packageManager
     * @return
     */
    public static List scanLocalInstallAppList(PackageManager packageManager) {
        List<MyAppInfo> myAppInfos = new ArrayList();
        try {
            List<PackageInfo> packageInfos = packageManager.getInstalledPackages(0);
            for (int i = 0; i < packageInfos.size(); i++) {
                PackageInfo packageInfo = packageInfos.get(i);
                    //过滤掉系统app
                if ((ApplicationInfo.FLAG_SYSTEM & packageInfo.applicationInfo.flags) != 0) {
                    continue;
                }
                MyAppInfo myAppInfo = new MyAppInfo();
                myAppInfo.setPackageName(packageInfo.packageName);
                myAppInfo.setAppName(packageInfo.applicationInfo.loadLabel(packageManager).toString());
                if (packageInfo.applicationInfo.loadIcon(packageManager) == null) {
                    continue;
                }
                myAppInfo.setImage(packageInfo.applicationInfo.loadIcon(packageManager));
                myAppInfos.add(myAppInfo);
            }
        } catch (Exception e) {
            LogUtil.log.e("", "===============获取应用包信息失败");
        }
        return myAppInfos;
    }
}
