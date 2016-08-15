package com.app.merbng.mycodelibs.activitys;

import android.os.Bundle;
import android.view.View;
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
public class EditAnimActivity extends BaseActivity {
    private FrameLayout layout_root;
    private View layout_name;
    private TextView tv_content;

    private View layout_edit;
    private EditText edt_content;

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
        tv_content = (TextView) findViewById(R.id.tv_content);
        layout_edit = View.inflate(this, R.layout.item_edit, null);
        edt_content = (EditText) layout_edit.findViewById(R.id.edt_content);
    }

    private void onEditClick(View view) {
        edt_content.setText(tv_content.getText().toString());
        EditAnimUtils.showEdit(layout_root, layout_edit, layout_name, screenWidth, duration);
    }

    private void onConfirmClick(View view) {
        tv_content.setText(edt_content.getText().toString());
        EditAnimUtils.dismissEdit(layout_root, layout_edit, screenWidth, duration);
    }
}
