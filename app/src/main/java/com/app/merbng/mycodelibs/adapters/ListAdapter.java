package com.app.merbng.mycodelibs.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.merbng.mycodelibs.R;

import java.util.List;

/**
 * Created by merbng on 2016/3/27.
 */
public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ItemViewHolder> {
    private final LayoutInflater mInflate;
    private List<String> mDatas;
    private OnItemClickListeners listener;

    public ListAdapter(Context mContext, List<String> mDatas) {
        mInflate = LayoutInflater.from(mContext);
        this.mDatas = mDatas;
    }

    @Override
    public ListAdapter.ItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        ItemViewHolder holder = new ItemViewHolder(mInflate.inflate(R.layout.item, viewGroup, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(final ListAdapter.ItemViewHolder itemViewHolder, int i) {
        itemViewHolder.mTextView.setText(mDatas.get(i));
        if (listener != null) {
            if (itemViewHolder.itemView.hasOnClickListeners()) {
                itemViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int position = itemViewHolder.getPosition();
                        listener.onItemClick(v, position);
                    }
                });

                itemViewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        int position = itemViewHolder.getPosition();
                        listener.onItemLoneClick(v, position);
                        return true;
                    }
                });


            }


        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        private TextView mTextView;

        public ItemViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.text);
        }
    }

    /**
     * 处理item点击事件的接口
     */
    public interface OnItemClickListeners {
        void onItemClick(View view, int position);

        void onItemLoneClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListeners listener) {
        this.listener = listener;
    }

    public void addItemData(int position, String value) {
        if (position > mDatas.size()) {
            position = mDatas.size();
        }
        if (position < 0) {
            position = 0;
        }
        mDatas.add(position, value);
        notifyItemChanged(position);
    }

    public String removeItemData(int positon) {
        if (positon > mDatas.size() - 1) {
            return null;
        }
        String value = mDatas.remove(positon);
        notifyItemRemoved(positon);
        return value;
    }
}
