package com.app.merbng.mycodelibs.activitys;

import android.content.Intent;
import android.os.Bundle;

import com.app.merbng.mycodelibs.R;
import com.app.merbng.mycodelibs.base.BaseActivity;
import com.app.merbng.mycodelibs.utils.LogUtil;
import com.app.merbng.mycodelibs.widget.SplashView;

/**
 * 闪屏页
 */
public class SplishActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splish);
        SplashView.showSplashView(this, 3, R.drawable.img_splash, new SplashView.OnSplashViewActionListener() {
            @Override
            public void onSplashImageClick(String actionUrl) {
                LogUtil.log.e("actionUrl：" + actionUrl);
                Intent intent = new Intent(mContext, MainActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onSplashViewDismiss(boolean initiativeDismiss) {
                Intent intent = new Intent(mContext, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        
    }
}
