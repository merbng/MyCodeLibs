package com.app.merbng.mycodelibs.activitys;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.app.merbng.mycodelibs.MyCodeLibApplication;
import com.app.merbng.mycodelibs.R;
import com.tencent.mm.sdk.modelmsg.SendAuth;

public class WeixinLoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weixin_login);
        findViewById(R.id.btn_login_weixin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WeixinLogin();
            }
        });
    }

    private void WeixinLogin() {
        final SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "wechat_sdk_demo_test";
        MyCodeLibApplication.api.sendReq(req);
    }

}
