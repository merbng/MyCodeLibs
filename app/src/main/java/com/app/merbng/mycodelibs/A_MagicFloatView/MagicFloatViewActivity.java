package com.app.merbng.mycodelibs.A_MagicFloatView;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.app.merbng.mycodelibs.R;
import com.app.merbng.mycodelibs.base.BaseActivity;

import cn.magic.library.MagicFlyLinearLayout;

/**
 * 一个可配置及自定义拓展漂浮路径的迷你版轻量级 MagicFlyLinearLayout 漂浮控件。
 * https://github.com/yanbober/MagicFloatView
 */
public class MagicFloatViewActivity extends BaseActivity implements View.OnClickListener {
    private MagicFlyLinearLayout mRainLinearLayout;
    private Button mRainButton;
    private MagicFlyLinearLayout mFlyLinearLayout;
    private Button mFlyButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_magic_float_view);

        mFlyLinearLayout = (MagicFlyLinearLayout) this.findViewById(R.id.fly_layout);
        mFlyButton = (Button) this.findViewById(R.id.fly_btn);
        mFlyButton.setOnClickListener(this);

        mRainLinearLayout = (MagicFlyLinearLayout) this.findViewById(R.id.gift_layout);
        mRainButton = (Button) this.findViewById(R.id.gift_btn);
        mRainButton.setOnClickListener(this);

        mRainLinearLayout.addDrawable(R.drawable.ebo);
        mRainLinearLayout.addDrawable(R.drawable.ecn);
        mRainLinearLayout.addDrawable(R.drawable.eco);
        mRainLinearLayout.addDrawable(R.drawable.eep);
        mRainLinearLayout.addDrawable(R.drawable.eer);
        mRainLinearLayout.addDrawable(R.drawable.eft);

        mFlyLinearLayout.addDrawable(R.drawable.twitter);
        mFlyLinearLayout.addDrawable(R.drawable.zan);
        mFlyLinearLayout.addDrawable(R.drawable.good);
        mFlyLinearLayout.addDrawable(R.drawable.bad);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fly_btn:
                mFlyLinearLayout.flying();
                break;
            case R.id.gift_btn:
                for (int index=0; index<8; index++) {
                    mRainLinearLayout.flying();
                }
                break;
        }
    }
}
