package com.app.merbng.mycodelibs.model;

import android.graphics.drawable.Drawable;

/**获取手机已安装APP
 * Created by Merbng on 2017/3/21.
 */

public class MyAppInfo {
    private Drawable image;
    private String packageName;
    private String AppName;

    public MyAppInfo() {
    }

    public MyAppInfo(Drawable image, String packageName, String appName) {
        this.image = image;
        this.packageName = packageName;
        AppName = appName;
    }

    public Drawable getImage() {
        return image;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getAppName() {
        return AppName;
    }

    public void setAppName(String appName) {
        AppName = appName;
    }
}
