package com.app.merbng.mycodelibs.A_drag_swip;

import java.util.List;

/**
 * Created by Merbng on 2017/2/15.
 */
public interface NewsView {
    void returnNewsChannel(List<NewsChannelTable> tables);
    void returnNewsList(List<NewsInfo> info);
}