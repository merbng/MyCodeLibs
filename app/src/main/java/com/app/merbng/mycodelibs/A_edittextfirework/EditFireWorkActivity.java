package com.app.merbng.mycodelibs.A_edittextfirework;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.merbng.mycodelibs.R;
import com.app.merbng.mycodelibs.base.BaseActivity;

/**
 * 输入框动画
 * https://github.com/covetcode/EditTextFirework-Demo
 */
public class EditFireWorkActivity extends BaseActivity {
    private EditText mEditText;
    private FireworkView mFireworkView;
    private RelativeLayout layout;
    private TextView day;
    private TextView night;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_fire_work);
        layout = (RelativeLayout) findViewById(R.id.layout);
        day = (TextView) findViewById(R.id.day);
        day.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout.setBackgroundColor(0xFFFFFFFF);
            }
        });
        night = (TextView) findViewById(R.id.night);
        night.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout.setBackgroundColor(0xFF000000);
            }
        });
        mEditText = (EditText) findViewById(R.id.edit_text);


        mFireworkView = (FireworkView) findViewById(R.id.fire_work);
        mFireworkView.bindEditText(mEditText);


    }
}
