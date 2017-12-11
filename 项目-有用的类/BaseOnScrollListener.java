package com.mayi.douyu.widget.help;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * RecycleView加载下一页
 *
 * mRecycleView.addOnScrollListener(new BaseOnScrollListener() {
 * @Override
 * public void onLoadMore() {
 * mPresenter.getListData(false);
 * mLiveAdapter.setLoading(true);
 * }
 * });
 *
 */

public abstract class BaseOnScrollListener extends RecyclerView.OnScrollListener {
    protected int lastVisibleItem;

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        lastVisibleItem = ((LinearLayoutManager)recyclerView.getLayoutManager()).findLastVisibleItemPosition();

    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        if (newState == RecyclerView.SCROLL_STATE_IDLE
                && lastVisibleItem + 1 == recyclerView.getAdapter().getItemCount()) {
            onLoadMore();
        }
    }

    public abstract void onLoadMore();
}
