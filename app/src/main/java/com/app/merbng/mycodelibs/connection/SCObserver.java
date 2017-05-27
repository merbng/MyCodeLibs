package com.app.merbng.mycodelibs.connection;


import com.app.merbng.mycodelibs.beans.ErrorModel;
import com.app.merbng.mycodelibs.utils.LogUtil;
import com.app.merbng.mycodelibs.utils.ModelUtil;

import java.io.IOException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;


/**
 * Created by ght on 2016/12/21.
 */

public abstract class SCObserver<T> implements Observer<T> {

    @Override
    public void onSubscribe(Disposable d) {
        onStart();
    }

    public void onStart() {
    }

    @Override
    public final void onError(Throwable e) {

        if (e instanceof HttpException) {
            try {
                String s = ((HttpException) e).response().errorBody().string();
                LogUtil.log.e("---", s);
                ErrorModel error = ModelUtil.getBean(s, ErrorModel.class);
                onError(error, e);
            } catch (IOException e1) {
                onError(null, e1);
            }
        } else {
            onError(null, e);
        }
    }

    /**
     * 获取数据错误时
     *
     * @param error 返回的业务错误
     * @param e     网络错误
     */
    public abstract void onError(ErrorModel error, Throwable e);
}
