package com.app.merbng.wxapi;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.app.merbng.mycodelibs.MyCodeLibApplication;
import com.app.merbng.mycodelibs.base.IRequestBack;
import com.app.merbng.mycodelibs.base.Request;
import com.app.merbng.mycodelibs.base.RequestParams;
import com.app.merbng.mycodelibs.utils.LogUtil;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.modelmsg.SendAuth;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

/**
 * Created by zx on 2016/7/15.
 */
public class WXEntryActivity extends Activity implements IWXAPIEventHandler {
    private Context context = WXEntryActivity.this;
    private IWXAPI api;
    private String SECRET = "223d726f966031f30125d0e4e7d4aed3";
    private String CODE = "";
    private String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + MyCodeLibApplication.WXapp_id + "&secret=" + SECRET + "&code=" + CODE + "&grant_type=authorization_code";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtil.log.e("----------onCreate：");
        api = WXAPIFactory.createWXAPI(this, MyCodeLibApplication.WXapp_id);
        api.handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
        LogUtil.log.e("----------onNewIntent：");
    }

    @Override
    public void onReq(BaseReq baseReq) {
        LogUtil.log.e("----------onReq：" + baseReq);
    }

    @Override
    public void onResp(BaseResp resp) {
        LogUtil.log.e("----------倒霉的：" + resp);
        switch (resp.errCode) {
            case BaseResp.ErrCode.ERR_OK:
                String code = ((SendAuth.Resp) resp).code; //即为所需的code
                refreshToken(code);
                getToken(code);
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                break;
            case BaseResp.ErrCode.ERR_AUTH_DENIED:
                break;
            default:
                break;
        }
        finish();
    }

    private void refreshToken(String code) {
        String url = "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=" + MyCodeLibApplication.WXapp_id + "&grant_type=refresh_token&refresh_token=REFRESH_TOKEN";
        RequestParams params = RequestParams.newParams()
                .addParameter("appid", MyCodeLibApplication.WXapp_id)
                .addParameter("grant_type", "REFRESH_TOKEN")
                .addParameter("refresh_token", code);
        Request.from(this).getRequestInBG(url, params, null);

    }

    private void getToken(String code) {
        RequestParams params = RequestParams.newParams()
                .addParameter("appid", MyCodeLibApplication.WXapp_id)
                .addParameter("secret", SECRET)
                .addParameter("code", code)
                .addParameter("grant_type", "authorization_code");
        Request.from(this).getRequestInBG(url, params, new IRequestBack() {
            @Override
            public void onSuccess(String json) {
                LogUtil.log.e("登陆成功返回：" + json);
                getUserInfo();
            }

            @Override
            public void onFail(Exception e) {
                LogUtil.log.e("登陆失败");
            }
        });
    }

    private void getUserInfo() {
        String UserInfoUrl = "https://api.weixin.qq.com/sns/userinfo";
        Request.from(this).getRequestInBG(UserInfoUrl, RequestParams.newParams(), new IRequestBack() {
            @Override
            public void onSuccess(String json) {
                LogUtil.log.e("用户信息：" + json);
            }

            @Override
            public void onFail(Exception e) {
                LogUtil.log.e("用户信息失败");
            }
        });
    }
}