package com.app.merbng.mycodelibs.base;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.widget.Toast;

import com.app.merbng.mycodelibs.MyCodeLibApplication;
import com.app.merbng.mycodelibs.activitys.MainActivity;
import com.app.merbng.mycodelibs.interfaces.BaseActivityInterface;
import com.sdsmdg.tastytoast.TastyToast;
import com.zhy.changeskin.base.BaseSkinActivity;

import java.lang.ref.WeakReference;
import java.util.LinkedList;
import java.util.List;

import qiu.niorgai.StatusBarCompat;

/**
 * Activity基类
 *
 * @author maxl
 * @email maxl@dxyer.com
 * @date 2014-12-18
 */
public class BaseActivity extends BaseSkinActivity implements BaseActivityInterface {
    private final int CODE_MSG = 3721;// 发送消息
    protected Context mContext;
    protected Activity thisActivity;
    private List<Activity> closeActivitiesForChangeLoginUser = new LinkedList<Activity>();
    public ProgressDialog mProgressDialog;

    protected MyHandler myHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mContext = getApplicationContext();
        thisActivity = this;
        MyCodeLibApplication.getmInstance().addActivity(this);
        mProgressDialog = new ProgressDialog(mContext);
        mProgressDialog.setTitle("请稍候...");
        mProgressDialog.setMax(100);
        mProgressDialog.setCancelable(true);
        mProgressDialog.setCanceledOnTouchOutside(true);
        myHandler = new MyHandler(this);
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

    /**
     * 弹出Toath
     *
     * @param str
     */
    public void show(String str) {
        try {
            Message message = Message.obtain(myHandler);
            message.what = CODE_MSG;
            message.obj = str;
            message.sendToTarget();
        } catch (Exception e) {
            Toast.makeText(mContext, str, Toast.LENGTH_SHORT).show();
        }
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
    public void hand_message(Message msg) {

    }

    /**
     * 发送Message
     */
    public void sendMessage(int what, Object obj, int arg1, int arg2) {
        Message message = Message.obtain(myHandler);
        message.what = what;
        message.obj = obj;
        message.arg1 = arg1;
        message.arg2 = arg2;
        message.sendToTarget();
    }

    @SuppressLint("HandlerLeak")
    public class MyHandler extends Handler {
        WeakReference<BaseActivity> mFragment;

        MyHandler(BaseActivity acitivity) {
            mFragment = new WeakReference<BaseActivity>(acitivity);
        }

        @Override
        public void handleMessage(android.os.Message msg) {
            if (msg.what == CODE_MSG) {
                String msg_tis = (String) msg.obj;
                if (null == msg_tis || msg_tis.equals("")) {
                    Toast.makeText(mContext, null, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(mContext, msg_tis, Toast.LENGTH_SHORT).show();
                }
            }
            hand_message(msg);
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (myHandler != null && myHandler.hasMessages(CODE_MSG)) {
            myHandler.removeMessages(CODE_MSG);
        }
        MyCodeLibApplication.getmInstance().removeActivity(thisActivity);
    }
}