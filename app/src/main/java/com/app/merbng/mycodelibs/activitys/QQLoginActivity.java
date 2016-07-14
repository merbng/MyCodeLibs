package com.app.merbng.mycodelibs.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.app.merbng.mycodelibs.R;
import com.app.merbng.mycodelibs.base.BaseActivity;
import com.tencent.connect.UserInfo;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONObject;

public class QQLoginActivity extends BaseActivity {

    private static final String APPID = "1105542406";

    private Tencent mTencent;
    private IUiListener loginListener;
    private IUiListener userInfoListener;
    private String scope;

    private UserInfo userInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qqlogin);
        setupViews();
        initData();
    }

    @Override
    protected void onDestroy() {
        if (mTencent != null) {
            mTencent.logout(QQLoginActivity.this);
        }
        super.onDestroy();
    }

    private void setupViews() {

        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                login();
            }
        });

        findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Log.e("开始获取用户信息", "");
                if (mTencent.getQQToken() == null) {
                    Log.e("qqtoken == null", "");
                }
                userInfo = new UserInfo(QQLoginActivity.this, mTencent.getQQToken());
                userInfo.getUserInfo(userInfoListener);
            }
        });
    }

    private void initData() {
        mTencent = Tencent.createInstance(APPID, mContext);
        Log.e("-----肯定：", mTencent.toString());
        //要所有权限，不用再次申请增量权限，这里不要设置成get_user_info,add_t
        scope = "get_simple_userinfo";
        loginListener = new IUiListener() {

            @Override
            public void onError(UiError arg0) {
                show("登陆错误");
            }

            @Override
            public void onComplete(Object value) {
                show("登陆完成" + value);
                if (value == null) {
                    return;
                }

                try {
                    JSONObject jo = (JSONObject) value;

                    String msg = jo.getString("msg");

                    Log.e("json=", String.valueOf(jo));

                    show(msg);
                    if ("sucess".equals(msg)) {
                        show("登录成功");
                        String openID = jo.getString("openid");
                        String accessToken = jo.getString("access_token");
                        String expires = jo.getString("expires_in");
                        mTencent.setOpenId(openID);
                        mTencent.setAccessToken(accessToken, expires);
                    }

                } catch (Exception e) {
                    show("异常");
                }
            }

            @Override
            public void onCancel() {
                show("取消了");
            }
        };

        userInfoListener = new IUiListener() {

            @Override
            public void onError(UiError arg0) {
                show("获取信息失败");
            }

            @Override
            public void onComplete(Object arg0) {
                show(arg0.toString());
                if (arg0 == null) {
                    return;
                }
                try {
                    JSONObject jo = (JSONObject) arg0;
                    int ret = jo.getInt("ret");
                    show(jo.toString() + "");
                    if (ret == 100030) {
                        //权限不够，需要增量授权
                        Runnable r = new Runnable() {
                            public void run() {
                                mTencent.reAuth(QQLoginActivity.this, "get_simple_userinfo", new IUiListener() {

                                    @Override
                                    public void onError(UiError arg0) {
                                        // TODO Auto-generated method stub

                                    }

                                    @Override
                                    public void onComplete(Object arg0) {
                                        // TODO Auto-generated method stub

                                    }

                                    @Override
                                    public void onCancel() {
                                        // TODO Auto-generated method stub

                                    }
                                });
                            }
                        };

                        QQLoginActivity.this.runOnUiThread(r);
                    } else {
                        String nickName = jo.getString("nickname");
                        String gender = jo.getString("gender");
                        Toast.makeText(QQLoginActivity.this, "你好，" + nickName + "性别：" + gender, Toast.LENGTH_LONG).show();
                    }

                } catch (Exception e) {
                    show("异常了。");
                }


            }

            @Override
            public void onCancel() {
                show("取消了，信息");
            }
        };
    }

    private void login() {
        if (!mTencent.isSessionValid()) {
            mTencent.login(QQLoginActivity.this, scope, loginListener);
            show("登陆");
        } else {
            show("否则！！！");
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Constants.REQUEST_API) {
            if (resultCode == Constants.REQUEST_LOGIN) {
                Log.e("得到了。result", "");
                Tencent.handleResultData(data, loginListener);
            }
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

}
