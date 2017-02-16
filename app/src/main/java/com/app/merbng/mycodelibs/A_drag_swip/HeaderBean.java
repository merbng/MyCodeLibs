package com.app.merbng.mycodelibs.A_drag_swip;

import java.util.List;

/**
 * Created by Merbng on 2017/2/15.
 */

public class HeaderBean {
    private List<NewsChannelTable> mList;

    public HeaderBean(List<NewsChannelTable> list) {
        mList = list;
    }

    public List<NewsChannelTable> getList() {
        return mList;
    }

    public void setList(List<NewsChannelTable> list) {
        mList = list;
    }
}