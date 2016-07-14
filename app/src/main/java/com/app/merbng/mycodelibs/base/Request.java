package com.app.merbng.mycodelibs.base;

import android.app.Activity;
import android.support.v4.app.Fragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by taojunbin on 2016/7/13.
 * Role:Request http for json;
 */
public class Request implements IRequest {
    private List<Connect> connects;

    public static Request from(Activity activity) {
        if (RequestCenter.isRegister(activity.hashCode())) {
            return RequestCenter.getConnect(activity.hashCode());
        } else {
            Request request = new Request();
            RequestCenter.registerConnection(activity.hashCode(), request);
            return request;
        }
    }

    public static Request from(Fragment fragment) {
        if (RequestCenter.isRegister(fragment.hashCode())) {
            return RequestCenter.getConnect(fragment.hashCode());
        } else {
            Request request = new Request();
            RequestCenter.registerConnection(fragment.hashCode(), request);
            return request;
        }
    }

    private Request() {
        connects = new ArrayList<>();
    }

    @Override
    public String getRequest(String url, RequestParams params) {
        Connect connect = new Connect()
                .setUrl(url)
                .setMethod(Connect.GET)
                .setParams(params);
        connects.add(connect);
        return connect.work();
    }

    @Override
    public void getRequestInBG(String url, RequestParams params, IRequestBack response) {
        final Connect connect = new Connect()
                .setUrl(url)
                .setMethod(Connect.GET)
                .setParams(params)
                .setListener(response);
        connects.add(connect);
        RequestCenter.obtainPower().execute(new Runnable() {
            @Override
            public void run() {
                connect.work();
            }
        });
    }

    @Override
    public String postRequest(String url, RequestParams params) {
        Connect connect = new Connect()
                .setUrl(url)
                .setMethod(Connect.POST)
                .setParams(params);
        connects.add(connect);
        return connect.work();
    }

    @Override
    public void postRequestInBG(String url, RequestParams params, IRequestBack response) {
        final Connect connect = new Connect()
                .setUrl(url)
                .setMethod(Connect.POST)
                .setParams(params)
                .setListener(response);
        connects.add(connect);
        RequestCenter.obtainPower().execute(new Runnable() {
            @Override
            public void run() {
                connect.work();
            }
        });
    }

    @Override
    public String putRequest(String url, RequestParams params) {
        Connect connect = new Connect()
                .setUrl(url)
                .setMethod(Connect.PUT)
                .setParams(params);
        connects.add(connect);
        return connect.work();
    }

    @Override
    public void putRequestInBG(String url, RequestParams params, IRequestBack response) {
        final Connect connect = new Connect()
                .setUrl(url)
                .setMethod(Connect.PUT)
                .setParams(params)
                .setListener(response);
        connects.add(connect);
        RequestCenter.obtainPower().execute(new Runnable() {
            @Override
            public void run() {
                connect.work();
            }
        });
    }

    @Override
    public String deleteRequest(String url, RequestParams params) {
        Connect connect = new Connect()
                .setUrl(url)
                .setMethod(Connect.DELETE)
                .setParams(params);
        connects.add(connect);
        return connect.work();
    }

    @Override
    public void deleteRequestInBG(String url, RequestParams params, IRequestBack response) {
        final Connect connect = new Connect()
                .setUrl(url)
                .setMethod(Connect.DELETE)
                .setParams(params)
                .setListener(response);
        connects.add(connect);
        RequestCenter.obtainPower().execute(new Runnable() {
            @Override
            public void run() {
                connect.work();
            }
        });
    }

    @Override
    public void cancelRequest() {
        for (int i = 0; i < connects.size(); i++) {
            connects.get(i).cancel();
        }
        connects.clear();
    }
}
