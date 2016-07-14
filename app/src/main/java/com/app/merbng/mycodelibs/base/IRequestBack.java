package com.app.merbng.mycodelibs.base;

/**
 * Created by taojunbin on 2016/7/13.
 * Role:请求回调接口
 */
public interface IRequestBack {
    void onSuccess(String json);

    void onFail(Exception e);
}
