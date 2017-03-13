package com.app.merbng.mycodelibs.activitys;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.app.merbng.mycodelibs.R;
import com.app.merbng.mycodelibs.base.BaseActivity;
import com.app.merbng.mycodelibs.utils.LogUtil;
import com.dalimao.corelibrary.VerificationCodeInput;

/**仿微信、支付宝等简洁的验证码、密码输入框。
 * 简洁验证码输入框，能自定义输入框个数和样式。
 * VerificationCodeInput
 * https://github.com/liuguangli/VerificationCodeInput
 */
public class VerificationCodeInputActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_verification_code_input);
        VerificationCodeInput input = (VerificationCodeInput) findViewById(R.id.verificationCodeInput);
        input.setOnCompleteListener(new VerificationCodeInput.Listener() {
            @Override
            public void onComplete(String content) {
                LogUtil.log.e("","完成输入：" + content);
            }
        });
    }

}
