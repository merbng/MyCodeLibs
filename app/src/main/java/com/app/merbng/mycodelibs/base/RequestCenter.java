package com.app.merbng.mycodelibs.base;

import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by taojunbin on 2016/7/13.
 * Role:Provide the connection pool and manager the connection
 */
public class RequestCenter {
    private static ExecutorService workShop;
    private static HashMap<Integer, Request> works = new HashMap<>();

    /**
     * 提供工作车间
     *
     * @return 工作车间
     */
    public static ExecutorService obtainPower() {
        if (workShop == null) {
            workShop = Executors.newCachedThreadPool();
        }
        return workShop;
    }

    /**
     * 注册请求
     *
     * @param code 识别码
     * @param work 请求
     */
    public static void registerConnection(int code, Request work) {
        works.put(code, work);
    }

    /**
     * 判断Connect是否注册
     *
     * @param code 识别码
     * @return ture:注册 false:未注册
     */
    public static boolean isRegister(int code) {
        return works.containsKey(code);
    }

    /**
     * 根据识别码拿到Connect
     *
     * @param code 识别码
     * @return Request
     */
    public static Request getConnect(int code) {
        return works.get(code);
    }
}
