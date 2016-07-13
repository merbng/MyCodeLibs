package com.app.merbng.mycodelibs;

import android.content.Context;

/**
 * Created by Sam on 14-3-24.
 */
public class MyApplication extends android.app.Application {
    private static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = getApplicationContext();
        
    }

    public static Context getContext() {
        return sContext;
    }
 
}
