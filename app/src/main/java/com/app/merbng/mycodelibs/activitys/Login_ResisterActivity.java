package com.app.merbng.mycodelibs.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.app.merbng.mycodelibs.Constent;
import com.app.merbng.mycodelibs.R;
import com.app.merbng.mycodelibs.base.BaseActivity;
import com.app.merbng.mycodelibs.utils.SharedPrefUtils;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WeiboAuthListener;
import com.sina.weibo.sdk.auth.sso.SsoHandler;
import com.sina.weibo.sdk.exception.WeiboException;
import com.sina.weibo.sdk.net.RequestListener;
import com.sina.weibo.sdk.openapi.UsersAPI;
import com.sina.weibo.sdk.openapi.models.User;
import com.tencent.connect.UserInfo;
import com.tencent.mm.sdk.modelmsg.SendAuth;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 登陆注册页面
 * Created by zx on 2016/7/26.
 */
public class Login_ResisterActivity extends BaseActivity implements View.OnClickListener {
    /*QQ登陆*/
    private Tencent mTencent;
    private IUiListener loginListener;
    private IUiListener userInfoListener;
    private String scope = "get_simple_userinfo";
    private UserInfo userInfo;
    private String accountId;
    /*Sina微博登陆*/
    private SsoHandler mSsoHandler;
    //注意：SsoHandler 仅当 SDK 支持 SSO 时有效
    private AuthInfo mAuthInfo;
    /*微信登陆*/
    private IWXAPI api;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_regisiter);
        fvbId();
    }

    private void fvbId() {
        findViewById(R.id.btn_login).setOnClickListener(this);
        findViewById(R.id.btn_sina).setOnClickListener(this);
        findViewById(R.id.btn_qq).setOnClickListener(this);
        findViewById(R.id.btn_weixin).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                SharedPrefUtils.putBoolean(mContext, "isLogin", true);
                Intent intent = new Intent(Login_ResisterActivity.this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_qq:
                LoginQQ();
                break;
            case R.id.btn_sina:
                sinaLogin();
                break;
            case R.id.btn_weixin:
                WeixinLogin();
                break;
        }
    }

    /**
     * QQ登陆
     */
    private void LoginQQ() {
        mTencent = Tencent.createInstance(Constent.APPID_QQ, mContext);
        loginListener = new IUiListener() {

            @Override
            public void onError(UiError arg0) {
                if (mProgressDialog.isShowing()) {
                    mProgressDialog.dismiss();
                }
            }

            @Override
            public void onComplete(Object value) {
   /*onComplete：{
    "ret": 0,
    "pay_token": "286EEDBE8456C642240206F8A351455B",
    "pf": "desktop_m_qq-10000144-android-2002-",
    "query_authority_cost": 319,
    "authority_cost": 0,
    "openid": "5D4C8F377C1AA60B8B711E2D562DE006",
    "expires_in": 7776000,
    "pfkey": "33431cc32df7b04eaa1af8fc798792fc",
    "msg": "",
    "access_token": "5911B4C0DFABDEE3D86702C821CB1D6A",
    "login_cost": 462
}*/
                try {
                    JSONObject jo = (JSONObject) value;
                    accountId = jo.getString("openid");
                    String access_token = jo.getString("access_token");
                    String expires_in = jo.getString("expires_in");

                    mTencent.setOpenId(accountId);
                    mTencent.setAccessToken(access_token, expires_in);
                    userInfo = new UserInfo(mContext, mTencent.getQQToken());
//                    登陆成功  1、有[项目]帐号----登陆 |  2.无[项目]帐号---注册
/*                    MICreate.otherLogin(accountId, Constant.LOGIN_QQ, new GetCallBack.GetCallBackInterface<MIUser>() {
                        @Override
                        public void onSuccess(MIUser miUser) {
                            if (miUser != null) {//1、有[项目]帐号----登陆
                                otherLoginAfter(miUser);
                            } else {//2.无[项目]帐号---注册
                                userInfo.getUserInfo(userInfoListener);
                            }
                        }

                        @Override
                        public void onFail(String s) {
                            if (mProgressDialog.isShowing()) {
                                mProgressDialog.dismiss();
                            }
                        }
                    });*/

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onCancel() {
                if (mProgressDialog.isShowing()) {
                    mProgressDialog.dismiss();
                }
            }
        };
        userInfoListener = new IUiListener() {

            @Override
            public void onError(UiError arg0) {
                if (mProgressDialog.isShowing()) {
                    mProgressDialog.dismiss();
                }
                show("获取信息失败");
            }

            @Override
            public void onComplete(Object arg0) {
                if (arg0 == null) {
                    return;
                }
                try {
                    final JSONObject jo = (JSONObject) arg0;
                    getUserInfoQQ(jo);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onCancel() {
                if (mProgressDialog.isShowing()) {
                    mProgressDialog.dismiss();
                }
            }
        };
        if (!mTencent.isSessionValid()) {
            if (!mProgressDialog.isShowing()) {
                mProgressDialog.show();
            }
            mTencent.login(this, scope, loginListener);
        }
    }

    /**
     * 获取信息并注册--QQ
     *
     * @param jo
     * @throws JSONException
     */
    private void getUserInfoQQ(JSONObject jo) throws JSONException {
        final String nickName = jo.getString("nickname");
        String heardImgUrl = jo.getString("figureurl_qq_2");
/*        MIQuery.getOtherUserHeardImg(heardImgUrl, new GetCallBack.CallBack() {
            @Override
            public void onSuccess(String fileId) {
                MICreate.otherRegister(accountId, fileId, nickName, Constant.LOGIN_QQ, new GetCallBack.GetCallBackInterface<MIUser>() {
                    @Override
                    public void onSuccess(MIUser miUser) {
                        otherLoginAfter(miUser);
                    }

                    @Override
                    public void onFail(String s) {
                        show("登陆失败");
                    }
                });
            }

            @Override
            public void onFail(String msg) {
                show(msg);
            }
        });*/
    }

    /**
     * Sina微博登陆
     */
    private void sinaLogin() {
        mProgressDialog.show();
        mAuthInfo = new AuthInfo(mContext, Constent.SINA_APP_KEY, Constent.SINA_REDIRECT_URL,Constent. SINA_SCOPE);

        mSsoHandler = new SsoHandler(thisActivity, mAuthInfo);

        mSsoHandler.authorize(new AuthListener());
    }
    class AuthListener implements WeiboAuthListener {

        @Override
        public void onComplete(Bundle bundle) {
            Oauth2AccessToken mAccessToken = Oauth2AccessToken.parseAccessToken(bundle);
            if (mAccessToken.isSessionValid()) {
                String uid = mAccessToken.getUid();
                UsersAPI usersAPI = new UsersAPI(mContext,Constent. SINA_APP_KEY, mAccessToken);
                usersAPI.show(Long.parseLong(uid), new SinaRequestListener());
            }
        }

        //[{_weibo_transaction=1469678549084, access_token=2.00KVLabDYTF2rCed2d9818f3AScNRD, refresh_token=2.00KVLabDYTF2rC4745a399c0nlNQmD, expires_in=2645849, _weibo_appPackage=com.sina.weibo, com.sina.weibo.intent.extra.NICK_NAME=Hi卓月光军, userName=Hi卓月光军, uid=3303746952, com.sina.weibo.intent.extra.USER_ICON=null}]
        @Override
        public void onWeiboException(WeiboException e) {
            e.printStackTrace();
            show("授权失败");
            if (mProgressDialog.isShowing()) {
                mProgressDialog.dismiss();
            }
        }

        @Override
        public void onCancel() {
            if (mProgressDialog.isShowing()) {
                mProgressDialog.dismiss();
            }
        }
    }
    class SinaRequestListener implements RequestListener {

        @Override
        public void onComplete(String response) {
            if (!TextUtils.isEmpty(response)) {
                // 调用 User#parse 将JSON串解析成User对象
                final User user = User.parse(response);
                //  根据uid查询用户，1.存在[项目]帐号，登陆。2.不存在。注册
/*                MICreate.otherLogin(user.id, Constant.LOGIN_SINA, new GetCallBack.GetCallBackInterface<MIUser>() {
                    @Override
                    public void onSuccess(MIUser miUser) {
                        if (miUser != null) {//1、有[项目]帐号----登陆
                            otherLoginAfter(miUser);
                        } else {
                            getUserInfoSina(user);
                        }
                    }

                    @Override
                    public void onFail(String s) {
                        if (mProgressDialog.isShowing()) {
                            mProgressDialog.dismiss();
                        }
                    }
                });*/
            }
        }

        @Override
        public void onWeiboException(WeiboException e) {
            if (mProgressDialog.isShowing()) {
                mProgressDialog.dismiss();
            }
        }
    }

    /**
     * 获取新浪用户信息--注册
     *
     * @param user
     */
    private void getUserInfoSina(final User user) {
/*        MIQuery.getOtherUserHeardImg(user.profile_image_url, new GetCallBack.CallBack() {
            @Override
            public void onSuccess(String fileId) {
                MICreate.otherRegister(user.id, fileId, user.screen_name, Constant.LOGIN_SINA, new GetCallBack.GetCallBackInterface<MIUser>() {
                    @Override
                    public void onSuccess(MIUser miUser) {
                        if (miUser != null) {
                            otherLoginAfter(miUser);
                        }
                    }

                    @Override
                    public void onFail(String s) {
                        if (mProgressDialog.isShowing()) {
                            mProgressDialog.dismiss();
                        }
                        show("登陆失败");
                    }
                });
            }

            @Override
            public void onFail(String msg) {
                show(msg);
                if (mProgressDialog.isShowing()) {
                    mProgressDialog.dismiss();
                }
            }
        });*/
    }
    /**
     * 微信登陆
     */
    private void WeixinLogin() {
        if (api == null) {
            api = WXAPIFactory.createWXAPI(mContext, Constent.WXAPP_ID, false);
            api.registerApp(Constent.WXAPP_ID);
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
    }}
