package com.app.merbng.mycodelibs.activitys;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.app.merbng.mycodelibs.R;
import com.app.merbng.mycodelibs.base.BaseActivity;
import com.app.merbng.mycodelibs.base.IRequestBack;
import com.app.merbng.mycodelibs.base.Request;
import com.app.merbng.mycodelibs.base.RequestParams;

/**
 * 测试Get请求
 */
public class TestGetConnection extends BaseActivity {
    private String url = "http://api.taikongdan.com/1.0/users/checkUserName";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        final TextView tvMsg = (TextView) findViewById(R.id.tv_msg);
        final EditText edit_input = (EditText) findViewById(R.id.edit_input);

        findViewById(R.id.btn_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestParams params = RequestParams.newParams().addParameter("userName", edit_input.getText().toString());
                Request.from(TestGetConnection.this).getRequestInBG(url, params, new IRequestBack() {
                    @Override
                    public void onSuccess(String json) {
                        tvMsg.setText(json);
                    }

                    @Override
                    public void onFail(Exception e) {
                        tvMsg.setText(e.getMessage());
                    }
                });

            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Request.from(this).cancelRequest();
    }
}
