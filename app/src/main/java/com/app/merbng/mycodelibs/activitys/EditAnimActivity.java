package com.app.merbng.mycodelibs.activitys;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.app.merbng.mycodelibs.R;
import com.app.merbng.mycodelibs.base.BaseActivity;
import com.app.merbng.mycodelibs.utils.AppSystemUtils;
import com.app.merbng.mycodelibs.utils.EditAnimUtils;

/**
 * 修改个人资料--编辑框右侧滑出动画
 */
public class EditAnimActivity extends BaseActivity implements View.OnClickListener {
    private FrameLayout layout_root;
    private View layout_name;
    private TextView tv_content;

    private View layout_edit;
    private EditText edt_content;
    private Button btn_ok;

    private int screenWidth;

    private int duration = 300;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_anim);
        fvbId();
        init();
    }

    private void init() {
        screenWidth = AppSystemUtils.getPhoneWidth(thisActivity);
    }

    private void fvbId() {
        layout_root = (FrameLayout) findViewById(R.id.layout_root);
        layout_name = findViewById(R.id.layout_name);
        layout_name.setOnClickListener(this);
        tv_content = (TextView) findViewById(R.id.tv_content);
        layout_edit = View.inflate(this, R.layout.item_edit, null);
        edt_content = (EditText) layout_edit.findViewById(R.id.edt_content);
        btn_ok = (Button) layout_edit.findViewById(R.id.btn_ok);
        btn_ok.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layout_name:
                edt_content.setText(tv_content.getText().toString());
                EditAnimUtils.showEdit(layout_root, layout_edit, layout_name, screenWidth, duration);
                break;
            case R.id.btn_ok:
                tv_content.setText(edt_content.getText().toString());
                EditAnimUtils.dismissEdit(layout_root, layout_edit, screenWidth, duration);
                break;
        }
    }
}
