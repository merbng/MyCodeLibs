package com.app.netconnection;


import io.reactivex.Observable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by merbng on 2017/10/26.
 */

public interface UPAPIService {
    /**
     * @param consName
     * @param type today,tomorrow,week,nextweek,month,year
     * @return
     */
    @GET("constellation/getAll")
    Observable<LuckBean> getAonstellationLuck(@Query("consName") String consName, @Query("type") String type,@Query("key")String key);

    @GET("constellation/getAll")
    Observable<LuckYearBean> getYearAonstellationLuck(@Query("consName") String consName, @Query("type") String type,@Query("key")String key);

    @POST("api/user/postTest")
    Observable<ResponseBody> postDataTest(@Body RequestBody requestBody);
}
