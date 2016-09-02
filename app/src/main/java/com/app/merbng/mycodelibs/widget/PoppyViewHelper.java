package com.app.merbng.mycodelibs.widget;

import android.app.Activity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;

import com.nineoldandroids.view.ViewPropertyAnimator;

/**
 * Created by zx on 2016/9/2.
 */
public class PoppyViewHelper {
    public enum PoppyViewPosition {
        TOP, BOTTOM
    };
    private static final int SCROLL_TO_TOP = - 1;
    private static final int SCROLL_TO_BOTTOM = 1;
    private static final int SCROLL_DIRECTION_CHANGE_THRESHOLD = 5;
    private Activity mActivity;
    private LayoutInflater mLayoutInflater;
    private View mPoppyView;
    private int mScrollDirection = 0;
    private int mPoppyViewHeight = - 1;

    private PoppyViewPosition mPoppyViewPosition;
    public PoppyViewHelper(Activity activity, PoppyViewPosition position) {
        mActivity = activity;
        mLayoutInflater = LayoutInflater.from(activity);
        mPoppyViewPosition = position;
    }
    public PoppyViewHelper(Activity activity) {
        this(activity, PoppyViewPosition.BOTTOM);
    }

    private ViewGroup viewGroup;
    // for scrollview
    private  NotifyingScrollView scrollView;
    public ViewGroup createPoppyViewOnScrollView(NotifyingScrollView scrollViewId, View poppyViewResId) {
        mPoppyView =poppyViewResId;
        scrollView = scrollViewId;
        initPoppyViewOnScrollView();
        return viewGroup;
    }
    // for ListView
    public View createPoppyViewOnListView(int listViewId, int poppyViewResId, AbsListView.OnScrollListener onScrollListener) {
        final ListView listView = (ListView)mActivity.findViewById(listViewId);
        if(listView.getHeaderViewsCount() != 0) {
            throw new IllegalArgumentException("use createPoppyViewOnListView with headerResId parameter");
        }
        if(listView.getFooterViewsCount() != 0) {
            throw new IllegalArgumentException("poppyview library doesn't support listview with footer");
        }
        mPoppyView = mLayoutInflater.inflate(poppyViewResId, null);
        initPoppyViewOnListView(listView, onScrollListener);
        return mPoppyView;
    }
    public View createPoppyViewOnListView(int listViewId, int poppyViewResId) {
        return createPoppyViewOnListView(listViewId, poppyViewResId, null);
    }
    // common
    private void setPoppyViewOnView(View view) {
        ViewGroup.LayoutParams lp = view.getLayoutParams();
        ViewParent parent = view.getParent();
        ViewGroup group = (ViewGroup)parent;
        int index = group.indexOfChild(view);
        final FrameLayout newContainer = new FrameLayout(mActivity);
        group.removeView(view);
        group.addView(newContainer, index, lp);
        newContainer.addView(view);
        final FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = mPoppyViewPosition == PoppyViewPosition.BOTTOM ? Gravity.BOTTOM : Gravity.TOP;
        newContainer.addView(mPoppyView, layoutParams);
        group.invalidate();
        viewGroup=group;
    }
    private boolean isjixu=false;
    private void onScrollPositionChanged(int oldScrollPosition, int newScrollPosition) {
        int newScrollDirection;
        System.out.println(oldScrollPosition + " ->" + newScrollPosition);
        if(newScrollPosition < oldScrollPosition) {
            newScrollDirection = SCROLL_TO_TOP;
            isjixu=true;
            //gundong滚动dao到dingb顶部
        } else {
            newScrollDirection = SCROLL_TO_BOTTOM;
            isjixu=false;
        }
        if(newScrollDirection != mScrollDirection) {
            mScrollDirection = newScrollDirection;
            translateYPoppyView();
        }
    }
    private     int translationY = 0;
    private boolean isdingbu=false;
    private void translateYPoppyView() {

        mPoppyView.post(new Runnable() {
            @Override
            public void run() {
                if(mPoppyViewHeight <= 0) {
                    mPoppyViewHeight = mPoppyView.getHeight();
                }


                switch (mPoppyViewPosition) {
                    case BOTTOM:
                        translationY = mScrollDirection == SCROLL_TO_TOP ? 0 : mPoppyViewHeight;

                        break;
                    case TOP:
                        translationY = mScrollDirection == SCROLL_TO_TOP ?  0:  -mPoppyViewHeight;
                        isdingbu=true;
                        break;
                }
                ViewPropertyAnimator.animate(mPoppyView).setDuration(300).translationY(translationY);
            }
        });
        if(isdingbu){
            scrollView.post(new Runnable() {
                @Override
                public void run() {
//                    如果为顶部显示就让view平移Mpoppyviewhiegh高度
                    ViewPropertyAnimator.animate(scrollView).setDuration(300).translationY(mPoppyViewHeight);
                    isdingbu=false;
                }
            });

        }else{
            scrollView.post(new Runnable() {
                @Override
                public void run() {
//                    不然就让他显示在屏幕顶部
                    ViewPropertyAnimator.animate(scrollView).setDuration(300).translationY(0);
                }
            });

        }

    }
    // for ScrollView
    private void initPoppyViewOnScrollView() {
        setPoppyViewOnView(scrollView);
        scrollView.setOnScrollChangedListener(new NotifyingScrollView.OnScrollChangedListener() {
            int mScrollPosition;
            public void onScrollChanged(ScrollView who, int l, int t, int oldl, int oldt) {
                if(Math.abs(t - mScrollPosition) >= SCROLL_DIRECTION_CHANGE_THRESHOLD) {
                    onScrollPositionChanged(mScrollPosition, t);
                }

                mScrollPosition = t;
            }
        });
    }
    // for ListView
    private void initPoppyViewOnListView(ListView listView, final AbsListView.OnScrollListener onScrollListener) {
        setPoppyViewOnView(listView);
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            int mScrollPosition;
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if(onScrollListener != null) {
                    onScrollListener.onScroll(view, firstVisibleItem, visibleItemCount, totalItemCount);
                }
                View topChild = view.getChildAt(0);
                int newScrollPosition = 0;
                if(topChild == null) {
                    newScrollPosition = 0;
                } else {
                    newScrollPosition = - topChild.getTop() + view.getFirstVisiblePosition() * topChild.getHeight();
                }
                if(Math.abs(newScrollPosition - mScrollPosition) >= SCROLL_DIRECTION_CHANGE_THRESHOLD) {
                    onScrollPositionChanged(mScrollPosition, newScrollPosition);
                }
                mScrollPosition = newScrollPosition;
            }
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if(onScrollListener != null) {
                    onScrollListener.onScrollStateChanged(view, scrollState);
                }
            }
        });
    }
}