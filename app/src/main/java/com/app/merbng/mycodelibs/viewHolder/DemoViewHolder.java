package com.app.merbng.mycodelibs.viewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.app.merbng.mycodelibs.R;

/**
 * Created by zx on 2016/7/12.
 */
public class DemoViewHolder extends RecyclerView.ViewHolder {
    public TextView mTectView;

    public DemoViewHolder(View itemView) {
        super(itemView);
        mTectView = (TextView) itemView.findViewById(R.id.mBtn);
    }
}
