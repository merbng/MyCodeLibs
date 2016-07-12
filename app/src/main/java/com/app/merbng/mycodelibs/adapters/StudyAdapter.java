package com.app.merbng.mycodelibs.adapters;

import android.support.v7.widget.RecyclerView;

import com.app.merbng.mycodelibs.R;
import com.app.merbng.mycodelibs.model.DataDemo;
import com.app.merbng.mycodelibs.viewHolder.RecyclerHolder;

import java.util.Collection;

/**
 * Created by zx on 2016/7/12.
 */
public class StudyAdapter extends BaseRecyclerAdapter<DataDemo> {
    public StudyAdapter(RecyclerView v, Collection<DataDemo> datas, int itemLayoutId) {
        super(v, datas, itemLayoutId);
    }

    @Override
    public void convert(RecyclerHolder holder, DataDemo item, int position, boolean isScrolling) {
        holder.setText(R.id.mBtn, item.getName() + "南海mBtn：" + isScrolling);
    }
}
