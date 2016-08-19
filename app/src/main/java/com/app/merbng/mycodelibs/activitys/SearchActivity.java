package com.app.merbng.mycodelibs.activitys;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;

import com.app.merbng.mycodelibs.R;
import com.app.merbng.mycodelibs.base.BaseActivity;
import com.app.merbng.mycodelibs.utils.DensityUtil;
import com.nineoldandroids.animation.ObjectAnimator;

import io.codetail.animation.SupportAnimator;
import io.codetail.animation.ViewAnimationUtils;

/**
 * 搜索动画
 */
public class SearchActivity extends BaseActivity {
    private SupportAnimator mAnimator;
    private ImageView iv_bottom_search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        final CardView cardView = (CardView) this.findViewById(R.id.card_search);
        cardView.setVisibility(View.INVISIBLE);
        final FloatingActionButton floatingActionButton = (FloatingActionButton) this.findViewById(R.id.floatingActionButton);
        iv_bottom_search = (ImageView) this.findViewById(R.id.iv_bottom_search);
        iv_bottom_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mAnimator != null && !mAnimator.isRunning()) {
                    mAnimator = mAnimator.reverse();
                    float curTranslationX = iv_bottom_search.getTranslationX();
                    final ObjectAnimator animator = ObjectAnimator.ofFloat(iv_bottom_search, "translationX", curTranslationX, 0);
                    animator.setDuration(1000);
                    mAnimator.addListener(new SupportAnimator.AnimatorListener() {
                        @Override
                        public void onAnimationStart() {
                            animator.start();
                        }

                        @Override
                        public void onAnimationEnd() {
                            mAnimator = null;
                            floatingActionButton.setVisibility(View.VISIBLE);
                            cardView.setVisibility(View.GONE);
                        }

                        @Override
                        public void onAnimationCancel() {

                        }

                        @Override
                        public void onAnimationRepeat() {

                        }
                    });
                } else if (mAnimator != null) {
                    mAnimator.cancel();
                    return;
                } else {
                    int cx = cardView.getRight();
                    int cy = cardView.getBottom();
                    float curTranslationX = iv_bottom_search.getTranslationX();
                    final ObjectAnimator animator = ObjectAnimator.ofFloat(iv_bottom_search, "translationX", curTranslationX, cx / 2 - DensityUtil.dip2px(SearchActivity.this, 24));
                    animator.setDuration(1000);
                    float radius = r(cardView.getWidth(), cardView.getHeight());
                    mAnimator = ViewAnimationUtils.createCircularReveal(cardView, cx / 2, cy - DensityUtil.dip2px(SearchActivity.this, 32), DensityUtil.dip2px(SearchActivity.this, 20), radius);
                    mAnimator.addListener(new SupportAnimator.AnimatorListener() {
                        @Override
                        public void onAnimationStart() {
                            animator.start();


                        }

                        @Override
                        public void onAnimationEnd() {

                        }

                        @Override
                        public void onAnimationCancel() {

                        }

                        @Override
                        public void onAnimationRepeat() {

                        }
                    });
                }

                mAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
                mAnimator.setDuration(1000);
                mAnimator.start();
            }
        });
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardView.setVisibility(View.VISIBLE);
                floatingActionButton.setVisibility(View.GONE);
                iv_bottom_search.performClick();
            }
        });
    }

    static float r(int a, int b) {
        return (float) Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));
    }

}
