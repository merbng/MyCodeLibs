package com.app.merbng.mycodelibs.activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import com.app.merbng.mycodelibs.R;
import com.app.merbng.mycodelibs.base.BaseActivity;

/**
 * 赞放大动画
 */
public class ZanAnimActivity extends BaseActivity {
    private ImageView mDongHua;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zan_anim);

        LayoutInflater inflater = getLayoutInflater();

        mDongHua = (ImageView) findViewById(R.id.donghua);

        mDongHua.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                mDongHua.startAnimation(AnimationUtils.loadAnimation(
                        mContext, R.anim.dianzan_anim));
            }
        });
    }
}
