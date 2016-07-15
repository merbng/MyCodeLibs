package com.app.merbng.mycodelibs.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.merbng.mycodelibs.R;
import com.app.merbng.mycodelibs.base.BaseActivity;
import com.app.merbng.mycodelibs.utils.LogUtil;
import com.squareup.picasso.Picasso;
import com.tencent.connect.UserInfo;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONException;
import org.json.JSONObject;

public class QQLoginActivity extends BaseActivity {

    private static final String APPID = "1105542406";

    private Tencent mTencent;
    private IUiListener loginListener;
    private IUiListener userInfoListener;
    private String scope = "get_simple_userinfo";

    private UserInfo userInfo;
    private TextView tv_nickName, tv_gender;
    private ImageView imgvHeard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qqlogin);
        setupViews();
        initData();
    }


    private void setupViews() {
        tv_nickName = ((TextView) findViewById(R.id.tv_nickName));
        tv_gender = ((TextView) findViewById(R.id.tv_gender));
        imgvHeard = ((ImageView) findViewById(R.id.imgv_heard));
        findViewById(R.id.btn_login_qq).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                loginQQ();
            }
        });
    }

    private void loginQQ() {
        if (!mTencent.isSessionValid()) {
            mTencent.login(QQLoginActivity.this, scope, loginListener);
        }
    }

    private void initData() {
        mTencent = Tencent.createInstance(APPID, mContext);
        loginListener = new IUiListener() {

            @Override
            public void onError(UiError arg0) {
                LogUtil.log.e("onError：");
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
                JSONObject jo = (JSONObject) value;

                try {
                    String openid = jo.getString("openid");
                    String access_token = jo.getString("access_token");
                    String expires_in = jo.getString("expires_in");

                    mTencent.setOpenId(openid);
                    mTencent.setAccessToken(access_token, expires_in);
                    userInfo = new UserInfo(QQLoginActivity.this, mTencent.getQQToken());
                    userInfo.getUserInfo(userInfoListener);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onCancel() {
                LogUtil.log.e("取消登陆：");
            }
        };

        userInfoListener = new IUiListener() {

            @Override
            public void onError(UiError arg0) {
                show("获取信息失败");
            }

            @Override
            public void onComplete(Object arg0) {
                LogUtil.log.e("-----" + arg0.toString());
                if (arg0 == null) {
                    return;
                }
                try {
                    final JSONObject jo = (JSONObject) arg0;

                    int ret = jo.getInt("ret");

                    if (ret == 100030) {
                        //权限不够，需要增量授权
                        Runnable r = new Runnable() {
                            public void run() {
                                mTencent.reAuth(QQLoginActivity.this, "get_simple_userinfo", new IUiListener() {

                                    @Override
                                    public void onError(UiError arg0) {

                                    }

                                    @Override
                                    public void onComplete(Object arg0) {
                                        try {
                                            getUserInfo(jo);
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }

                                    }

                                    @Override
                                    public void onCancel() {

                                    }
                                });
                            }
                        };

                        QQLoginActivity.this.runOnUiThread(r);
                    } else {
                        getUserInfo(jo);
                    }
                } catch (Exception e) {
                    show("异常了-----------------------------。");
                }


            }

            @Override
            public void onCancel() {
                show("取消了，信息");
            }
        };
    }

    private void getUserInfo(JSONObject jo) throws JSONException {
        String nickName = jo.getString("nickname");
        String gender = jo.getString("gender");
        String heardImgUrl = jo.getString("figureurl_qq_2");
        tv_gender.setText("性别：" + gender);
        tv_nickName.setText("昵称：" + nickName);
        Picasso.with(mContext).load(heardImgUrl).into(imgvHeard);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == Constants.REQUEST_LOGIN) {
            LogUtil.log.e("requestCode----：" + requestCode);
            LogUtil.log.e("resultCode：" + resultCode);

            Tencent.handleResultData(data, loginListener);
        }
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == Constants.REQUEST_API) {

        }
    }

    @Override
    protected void onDestroy() {
        if (mTencent != null) {
            mTencent.logout(QQLoginActivity.this);
        }
        super.onDestroy();
    }
}
