package com.app.merbng.mycodelibs.music;


import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by MASAILA on 16/5/18.
 */
public interface MusicService {

    @GET("getAllPlaylist/{page}")
    Observable<HttpResult<List<PlayList>>> getAllPlaylist(
            @Path("page") int page
    );

}