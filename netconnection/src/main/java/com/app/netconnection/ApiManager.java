package com.app.netconnection;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by merbng on 2017/10/26.
 */

public class ApiManager {
    public static final String BASE_URL = "http://web.juhe.cn:8080/";
    private Retrofit retrofit;
    private volatile static ApiManager apiManager = null;

    public ApiManager() {
        init();
    }

    private void init() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(generateClient())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static ApiManager getInstance() {
        if (apiManager == null) {
            synchronized (ApiManager.class) {
                if (apiManager == null) {
                    apiManager = new ApiManager();
                }
            }
        }
        return apiManager;
    }

    UPAPIService upapiService() {
        return retrofit.create(UPAPIService.class);
    }

    /**
     * 生成client
     *
     * @return
     */
    private OkHttpClient generateClient() {
        return new OkHttpClient.Builder().addInterceptor(new HeardRequestInterceptor()).build();
    }

    /**
     * 拦截头部
     */
    private class HeardRequestInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            return chain.proceed(initBuilder(chain.request().newBuilder()).build());
        }

        private Request.Builder initBuilder(Request.Builder build) {
            build.addHeader("device", "android");
            return build;
        }
    }
}
