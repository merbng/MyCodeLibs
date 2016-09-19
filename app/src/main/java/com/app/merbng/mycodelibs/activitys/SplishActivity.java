package com.app.merbng.mycodelibs.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.app.merbng.mycodelibs.R;
import com.app.merbng.mycodelibs.widget.SplashView;

/**
 * 闪屏页
 */
public class SplishActivity extends AppCompatActivity {
    private static final int REQUEST_CODE = 1234;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splish);

        SplashView.showSplashView(this, 3, R.drawable.img_splash, new SplashView.OnSplashViewActionListener() {
            @Override
            public void onSplashImageClick(String actionUrl) {
                Intent intent = new Intent(SplishActivity.this, OpenAdvActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.excess_fade_anim_in, R.anim.excess_fade_anim_out);
                finish();
            }

            @Override
            public void onSplashViewDismiss(boolean initiativeDismiss) {
                Intent intent = new Intent(SplishActivity.this, OpenAdvActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.excess_fade_anim_in, R.anim.excess_fade_anim_out);
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //    super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
            finish();
        }
    }
}
