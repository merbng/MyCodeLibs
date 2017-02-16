package com.app.merbng.mycodelibs.A_drag_swip;

import java.util.List;

import rx.Observable;

/**
 * Created by Merbng on 2017/2/15.
 */
public interface NewsModel {
    Observable<List<NewsChannelTable>> requestNewsChannels();
    Observable<NewsInfo> requestNewsList(String newsType, final String id, int startPage);
}