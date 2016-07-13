package com.app.merbng.mycodelibs.activitys;

import android.os.Bundle;

import com.app.merbng.mycodelibs.R;
import com.app.merbng.mycodelibs.base.BaseActivity;
import com.lusfold.spinnerloading.SpinnerLoading;


/**
 * 转圈Loading
 */
public class LoadingActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        SpinnerLoading loading = (SpinnerLoading) findViewById(R.id.loading);
        loading.setCircleRadius(8);
        loading.setPaintMode(0);//空心圆
        loading.setItemCount(3);
    }
}
