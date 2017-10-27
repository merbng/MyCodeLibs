package com.app.netconnection.demo;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.TextView;

import com.app.a1merbng.tempdemo.R;
import com.app.netconnection.customView.LineView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 仿微信录小视频 倒计进度条
 * http://blog.devwiki.net/index.php/2015/11/11/Android-WeiXin-Line-Timer-View.html
 */
public class LineViewActivity extends AppCompatActivity {
    @BindView(R.id.lineView)
    LineView lineView;
    @BindView(R.id.button)
    Button button;
    @BindView(R.id.button_repeat)
    Button buttonRepeat;
    @BindView(R.id.tv_result)
    TextView tvResult;
    private ObjectAnimator animator;
    private int width;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_view);
        ButterKnife.bind(this);
        ViewTreeObserver observer = lineView.getViewTreeObserver();
        observer.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                width = lineView.getMeasuredWidth();
                return true;
            }
        });
    }

    @OnClick({R.id.button_repeat,R.id.button})
    void click(View view) {
        if (view.getId()==R.id.button){
        startAnimator();
        }else if (view.getId()==R.id.button_repeat){
            width=720;
            animator.reverse();
        }

    }

    private void startAnimator() {
        animator = ObjectAnimator.ofInt(lineView, "layoutWidth", width, 0);
        animator.setDuration(6000);
        animator.setInterpolator(new LinearInterpolator());
        animator.start();
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                tvResult.setText("动画开始");
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                tvResult.setText("动画结束");
            }

            @Override
            public void onAnimationCancel(Animator animator) {
                tvResult.setText("动画取消");
            }

            @Override
            public void onAnimationRepeat(Animator animator) {
                tvResult.setText("动画Repeat");
            }
        });
    }
}
