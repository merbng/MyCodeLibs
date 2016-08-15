package com.app.merbng.mycodelibs.utils;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.view.View;
import android.widget.FrameLayout;

/**
 * Created by merbng on 2016/8/15.
 * 修改资料的编辑框动画
 * 从右边滑出的动画
 */
public class EditAnimUtils {
    /**
     * layout_root = (FrameLayout) findViewById(R.id.layout_root);
     * layout_name = findViewById(R.id.layout_name);
     * tv_content = (TextView) findViewById(R.id.tv_content);
     * layout_edit = View.inflate(this, R.layout.item_edit, null);
     *
     * @param layout_root 根布局
     * @param layout_edit 编辑框布局
     * @param layout_name 原布局
     * @param screenWidth 屏幕宽度
     * @param duration    动画间隔时长
     */
    public static void showEdit(FrameLayout layout_root, View layout_edit, View layout_name, int screenWidth, int duration) {
        layout_root.addView(layout_edit);
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) layout_edit.getLayoutParams();
        params.setMargins(0, layout_name.getTop(), 0, 0);
        PropertyValuesHolder translationX = PropertyValuesHolder.ofFloat("translationX", screenWidth, 0);
        ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(layout_edit, translationX);
        objectAnimator.setDuration(duration);
        objectAnimator.start();
    }

    public static void dismissEdit(final FrameLayout layout_root, final View layout_edit, int screenWidth, int duration) {
        PropertyValuesHolder translationX = PropertyValuesHolder.ofFloat("translationX", 0, screenWidth);
        ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(layout_edit, translationX);
        objectAnimator.setDuration(duration);
        objectAnimator.start();
        objectAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                layout_root.removeView(layout_edit);
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
    }
}
