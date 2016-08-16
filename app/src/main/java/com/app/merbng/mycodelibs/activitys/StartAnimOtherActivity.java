package com.app.merbng.mycodelibs.activitys;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Fade;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.merbng.mycodelibs.R;
import com.app.merbng.mycodelibs.base.BaseActivity;
import com.app.merbng.mycodelibs.utils.AnimUtils;

public class StartAnimOtherActivity extends BaseActivity {
    FloatingActionButton mFabCircle;
    TextView mTVContainer;
    ImageView mIvClose;
    RelativeLayout mRlContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_anim_other);
        fvbId();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setupEnterAnimation(); // 入场动画
            setupExitAnimation(); // 退场动画
        } else {
            initViews();
        }
    }

    private void fvbId() {
        mFabCircle = ((FloatingActionButton) findViewById(R.id.other_fab_circle));
        mTVContainer = ((TextView) findViewById(R.id.other_tv_container));
        mIvClose = ((ImageView) findViewById(R.id.other_iv_close));
        mRlContainer = ((RelativeLayout) findViewById(R.id.other_rl_container));
    }

    // 入场动画
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void setupEnterAnimation() {
        Transition transition = TransitionInflater.from(this)
                .inflateTransition(R.transition.arc_motion);
        getWindow().setSharedElementEnterTransition(transition);
        transition.addListener(new Transition.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {

            }

            @Override
            public void onTransitionEnd(Transition transition) {
                transition.removeListener(this);
                animateRevealShow();
            }

            @Override
            public void onTransitionCancel(Transition transition) {

            }

            @Override
            public void onTransitionPause(Transition transition) {

            }

            @Override
            public void onTransitionResume(Transition transition) {

            }
        });
    }

    // 动画展示
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void animateRevealShow() {
        AnimUtils.animateRevealShow(
                this, mRlContainer,
                mFabCircle.getWidth() / 2, R.color.colorAccent,
                new AnimUtils.OnRevealAnimationListener() {
                    @Override
                    public void onRevealHide() {

                    }

                    @Override
                    public void onRevealShow() {
                        initViews();
                    }
                });
    }

    // 退出动画
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void setupExitAnimation() {
        Fade fade = new Fade();
        getWindow().setReturnTransition(fade);
        fade.setDuration(300);
    }

    // 初始化视图
    private void initViews() {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                Animation animation = AnimationUtils.loadAnimation(mContext, android.R.anim.fade_in);
                animation.setDuration(300);
                mTVContainer.startAnimation(animation);
                mIvClose.setAnimation(animation);
                mTVContainer.setVisibility(View.VISIBLE);
                mIvClose.setVisibility(View.VISIBLE);
            }
        });
    }

    // 退出按钮
    public void backActivity(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            onBackPressed();
        } else {
            defaultBackPressed();
        }
    }

    // 默认回退
    private void defaultBackPressed() {
        super.onBackPressed();
    }
}
