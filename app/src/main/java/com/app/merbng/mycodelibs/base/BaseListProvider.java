package com.app.merbng.mycodelibs.base;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

/**
 * Created by ght on 2016/12/27.
 * 提供数据数组的数据提供者
 *
 * @param <E>
 */
public abstract class BaseListProvider<E> extends BaseProvider<List<E>> {
    private static final int LOAD_DATA_FROM_LAST_ITEM_COUNT = 2; //从最后的第几个item开始触发加载下一页
    /**
     * 标示 是否已经是最后数据 不会再翻页.
     */
    private boolean isEnd = false;

    /**
     * 是否已经是最后数据 不会再翻页.
     *
     * @return 是否已经是最后数据
     */
    public boolean isEnd() {
        return isEnd;
    }

    /**
     * 是否已经是最后数据 不会再翻页.
     *
     * @param end 是否已经是最后数据
     */
    public void setEnd(boolean end) {
        isEnd = end;
    }

    /**
     * 返回数组大小.
     *
     * @return 数组长度
     */
    public abstract int getItemCount();

    /**
     * 翻页加载下一页的方法.
     */
    public abstract void loadNext();

    /**
     * 从数组中移除一项.
     *
     * @param position 移除位置
     */
    public abstract void removeItem(int position);

    /**
     * 获取某个一项.
     *
     * @param position 位置
     * @return 数据
     */
    public abstract E getItem(int position);


    /**
     * 只支持LinearLayoutManager.
     * 给RecyclerView添加翻页的监听
     *
     * @param recyclerView 列表
     */
    public void bindRecyclerView(RecyclerView recyclerView) {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {
                    LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
                    if (manager.findLastVisibleItemPosition() > manager.getItemCount() - LOAD_DATA_FROM_LAST_ITEM_COUNT
                            && !isEnd() && !isLoading()) {
                        loadNext();
                    }
                }
            }
        });
    }

}
