package com.app.merbng.mycodelibs.base;

import android.content.Context;

import com.app.merbng.mycodelibs.connection.SCObserver;


/**
 * Created by ght on 2016/12/27.
 * 一个基础的数据提供者，用来从网络获取beanObject对象并且提供回调 分离网络请求与视图
 *
 * @param <T>
 */
public abstract class BaseProvider<T> {
    /**
     * 状态标识 是否正在加载.
     */
    private boolean isLoading = false;
    /**
     * 一个默认回调接口 用来获取网络请求的事件回调.
     */
    private SCObserver<T> observer;

    /**
     * 设置加载状态标识.
     *
     * @param loading 是否正在加载
     */
    public void setLoading(boolean loading) {
        isLoading = loading;
    }

    /**
     * 得到观察者.
     *
     * @return 得到观察者.
     */
    public SCObserver<T> getSubscriber() {
        return observer;
    }

    /**
     * 设置数据获取后的回调监听.
     *
     * @param observer 观察者 用来获取网络请求的事件回调.
     */
    public void setSubscriber(SCObserver<T> observer) {
        this.observer = observer;
    }

    /**
     * 是否正在加载.
     *
     * @return 是否正在加载.
     */
    public boolean isLoading() {
        return isLoading;
    }

    /**
     * 获取加载到的本地数据.
     *
     * @return 数据
     */
    public abstract T getData();

    /**
     * 需要子类自己实现的获取数据的方法.
     *
     * @param needForce 是否是强制刷新的
     */
    public abstract void loadData(boolean needForce);

    /**
     * 刷新数据的方法.
     *
     * @param context 上下文
     */
    public abstract void refresh(Context context);

}

