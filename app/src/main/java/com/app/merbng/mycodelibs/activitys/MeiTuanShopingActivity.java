package com.app.merbng.mycodelibs.activitys;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.merbng.mycodelibs.R;
import com.app.merbng.mycodelibs.base.BaseActivity;
import com.app.merbng.mycodelibs.views.AddCartAnimation;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * 仿美团  购物车动画
 * https://github.com/jlcclidong/AddCartAniamtion
 */
public class MeiTuanShopingActivity extends BaseActivity {
    private RecyclerView mRv;
    List<Integer> mList;
    private ImageView mCart;
    private RelativeLayout mRl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mei_tuan_shoping);
        mRl = (RelativeLayout) findViewById(R.id.rl);
        mRv = (RecyclerView) findViewById(R.id.rv);
        mCart = (ImageView) findViewById(R.id.cart);
        mList = new ArrayList<>();
        mList.add(R.drawable.eco);
        mList.add(R.drawable.eep);
        mList.add(R.drawable.eer);
        mList.add(R.drawable.eft);
        mList.add(R.drawable.kys);
        mList.add(R.drawable.img2);
        mList.add(R.drawable.img3);
        mList.add(R.drawable.eer);
        mList.add(R.drawable.eft);
        mList.add(R.drawable.kys);
        mList.add(R.drawable.img2);
        mList.add(R.drawable.img3);
        mList.add(R.drawable.img4);
        CommonAdapter<Integer> mAdapter = new CommonAdapter<Integer>(this, R.layout.item_shop, mList) {
            @Override
            protected void convert(final ViewHolder holder, Integer integer, int position) {
                holder.setImageResource(R.id.iv, integer);
                final ImageView imageView = holder.getView(R.id.iv);
                TextView view = holder.getView(R.id.buy);
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AddCartAnimation.AddToCart(imageView, mCart, mContext, mRl, 1);
                    }
                });
            }
        };
        mRv.setLayoutManager(new LinearLayoutManager(this));
        mRv.setAdapter(mAdapter);
    }
}