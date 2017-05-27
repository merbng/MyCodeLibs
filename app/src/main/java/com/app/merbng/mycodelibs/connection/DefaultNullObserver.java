package com.app.merbng.mycodelibs.connection;


import com.app.merbng.mycodelibs.beans.ErrorModel;
import com.app.merbng.mycodelibs.utils.LogUtil;

/**
 * Created by ght on 2017/1/13.
 * 一个默认的网络请求回调实现，用来处理完全不关心请求结果的情况
 * 只负责默认的log打印
 *
 * @param <T>
 */

public class DefaultNullObserver<T> extends SCObserver<T> {
    @Override
    public void onError(ErrorModel error, Throwable e) {
        if (error != null) {
            LogUtil.log.e("DefaultNullObserver ->" + error.getMessage());
        } else {
            e.printStackTrace();
        }
    }
    @Override
    public void onNext(T o) {
        if (o != null) {
            LogUtil.log.v("DefaultNullObserver -> Observable<" + o.getClass() + "> success");
        } else {
            LogUtil.log.v("DefaultNullObserver -> unknow Observable success");
        }

    }

    @Override
    public void onComplete() {

    }
}
