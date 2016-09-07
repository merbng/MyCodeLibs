package com.app.merbng.mycodelibs.A_windowManager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RomUtils {
    
    public final static String ROM_MIUI_V5 = "V5";
    public final static String ROM_MIUI_V6 = "V6";

    /**是否是小米*/
    public static boolean isMIUI(){
        if(Build.MANUFACTURER.equals("Xiaomi"))
            return true;
        else 
            return false;
    }
    
    /**获得小米版本  V6   V5*/
    public static String getRom(){
        return getSystemProperty("ro.miui.ui.version.name");
    }
    
    public static String getSystemProperty(String propName) {
        String line;
        BufferedReader input = null;
        try {
            Process p = Runtime.getRuntime().exec("getprop " + propName);
            input = new BufferedReader(new InputStreamReader(p.getInputStream()), 1024);
            line = input.readLine();
            input.close();
        } catch (IOException ex) {
            return null;
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                }
            }
        }
        return line;
    }
    
    /**
     * 打开MIUI权限管理界面(MIUI v5, v6)
     * 
     * @param context
     */
    public static void openMiuiPermissionActivity(Context context) {
        Intent intent = new Intent("miui.intent.action.APP_PERM_EDITOR");
        String rom = RomUtils.getRom();
        String SCHEME = "package";
        if (RomUtils.ROM_MIUI_V5.equals(rom)) {
            intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);  
            Uri uri = Uri.fromParts(SCHEME, context.getPackageName(), null);  
            intent.setData(uri);  
            context.startActivity(intent);  
            return;
        } else if (RomUtils.ROM_MIUI_V6.equals(rom)) {
            intent.setClassName("com.miui.securitycenter", "com.miui.permcenter.permissions.AppPermissionsEditorActivity");
            intent.putExtra("extra_pkgname", context.getPackageName());
        }

        if (isIntentAvailable(context, intent)) {
            if (context instanceof Activity) {
                Activity a = (Activity) context;
                a.startActivityForResult(intent, 2);
            }
        } 
    }
    
    public static void openMiuiPermissionAutoBoot(Context context){
        String SCHEME = "package";
        String rom = RomUtils.getRom();
        PackageInfo info = null;
        try {
            PackageManager pm = context.getPackageManager();
            info = pm.getPackageInfo(context.getPackageName(), 0);
            Intent intent = new Intent("miui.intent.action.APP_PERM_EDITOR");
            if (RomUtils.ROM_MIUI_V5.equals(rom)) {
                intent.setClassName("com.android.settings", "com.miui.securitycenter.permission.AppPermissionsEditor");
                intent.putExtra("extra_package_uid", info.applicationInfo.uid);
                Uri uri = Uri.fromParts(SCHEME, context.getPackageName(), null);  
                intent.setData(uri);  
            }else if(RomUtils.ROM_MIUI_V6.equals(rom)){
                intent.setClassName("com.miui.securitycenter", "com.miui.securitycenter.MainActivity");
                intent.putExtra("extra_pkgname", context.getPackageName());
            }
            
            if (isIntentAvailable(context, intent)) {
                if (context instanceof Activity) {
                    Activity a = (Activity) context;
                    a.startActivityForResult(intent, 2);
                }
            } 
        } catch (Exception e) {
                e.printStackTrace();
        }
    }
    
    public static void openMiui5PermissionActivity(Context context){
        String SCHEME = "package";
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);  
        Uri uri = Uri.fromParts(SCHEME, context.getPackageName(), null);  
        intent.setData(uri);  
        if (isIntentAvailable(context, intent)) {
            if (context instanceof Activity) {
                Activity a = (Activity) context;
                a.startActivityForResult(intent, 2);
            }
        } 
    }
    
    public static void openMiui6PermissionActivity(Context context){
        Intent intent = new Intent("miui.intent.action.APP_PERM_EDITOR");
        intent.setClassName("com.miui.securitycenter", "com.miui.permcenter.permissions.AppPermissionsEditorActivity");
        intent.putExtra("extra_pkgname", context.getPackageName());
        if (isIntentAvailable(context, intent)) {
            if (context instanceof Activity) {
                Activity a = (Activity) context;
                a.startActivityForResult(intent, 2);
            }
        } 
    }

    public static void openMiui6SafeCenterActivity(Context context){
        Intent intent = new Intent("miui.intent.action.APP_PERM_EDITOR");
        intent.setClassName("com.miui.securitycenter", "com.miui.securitycenter.MainActivity");
        intent.putExtra("extra_pkgname", context.getPackageName());
        if (isIntentAvailable(context, intent)) {
            if (context instanceof Activity) {
                Activity a = (Activity) context;
                a.startActivityForResult(intent, 2);
            }
        } 
    }
    
    /**
     * 判断是否有可以接受的Activity
     * @param context
     * @param intent
     * @return
     */
    public static boolean isIntentAvailable(Context context, Intent intent) {
        if (intent == null) return false;
        return context.getPackageManager().queryIntentActivities(intent, PackageManager.GET_ACTIVITIES).size() > 0;
    }
    
}
