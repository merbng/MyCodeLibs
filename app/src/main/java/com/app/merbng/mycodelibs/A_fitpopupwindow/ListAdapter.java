package com.app.merbng.mycodelibs.A_fitpopupwindow;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.app.merbng.mycodelibs.A_fitpopupwindow.utils.FitPopupWindow;
import com.app.merbng.mycodelibs.A_fitpopupwindow.utils.ScreenUtils;
import com.app.merbng.mycodelibs.R;
import com.app.merbng.mycodelibs.utils.DensityUtil;

import java.util.List;

/**
 * Created by DongJr on 2017/2/21.
 */

public class ListAdapter extends RecyclerView.Adapter<ListHolder> {

    private List<String> mList;
    private Context mContext;

    public ListAdapter(Context context, List<String> list) {
        mContext = context;
        mList = list;
    }


    @Override
    public ListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.pop_item_list, parent, false);
        return new ListHolder(view);
    }

    @Override
    public void onBindViewHolder(ListHolder holder, int position) {

        holder.ivRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View contentView = LayoutInflater.from(mContext)
                        .inflate(R.layout.layout_popupwindow, null);
                FitPopupWindow fitPopupWindow = new FitPopupWindow((Activity) mContext,
                        ScreenUtils.getScreenWidth(mContext) - DensityUtil.dip2px(mContext,20),
                        ViewGroup.LayoutParams.WRAP_CONTENT
                );
                fitPopupWindow.setView(contentView, v);
                fitPopupWindow.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
