package com.app.merbng.mycodelibs.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;

import com.app.merbng.mycodelibs.MyCodeLibApplication;
import com.app.merbng.mycodelibs.activitys.MainActivity;
import com.sdsmdg.tastytoast.TastyToast;
import com.zhy.changeskin.base.BaseSkinActivity;

import java.util.LinkedList;
import java.util.List;

/**
 * Activity基类
 *
 * @author maxl
 * @email maxl@dxyer.com
 * @date 2014-12-18
 */
public class BaseActivity extends BaseSkinActivity {

    protected Context mContext;
    protected Activity thisActivity;
    private List<Activity> closeActivitiesForChangeLoginUser = new LinkedList<Activity>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mContext = getApplicationContext();
        thisActivity = this;
        MyCodeLibApplication.getmInstance().addActivity(this);
    }

    /**
     * 将此activity添加到集合里，方便统一关闭</br>这个方法是为了切换登录用户而同时关闭多个页面
     */
    public void addActivityForChangeLoginUser(Activity activity) {
        closeActivitiesForChangeLoginUser.add(activity);
    }

    /**
     * 当Activity结束时从当前集合中移除
     *
     * @param activity
     */
    public void removeActivity(Activity activity) {
        if (closeActivitiesForChangeLoginUser.contains(activity)) {
            closeActivitiesForChangeLoginUser.remove(activity);
            activity.finish();

        }
    }

    protected void show(String message) {
//        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
        TastyToast.makeText(mContext,message,TastyToast.LENGTH_SHORT,TastyToast.INFO);
    }

    // 按两次返回键退出程序
    private long exitTime = 0;

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK &&
                event.getAction() == KeyEvent.ACTION_DOWN &&
                event.getRepeatCount() == 0) {
            if (thisActivity instanceof MainActivity) {
                if ((System.currentTimeMillis() - exitTime) > 2000) {
                    exitTime = System.currentTimeMillis();
                    return false;
                } else {
                    thisActivity.finish();
                }
            }
        }
        return super.dispatchKeyEvent(event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MyCodeLibApplication.getmInstance().exitActivity();
    }
}