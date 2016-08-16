package com.app.merbng.mycodelibs.activitys;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.app.merbng.mycodelibs.R;
import com.app.merbng.mycodelibs.base.BaseActivity;

/**
 * 跳转  动画
 */
public class StartAnimActivity extends BaseActivity {
    FloatingActionButton mFab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_zoom_img);
        fvbId();
    }

    private void fvbId() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mFab = ((FloatingActionButton) findViewById(R.id.fab));

    }

    // Fab的跳转事件
    public void startOtherActivity(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options =
                    ActivityOptions.makeSceneTransitionAnimation(this, mFab, mFab.getTransitionName());
            startActivity(new Intent(this, StartAnimOtherActivity.class), options.toBundle());
        } else {
            startActivity(new Intent(this, StartAnimOtherActivity.class));
        }
    }
}
