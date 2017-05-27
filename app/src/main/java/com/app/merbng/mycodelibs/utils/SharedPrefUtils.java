package com.app.merbng.mycodelibs.utils;

import android.content.Context;

public class SharedPrefUtils {

    private static final String SP_RECENT_SHARED_PACKAGE = "recentSharedPackage";//最近使用的分享程序

    public static void putBoolean(Context context, String key, boolean flag) {
        context.getApplicationContext().getSharedPreferences("MerbngKey", Context.MODE_PRIVATE)
                .edit().putBoolean(key, flag).commit();

    }

    public static boolean getBoolean(Context context, String key) {
        boolean flag = context.getApplicationContext().getSharedPreferences("MerbngKey",
                Context.MODE_PRIVATE).getBoolean(key, false);
        return flag;

    }


     

       
    /**
     * 共享参数 存储
     *
     * @param context
     * @param key
     * @param value
     * @return
     */
    public static boolean setAny(Context context, String key, String value) {
        boolean flag = false;
        context.getApplicationContext().getSharedPreferences("MerbngKey", Context.MODE_PRIVATE)
                .edit().putString(key, value).commit();
        return flag;
    }

    /**
     * 共享参数 存储Int
     *
     * @param context
     * @param key
     * @param value
     * @return
     */
    public static boolean setInt(Context context, String key, int value) {
        boolean flag = false;
        context.getApplicationContext().getSharedPreferences("MerbngKey", Context.MODE_PRIVATE)
                .edit().putInt(key, value).commit();
        return flag;
    }

    /**
     * 共享参数 得到Int
     *
     * @param context
     * @param key
     * @return
     */
    public static int getInt(Context context, String key) {
        return context.getApplicationContext().getSharedPreferences("MerbngKey",
                Context.MODE_PRIVATE).getInt(key, 1);
    }

    public static String getString(Context context, String key) {
        String result = null;
        result = context.getApplicationContext().getSharedPreferences("MerbngKey",
                Context.MODE_PRIVATE).getString(key, "");
        return result;
    }

    public static long getLong(Context context, String key) {
        return context.getApplicationContext().getSharedPreferences("MerbngKey",
                Context.MODE_PRIVATE).getLong(key, 0);
    }

    public static boolean setLong(Context context, String key, long value) {
        boolean flag = false;
        context.getApplicationContext().getSharedPreferences("MerbngKey", Context.MODE_PRIVATE)
                .edit().putLong(key, value).commit();
        return flag;
    }

    public static void removeInfo(Context context, String key) {
        context.getApplicationContext().getSharedPreferences("MerbngKey", Context.MODE_PRIVATE)
                .edit().remove(key).commit();
    }
    public static String getRecentSharedPackage(Context mContext) {
        return SharedPrefUtils.getString(mContext,SP_RECENT_SHARED_PACKAGE);
    }

    public static void putRecentSharedPackage(Context mContext,String recentSharedPackage) {
        SharedPrefUtils.setAny(mContext,SP_RECENT_SHARED_PACKAGE, recentSharedPackage);
    }
    private SharedPrefUtils() {
    }
    static final String SP_NAME = "sp_name_dami";



    /**
     * 共享参数 存储
     */
    static boolean putAny(Context context, String key, String value) {
        return context.getApplicationContext().getSharedPreferences(SP_NAME, Context.MODE_PRIVATE)
                .edit().putString(key, value).commit();
    }

    /**
     * 共享参数 存储Int
     */
    static boolean putInt(Context context, String key, int value) {
        return context.getApplicationContext().getSharedPreferences(SP_NAME, Context.MODE_PRIVATE)
                .edit().putInt(key, value).commit();
    }


}
