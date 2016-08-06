package com.app.merbng.mycodelibs.utils;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by taojunbin on 2016/2/29.
 * 用于保存和删除本地等耗时操作的线程
 */
public class AsyncThread {
    private static AsyncThread asyncThread;
    private ExecutorService threadPool;

    private AsyncThread() {
        threadPool = Executors.newCachedThreadPool();
    }

    public synchronized static AsyncThread getInstance() {
        if (asyncThread == null) {
            asyncThread = new AsyncThread();
        }
        return asyncThread;
    }

    public void start(final OnListener onListener) {
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    final Object object = onListener.doInBackground();
                    Handler mHanlder = new Handler(Looper.getMainLooper());
                    mHanlder.post(new Runnable() {
                        @Override
                        public void run() {
                            onListener.doFinish(object);
                        }
                    });
                } catch (Exception e) {
                    onListener.error(e);
                }
            }
        });
    }

    public void start(final OnDoInBackgroundListener callBack) {
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                callBack.doInBackground();
            }
        });
    }

    public interface OnListener {
        Object doInBackground();

        void doFinish(Object object);

        void error(Exception e);
    }

    public interface OnDoInBackgroundListener {
        void doInBackground();
    }
}
