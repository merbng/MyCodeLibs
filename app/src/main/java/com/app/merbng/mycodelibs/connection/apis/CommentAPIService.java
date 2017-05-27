package com.app.merbng.mycodelibs.connection.apis;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Query;

/**
 * Created by ght on 2016/9/9.
 */
@SuppressWarnings("CheckStyle")
public interface CommentAPIService {
    /**
     * 修改评论
     */
    @PUT("api/comments")
    Observable<ResponseBody>  editComment(@Body RequestBody body);

    /**
     * 删除评论
     */
    @DELETE("api/comments")
    Observable<ResponseBody> deleteComment(@Query("commentId") String commentId);

    /**
     * 获取某人发表的评论
     */
    @GET("api/comments")
    Observable<ResponseBody>  getComments(@Query("pageNo") int pageNo, @Query("pageSize") int pageSize, @Query("toUserId") String toUserId);

}
