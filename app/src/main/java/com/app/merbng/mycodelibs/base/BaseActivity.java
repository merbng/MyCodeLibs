package com.app.merbng.mycodelibs.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Toast;

import com.app.merbng.mycodelibs.activitys.MainActivity;
import com.zhy.changeskin.base.BaseSkinActivity;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mContext = getApplicationContext();
        thisActivity = this;
    }


    protected void show(String message) {
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
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
}