package com.app.merbng.mycodelibs.activitys;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.app.merbng.mycodelibs.R;
import com.app.merbng.mycodelibs.base.BaseActivity;

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

        /*findViewById(R.id.btn_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String, Object> param = new HashMap<>();
                if (edit_input != null) {
                    param.put("userName", edit_input.getText().toString());
                }
                getName(param, new Connection.IConnectListener() {
                    @Override
                    public void onSuccess(String result) {
                        if (tvMsg != null) {
                            tvMsg.setText(result);
                        }
                    }

                    @Override
                    public void onFail(String failMsg) {

                    }

                    @Override
                    public void onException(Exception e) {

                    }
                });
            }
        });*/

    }
/*

    private void getName(HashMap<String, Object> param, final Connection.IConnectListener cb) {

        Connection conn = new Connection.Builder(TestGetConnection.this).setUrl(url).setMethod(Connection.GET).setParams(param).setListener(new Connection.IConnectListener() {
            @Override
            public void onSuccess(String result) {
                cb.onSuccess(result);
            }

            @Override
            public void onFail(String failMsg) {
                cb.onFail(failMsg);
            }

            @Override
            public void onException(Exception e) {
                cb.onException(e);
            }
        }).build();
        ConnectionManager.getInstance().connect(conn);
    }
*/

}
