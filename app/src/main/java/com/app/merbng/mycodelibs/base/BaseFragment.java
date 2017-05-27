package com.app.merbng.mycodelibs.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.app.merbng.mycodelibs.A_TumbleEditText.utils.ToastUtil;
import com.app.merbng.mycodelibs.event.DefaultEvent;

import de.greenrobot.event.EventBus;

/**
 * Created by ght on 2017/2/17.
 */

public abstract class BaseFragment extends Fragment {

    /**
     * findviewbyid 绑定view的方法.
     *
     * @param mView 根布局
     */
    public abstract void fvb(View mView);

    /**
     * 初始化方法.
     */
    public abstract void init();

    /**
     * 普通的toast.
     *
     * @param str
     */
    public void show(String str) {
         ToastUtil.showInfo(getContext(), str);
    }


    /**
     * 通信方法.
     *
     * @param event 默认的全局事件
     */
    public void onEvent(DefaultEvent event) {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getView() != null) {
            fvb(getView());
        }
        init();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

}
