package com.app.merbng.mycodelibs.base;

import android.content.Context;


/**
 * use:
 * Created by taojunbin on 2016/5/18.
 */
public interface IConnectionManager {
    void connect(Connection connection);

    String getResult(Connection connection);

    void cancelAll();

    void cancelCurrent(Context mContext);

    void destory();
}
