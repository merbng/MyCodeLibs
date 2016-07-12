package com.app.merbng.mycodelibs.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.app.merbng.mycodelibs.R;
import com.app.merbng.mycodelibs.model.DataDemo;
import com.app.merbng.mycodelibs.viewHolder.DemoViewHolder;

import java.util.List;

/**
 * Created by zx on 2016/7/12.
 */
public class DemoAdapter extends RecyclerView.Adapter<DemoViewHolder> {
    private List<DataDemo> dataList;
    private Context mContext;

    public DemoAdapter(List<DataDemo> dataList, Context mContext) {
        this.dataList = dataList;
        this.mContext = mContext;
    }

    @Override
    public DemoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DemoViewHolder(View.inflate(mContext, R.layout.item_demo, null));
    }

    @Override
    public void onBindViewHolder(DemoViewHolder holder, int position) {
        holder.mTectView.setText(dataList.get(position).getName());
    }


    @Override
    public int getItemCount() {
        return dataList.size();
    }

}
