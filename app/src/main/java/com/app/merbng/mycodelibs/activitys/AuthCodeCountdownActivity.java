package com.app.merbng.mycodelibs.activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.merbng.mycodelibs.R;
import com.app.merbng.mycodelibs.base.BaseActivity;
import com.app.merbng.mycodelibs.utils.TimeUtils;

/**
 * 验证码倒计时
 */
public class AuthCodeCountdownActivity extends BaseActivity implements View.OnClickListener {

    private LinearLayout ll_getCode;
    private TextView tv_getCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth_code_countdown);

        ll_getCode = (LinearLayout)findViewById(R.id.ll_getCode);
        tv_getCode = (TextView)findViewById(R.id.tv_getCode);
        ll_getCode.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_getCode://获取验证码
                ll_getCode.setClickable(false);
                TimeUtils.timerContent(mContext,tv_getCode,ll_getCode);
                break;
        }
    }
}
