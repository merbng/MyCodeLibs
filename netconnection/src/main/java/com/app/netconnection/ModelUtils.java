package com.app.netconnection;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

/**
 * Created by merbng on 2017/10/26.
 */

public class ModelUtils {
    public ModelUtils() {

    }

    /**
     * json字符串转成Bean
     */
    public static <T> T getBean(String json, Class<T> tClass) {
        T t = null;
        try {
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss SSS").create();
            gson.fromJson(json, tClass);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
        return t;
    }
}
