package com.app.merbng.mycodelibs.activitys;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.app.merbng.mycodelibs.R;
import com.app.merbng.mycodelibs.base.BaseActivity;
import com.app.merbng.mycodelibs.widget.ButtonView;

public class ButtonViewActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_btn_view);
        ButtonView viewBt = (ButtonView) findViewById(R.id.view_bt);
        viewBt.setOnListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ButtonViewActivity.this, "on Click !", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
