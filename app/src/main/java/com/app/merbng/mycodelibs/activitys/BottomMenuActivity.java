package com.app.merbng.mycodelibs.activitys;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebView;
import android.widget.Button;

import com.app.merbng.mycodelibs.R;
import com.app.merbng.mycodelibs.base.BaseActivity;

/**
 * 底部菜单  滚动隐藏，上滑显示
 */
public class BottomMenuActivity extends BaseActivity {

    WebView lv;
    Button ll_detail_bottom;

    Animation up, down, header_down;

    final int MIN_DINTANCE_MODELY = 75;
    final int MIN_DINTANCE_ORDERY = 150;

    LeftGesture leftGesture;
    GestureDetector mGestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_menu);
        lv = (WebView) findViewById(R.id.listView1);
        ll_detail_bottom = (Button) findViewById(R.id.button1);
        lv.loadUrl("http://music.163.com/#/song?id=421148956");
        up = AnimationUtils.loadAnimation(mContext, R.anim.anim_web_bottom_up);
        down = AnimationUtils.loadAnimation(mContext, R.anim.anim_web_bottom_down);
        lv.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                mGestureDetector.onTouchEvent(arg1);
                return false;
            }
        });
        leftGesture = new LeftGesture();// 手势监听类
        mGestureDetector = new GestureDetector(this, leftGesture);
    }

    class LeftGesture extends SimpleOnGestureListener {
        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            if (e1 != null && e2 != null)
                if ((e1.getY() - e2.getY()) > MIN_DINTANCE_MODELY) {
                    // 向上滑动
                    if ((e1.getY() - e2.getY()) > MIN_DINTANCE_ORDERY) {
                        if (ll_detail_bottom.getVisibility() == View.VISIBLE) {
                            ll_detail_bottom.startAnimation(down);
                            ll_detail_bottom.setVisibility(View.GONE);
                        }
                    }
                } else if ((e2.getY() - e1.getY()) > MIN_DINTANCE_MODELY) {
                    // 向下滑动
                    if ((e2.getY() - e1.getY()) > MIN_DINTANCE_ORDERY) {
                        if (ll_detail_bottom.getVisibility() == View.GONE) {
                            ll_detail_bottom.startAnimation(up);
                            ll_detail_bottom.setVisibility(View.VISIBLE);
                        }
                    }
                }
            return super.onScroll(e1, e2, distanceX, distanceY);
        }
    }
}
