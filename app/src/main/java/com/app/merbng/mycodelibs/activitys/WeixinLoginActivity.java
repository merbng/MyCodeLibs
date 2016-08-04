package com.app.merbng.mycodelibs.activitys;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.app.merbng.mycodelibs.MyCodeLibApplication;
import com.app.merbng.mycodelibs.R;
import com.app.merbng.mycodelibs.base.BaseActivity;
import com.tencent.mm.sdk.modelmsg.SendAuth;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

/**
 * 微信登陆
 */
public class WeixinLoginActivity extends BaseActivity {
    private ProgressDialog mProgressDialog;
    /*微信登陆*/
    private IWXAPI api;
    public static final String APP_ID = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weixin_login);
        mProgressDialog = new ProgressDialog(mContext, ProgressDialog.THEME_HOLO_LIGHT);
        mProgressDialog.setTitle("请稍候...");
        mProgressDialog.setMax(100);
        mProgressDialog.setCancelable(true);
        mProgressDialog.setCanceledOnTouchOutside(false);
        findViewById(R.id.btn_login_weixin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WeixinLogin();
            }
        });
    }

    /**
     * 微信登陆
     */
    private void WeixinLogin() {
        if (api == null) {
            api = WXAPIFactory.createWXAPI(mContext, APP_ID, false);
            api.registerApp(APP_ID);
        }
        if (!api.isWXAppInstalled()) {
            //提醒用户没有按照微信
            show("没有安装微信,请先安装微信!");
            return;
        }
        SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "wechat_sdk_demo_test";
        api.sendReq(req);
        if (!mProgressDialog.isShowing()) {
            mProgressDialog.show();
        }
    }
}
