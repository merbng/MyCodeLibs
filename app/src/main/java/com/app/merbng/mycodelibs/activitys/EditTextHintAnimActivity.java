package com.app.merbng.mycodelibs.activitys;

import android.os.Bundle;
import android.os.Handler;

import com.app.merbng.mycodelibs.R;
import com.app.merbng.mycodelibs.base.BaseActivity;
import com.xiaochendev.lib.HintAnimEditText;

import java.util.ArrayList;
import java.util.List;

public class EditTextHintAnimActivity extends BaseActivity {
    HintAnimEditText mEditText;
    List<CharSequence> data;

    Handler handler = new Handler();

    int mPos = 0;
    private Runnable mRunnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edittext_hint_anim);
        //构造数据
        data = new ArrayList<>();
        data.add("微信");
        data.add("QQ");
        data.add("大众点评");
        data.add("淘宝");
        data.add("暮光之城");


        mEditText = (HintAnimEditText) findViewById(R.id.edittxt);

        //设置初始显示
        mEditText.setHintString("搜索");

        mRunnable = new Runnable() {
            @Override
            public void run() {
                mEditText.changeHintWithAnim(data.get(mPos));
                mPos++;
                if (mPos >= data.size()) {
                    mPos = 0;
                }
                handler.postDelayed(this, 3000);
            }
        };

        handler.postDelayed(mRunnable, 3000);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(mRunnable);
    }
}