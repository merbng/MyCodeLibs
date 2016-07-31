package com.app.merbng.mycodelibs.A_TumbleEditText.mr_immortalz.com.stereoview;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.app.merbng.mycodelibs.R;

/**
 * Created by Mr_immortalZ on 2016/7/15.
 * email : mr_immortalz@qq.com
 */
public class TumbeEditTextActivity extends AppCompatActivity {

    private Button btnLogin;
    private Button btnSetting;
    private Button btnImage;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tumbeedittext);
        btnLogin = (Button) findViewById(R.id.btn_login);
        btnSetting = (Button) findViewById(R.id.btn_setting);
        btnImage = (Button) findViewById(R.id.btn_image);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TumbeEditTextActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        btnSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TumbeEditTextActivity.this, SettingActivity.class);
                startActivity(intent);
            }
        });
        btnImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TumbeEditTextActivity.this, ImageActivity.class);
                startActivity(intent);
            }
        });
    }
}
