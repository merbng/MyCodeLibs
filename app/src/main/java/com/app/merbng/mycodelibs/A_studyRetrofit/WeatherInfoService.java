package com.app.merbng.mycodelibs.A_studyRetrofit;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by zx on 2016/7/13.
 */
public interface WeatherInfoService {
    @GET("http://v.juhe.cn/weather/index?format=2&cityname=北京&key=b952ad7acbc7415f3f3c9bf274e39c45")
    Call<ResponseBody> getString();

    @GET("http://v.juhe.cn/weather/index?format=2&cityname=北京&key=b952ad7acbc7415f3f3c9bf274e39c45")
    Call<WeatherInfo> getWeatherInfo();

    @GET("/weather/index?/")
    Call<WeatherInfo> getWeatherInfo(@Query("format") String format, @Query("cityname") String cityname, @Query("key") String key);
}
