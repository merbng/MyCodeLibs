package com.app.merbng.mycodelibs.utils;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;

import com.app.merbng.mycodelibs.R;

/**
 * Created by sam on 14-10-30.
 */
public class UIUtils {
    /**4.4状态栏颜色
     * @param activity
     */
    public static void setSystemBarTintColor(Activity activity){
        if(SystemBarTintManager.isKitKat()){
            SystemBarTintManager tintManager = new SystemBarTintManager(activity);
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setStatusBarTintDrawable(new ColorDrawable(activity.getResources().getColor(R.color.skin_color_1_night )));
        }
    }
}
