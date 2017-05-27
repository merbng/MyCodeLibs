package com.app.merbng.mycodelibs.utils;

import com.app.merbng.mycodelibs.beans.ErrorModel;
import com.app.merbng.mycodelibs.connection.SCObserver;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;

/**
 * Created by taojunbin on 2016/7/20.
 * Role:
 */
public final class ModelUtil {
    /**
     * 私有构造器
     */
    private ModelUtil() {
    }

    /**
     * 字节码对象转成JSON字符串
     */
    public static String bean2Json(Object obj) {
        if (obj == null) {
            return null;
        }
        return new Gson().toJson(obj);
    }

    /**
     * Json字符串转成bean
     */
    public static <T> T getBean(String json, Class<T> cls) {
        T t = null;
        try {
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss SSS").create();
            t = gson.fromJson(json, cls);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }

    /**
     * 解析成list<bean>的形式
     *
     * @param json 要解析的字符串
     * @param cls  参数
     * @param <T>  泛型
     * @return 对象集合
     */
    public static <T> List<T> getListBeans(String json, Class<T> cls) {
        List<T> list = new ArrayList<>();
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss.SSS").create();
        try {
            JsonArray array = new JsonParser().parse(json).getAsJsonArray();
            for (JsonElement element : array) {
                list.add(gson.fromJson(element, cls));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 解析服务器返回的结果
     *
     * @param response 返回消息体
     * @param callBack 回调
     */
    public static <T> void parser(Response<T> response, SCObserver<T> callBack) {

        if (response.code() >= 200
                && response.code() < 400) {
            callBack.onNext(response.body());
        } else {
            try {
                String s = response.errorBody().string();
                LogUtil.log.e(s);
                ErrorModel error = ModelUtil.getBean(s, ErrorModel.class);
                callBack.onError(error, null);
            } catch (Exception e) {
                callBack.onError(null, e);
            }
        }

    }
}
