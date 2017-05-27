package com.app.merbng.mycodelibs.connection;

import com.app.merbng.mycodelibs.Constent;
import com.app.merbng.mycodelibs.beans.UserBean;
import com.app.merbng.mycodelibs.connection.apis.CommentAPIService;
import com.app.merbng.mycodelibs.utils.UserUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ght on 2017/2/17.
 */

@SuppressWarnings("CheckStyle")
public class ApiManager {
    private Retrofit retrofit;


    private ApiManager() {
        init();
    }

    public void init() {
        retrofit = new Retrofit.Builder()
                .baseUrl(Constent.URL)
                .client(generateClient())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//新的配置
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    CommentAPIService commentAPIService() {
        return retrofit.create(CommentAPIService.class);
    }


    private volatile static ApiManager INSTANCE = null;

    //获取单例
    public static ApiManager getInstance() {
        if (INSTANCE == null) {
            synchronized (ApiManager.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ApiManager();
                }
            }
        }
        return INSTANCE;
    }

    /**
     * 使用拦截器来给所有的请求加上头
     *
     * @return 处理后的Client
     */
    private OkHttpClient generateClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(new HeadRequestInterceptor())
                .build();
    }

    private class HeadRequestInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            return chain.proceed(initBuilder(chain.request().newBuilder()).build());
        }

        private Request.Builder initBuilder(Request.Builder builder) {
            builder.addHeader("device", "android");
            UserBean userBean = UserUtils.getCurrentUser();
            if (userBean != null) {
                return builder.addHeader("userId", userBean.getUserId())
                        .addHeader("ccookie", userBean.getCookie());
            }
            return builder;
        }
    }
}
