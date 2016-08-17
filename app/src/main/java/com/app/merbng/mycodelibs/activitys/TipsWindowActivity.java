package com.app.merbng.mycodelibs.activitys;

import android.animation.IntEvaluator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.app.merbng.mycodelibs.R;
import com.app.merbng.mycodelibs.base.BaseActivity;

public class TipsWindowActivity extends BaseActivity {
    PopupWindow popWindow;
    LinearLayout ll;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clicktips);
        ll = (LinearLayout)findViewById(R.id.ll);
        final View popView = LayoutInflater.from(mContext).inflate(R.layout.activity_tips_window, null);
        popWindow = new PopupWindow(popView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popWindow.setAnimationStyle(R.style.popwindowStyle);
        popWindow.setBackgroundDrawable(new BitmapDrawable());

        findViewById(R.id.btn_clear).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                popView.setOnTouchListener(new View.OnTouchListener() {

                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (popWindow != null && popWindow.isShowing()) {
                            popWindow.dismiss();
                        }
                        return false;
                    }
                });
                popWindow.showAtLocation(ll, Gravity.BOTTOM,0,0);
                setHuise(true);
            }
        });
        popWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                setHuise(false);
            }
        });
    }

    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha
     */
    public void backgroundAlpha(float bgAlpha, Activity activity) {
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        activity.getWindow().setAttributes(lp);
    }

    private void setHuise(final boolean isShow) {
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 100);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                IntEvaluator evaluator = new IntEvaluator();
                float fragtion = (float) animation.getAnimatedValue() / 100f;
                float current = 0;
                if (isShow) {
                    current = evaluator.evaluate(fragtion, 100, 60) /100f;
                }else {
                    current = evaluator.evaluate(fragtion, 60, 100) /100f;
                }
                backgroundAlpha(current, TipsWindowActivity.this);
            }
        });
        valueAnimator.setDuration(500);
        valueAnimator.start();
    }
}
