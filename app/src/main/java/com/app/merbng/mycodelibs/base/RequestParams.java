package com.app.merbng.mycodelibs.base;

import java.util.HashMap;

/**
 * Created by taojunbin on 2016/7/13.
 * Role:
 */
public class RequestParams {
    private HashMap<String, Object> params;

    public static RequestParams newParams() {
        return new RequestParams();
    }

    private RequestParams() {
        params = new HashMap<>();
    }

    public RequestParams addParameter(String key, Object value) {
        params.put(key, value);
        return this;
    }

    public HashMap<String, Object> getValue() {
        return params;
    }
}
