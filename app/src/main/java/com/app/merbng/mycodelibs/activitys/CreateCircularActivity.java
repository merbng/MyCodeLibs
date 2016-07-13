package com.app.merbng.mycodelibs.activitys;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.LinearLayout;

import com.app.merbng.mycodelibs.R;
import com.app.merbng.mycodelibs.base.BaseActivity;


/**
 * 动画缩放
 */
public class CreateCircularActivity extends BaseActivity {
    Button btnTestScale;
    LinearLayout linearTestScale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createcircular);
        btnTestScale = (Button) findViewById(R.id.btnTestScale);
        linearTestScale = (LinearLayout) findViewById(R.id.linearTestScale);
        btnTestScale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectAnimator revealAnimator = ObjectAnimator.ofFloat( //缩放X 轴的
                        linearTestScale, "scaleX", 0, 200);
                ObjectAnimator revealAnimator1 = ObjectAnimator.ofFloat(//缩放Y 轴的
                        linearTestScale, "scaleY", 0, 200);
                AnimatorSet set = new AnimatorSet();
                set.setDuration(500);//设置播放时间
                set.setInterpolator(new LinearInterpolator());//设置播放模式，这里是平常模式
                set.playTogether(revealAnimator, revealAnimator1);//设置一起播放
                set.start();
            }
        });
    }

}
