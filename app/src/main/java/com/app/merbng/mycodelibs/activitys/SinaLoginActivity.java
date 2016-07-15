package com.app.merbng.mycodelibs.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.merbng.mycodelibs.Login.Constants;
import com.app.merbng.mycodelibs.R;
import com.app.merbng.mycodelibs.base.BaseActivity;
import com.app.merbng.mycodelibs.utils.LogUtil;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WeiboAuthListener;
import com.sina.weibo.sdk.auth.sso.SsoHandler;
import com.sina.weibo.sdk.exception.WeiboException;
import com.sina.weibo.sdk.net.RequestListener;
import com.sina.weibo.sdk.openapi.UsersAPI;
import com.sina.weibo.sdk.openapi.models.User;
import com.squareup.picasso.Picasso;

import org.json.JSONException;

/**
 * 新浪微博登陆
 */
public class SinaLoginActivity extends BaseActivity {
    //注册成功之后的APPKEY
    public static final String SINA_APPKEY = "2651314935";
    //注册成功之后的REDIRECT_URL
    public static final String SINA_REDIRECT_URL = "https://api.weibo.com/oauth2/default.html";
    public static final String SINA_SCOPE = "all";
    /**
     * 注意：SsoHandler 仅当 SDK 支持 SSO 时有效
     */
    private AuthInfo mAuthInfo;
    private SsoHandler mSsoHandler;
    private TextView tv_nickName, tv_gender;
    private ImageView imgvHeard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sina_login);
        initViews();
        initData();
    }

    private void initViews() {
        tv_nickName = ((TextView) findViewById(R.id.tv_nickName));
        tv_gender = ((TextView) findViewById(R.id.tv_gender));
        imgvHeard = ((ImageView) findViewById(R.id.imgv_heard));
        findViewById(R.id.btn_login_sina).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sinaLogin();
            }
        });
    }

    private void sinaLogin() {
        mAuthInfo = new AuthInfo(mContext, SINA_APPKEY, SINA_REDIRECT_URL, SINA_SCOPE);
        mSsoHandler = new SsoHandler(this, mAuthInfo);
        mSsoHandler.authorize(new AuthListener());
    }

    private void initData() {
        mAuthInfo = new AuthInfo(this, Constants.APP_KEY, Constants.REDIRECT_URL, Constants.SCOPE);
    }

    class AuthListener implements WeiboAuthListener {

        @Override
        public void onComplete(Bundle bundle) {
            Oauth2AccessToken mAccessToken = Oauth2AccessToken.parseAccessToken(bundle);
            if (mAccessToken.isSessionValid()) {
                String uid = mAccessToken.getUid();
                UsersAPI usersAPI = new UsersAPI(mContext, SINA_APPKEY, mAccessToken);
                usersAPI.show(uid, new SinaRequestListener());
            }
        }

        @Override
        public void onWeiboException(WeiboException e) {

        }

        @Override
        public void onCancel() {

        }
    }

    class SinaRequestListener implements RequestListener {

        @Override
        public void onComplete(String response) {
            if (!TextUtils.isEmpty(response)) {
                // 调用 User#parse 将JSON串解析成User对象
                try {
                    User user = User.parse(response);
                    getUserInfo(user);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }

        @Override
        public void onWeiboException(WeiboException e) {
            LogUtil.log.e("新浪微博e:" + e);
        }
    }

    private void getUserInfo(User user) throws JSONException {
        String nickName = user.name;
        String gender = user.gender;
        String heardImgUrl = user.avatar_large;
        tv_gender.setText("性别：" + gender);
        tv_nickName.setText("昵称：" + nickName);
        Picasso.with(mContext).load(heardImgUrl).into(imgvHeard);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (mSsoHandler != null) {
            mSsoHandler.authorizeCallBack(requestCode, resultCode, data);
        }
    }
}
