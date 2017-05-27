package com.app.merbng.mycodelibs.base;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.app.merbng.mycodelibs.R;
import com.app.merbng.mycodelibs.beans.ErrorModel;
import com.app.merbng.mycodelibs.connection.SCObserver;
import com.app.merbng.mycodelibs.utils.AppRefreshUtils;
import com.app.merbng.mycodelibs.utils.LogUtil;

import java.util.List;


/**
 * Created by ght on 2017/2/17.
 * 显示一个列表的fragment
 * 需要提供一个BaseListProvider自动网络加载翻页
 * 和一个RecyclerView.Adapter来控制显示效果
 *
 * @param <T> 列表的数据格式List中的泛型类型
 */

public abstract class BaseListFragment<T> extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {
    private SwipeRefreshLayout refreshLayout;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private BaseListProvider<T> provider;
    private FrameLayout layoutContenter;
    private RecyclerView.OnScrollListener onScrollListener;

    /**
     * @return 适配器
     */
    @NonNull
    protected abstract RecyclerView.Adapter createAdapter();

    /**
     * @return 获取适配器
     */
    protected final RecyclerView.Adapter getAdapter() {
        if (adapter == null) {
            adapter = createAdapter();
        }
        return adapter;
    }

    /**
     * 获取回调方法.
     * 已经有默认的实现
     * 需要调整时可以覆写这个方法
     *
     * @return 默认的实现 show出错误信息 加载完成时刷新适配器 没有数据库操作
     */
    protected SCObserver<List<T>> getSubscriber() {
        return new SCObserver<List<T>>() {
            @Override
            public void onStart() {
                super.onStart();
                refreshLayout.setRefreshing(true);
            }

            @Override
            public void onError(ErrorModel error, Throwable e) {
                onLoadError(error, e);

            }

            @Override
            public void onComplete() {
                refreshLayout.setRefreshing(false);
            }

            @Override
            public void onNext(List<T>o) {
                LogUtil.log.d("onNext");
                getAdapter().notifyDataSetChanged();
            }
        };
    }

    /**
     * 网络请求错误时回调.
     *
     * @param error 服务器错误
     * @param e     其他错误
     */
    protected void onLoadError(ErrorModel error, Throwable e) {
        if (error != null) {
            show(error.getMessage());
        } else {
            show(e.getMessage());
            LogUtil.log.e(e.getMessage());
        }
        refreshLayout.setRefreshing(false);
    }

    /**
     * @return 数据提供者
     */
    @NonNull
    protected abstract BaseListProvider<T> createProvider();

    /**
     * @return 数据提供者
     */
    @NonNull
    protected final BaseListProvider getProvider() {
        if (provider == null) {
            provider = createProvider();
            provider.setSubscriber(getSubscriber());
        }
        return provider;
    }

    /**
     * @return 获取列表view
     */
    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    /**
     * @return 获取刷新布局.
     */
    public SwipeRefreshLayout getRefreshLayout() {
        return refreshLayout;
    }

    @NonNull
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup
            container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_base_list, container, false);
    }

    @Override
    @CallSuper
    public void fvb(View mView) {

        layoutContenter = (FrameLayout) mView.findViewById(R.id.base_list_container);
        recyclerView = (RecyclerView) mView.findViewById(R.id.list_layout);
        refreshLayout = (SwipeRefreshLayout) mView.findViewById(R.id.refresh_layout);
    }
    public void setOnScollListener(RecyclerView.OnScrollListener onScrollListener){
           this.onScrollListener=onScrollListener;
    }

    @Override
    @CallSuper
    public void init() {
        AppRefreshUtils.initRefresh(getContext(), refreshLayout);
        refreshLayout.setOnRefreshListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(getAdapter());
        if (onScrollListener!=null){
            getRecyclerView().addOnScrollListener(onScrollListener);
        }
        getProvider().bindRecyclerView(recyclerView);
        getProvider().loadData(true);
    }

    @Override
    @CallSuper
    public void onRefresh() {
        getProvider().refresh(getContext());
    }

    public FrameLayout getLayoutContenter() {
        return layoutContenter;
    }
}
