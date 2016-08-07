package com.app.merbng.mycodelibs.utils;

import android.content.Context;

public class SharedPrefUtils {
     

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
}
