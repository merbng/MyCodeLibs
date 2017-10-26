package com.app.netconnection;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

/**
 * Created by merbng on 2017/10/26.
 */

public abstract class CustomObserver<T> implements Observer<T> {
    private Disposable disposable;

    @Override
    public void onSubscribe(@NonNull Disposable d) {
        onStart();
        disposable = d;
    }

    public Disposable getDisposable() {
        return disposable;
    }

    private void onStart() {
    }

    @Override
    public void onError(@NonNull Throwable e) {
        if (e instanceof HttpException) {
            try {
                String s = ((HttpException) e).response().errorBody().toString();
                ErrorModel model = ModelUtils.getBean(s, ErrorModel.class);
                onError(model, e);
            } catch (Exception e1) {
                onError(null, e);
            }
        } else {
            onError(null, e);
        }
    }

    public abstract void onError(ErrorModel model, Throwable e);
}
