package com.app.merbng.mycodelibs.activitys;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.merbng.mycodelibs.R;
import com.app.merbng.mycodelibs.base.BaseActivity;
import com.app.merbng.mycodelibs.utils.AppFunctionUtils;
import com.app.merbng.mycodelibs.utils.DensityUtil;
import com.app.merbng.mycodelibs.utils.SharedPrefUtils;

/**
 * 广告页
 */
public class OpenAdvActivity extends BaseActivity {
    Handler mhander;
    private static final int MESSAGE = 0x01;
    TextView timeText;
    int time = 3;
    LinearLayout jumpUrlLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_adv);
        timeText = (TextView) findViewById(R.id.time);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ((FrameLayout.LayoutParams) timeText.getLayoutParams()).topMargin = DensityUtil.getStateBarHeight(mContext);
        }

        jumpUrlLayout = (LinearLayout) findViewById(R.id.link_line);
        jumpUrlLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//    打开广告webview            startActivity(IntentUtil.startWebView(OpenAdvActivity.this, ""));
                time = 1;
            }
        });
        timeText.setText("跳过 " + time);
        mhander = new MyHandler();

        timeText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mhander.removeMessages(MESSAGE);
                selectWho();
            }
        });
    }

    /**
     * 判断打开哪个
     */
    private void selectWho() {
//        if (UserUtils.is_login(OpenAdvActivity.this)) {
//            startActivityWithTransition(MainActivity.class,true);
//        } else {
//            startActivityWithTransition(ViewPager_Activity.class,true);
//        }
        boolean isLogin = SharedPrefUtils.getBoolean(this, "isLogin");
        if (isLogin) {
            startActivity(new Intent(thisActivity, MainActivity.class));
            AppFunctionUtils.loadTutorial(mContext);
            finish();
        } else {
            startActivity(new Intent(thisActivity, Login_ResisterActivity.class));
            finish();
        }

    }

    @Override
    public void hand_message(Message msg) {

    }

    protected class MyHandler extends Handler {
        MyHandler() {
        }

        @Override
        public void handleMessage(Message msg) {
            if (msg.what == MESSAGE) {
                time--;
                timeText.setText("跳过 " + time);
                if (time == 0) {
                    selectWho();
                } else {
                    sendEmptyMessageDelayed(MESSAGE, 1000);
                }

            }

        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        mhander.removeMessages(MESSAGE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mhander.sendEmptyMessageDelayed(MESSAGE, 1000);
    }

    @Override
    public void finish() {
        mhander.removeMessages(MESSAGE);
        super.finish();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) { //监控/拦截/屏蔽返回键
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    public static void startThis(Context context) {
        Intent intent = new Intent(context, OpenAdvActivity.class);
        context.startActivity(intent);

    }
}
