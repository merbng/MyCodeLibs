package com.app.merbng.mycodelibs.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.app.merbng.mycodelibs.R;
import com.app.merbng.mycodelibs.base.BaseActivity;
import com.app.merbng.mycodelibs.utils.SharedPrefUtils;

/**
 * 登陆注册页面
 * Created by zx on 2016/7/26.
 */
public class Login_ResisterActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_regisiter);
        fvbId();
    }

    private void fvbId() {
        findViewById(R.id.btn_login).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                SharedPrefUtils.putBoolean(mContext, "isLogin", true);
                Intent intent = new Intent(Login_ResisterActivity.this, MainActivity.class);
                startActivity(intent);
                break;
        }
    }
}
