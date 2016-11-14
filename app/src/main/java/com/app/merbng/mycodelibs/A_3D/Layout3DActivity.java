package com.app.merbng.mycodelibs.A_3D;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.app.merbng.mycodelibs.R;

/**
 * 一秒让你的view拥有3D效果！
 * https://github.com/githubwing/ThreeDLayout
 * Created by wing on 2016/10/16.
 */

public class Layout3DActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

    }


    public void startWeatherActivity(View view){
        startActivity(new Intent(this,WeatherActivity.class));
    }

    public void startUserCenterActivity(View view){
        startActivity(new Intent(this,UserCenterActivity.class));
    }
}
