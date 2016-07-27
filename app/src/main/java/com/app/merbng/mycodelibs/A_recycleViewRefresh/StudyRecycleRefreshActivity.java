package com.app.merbng.mycodelibs.A_recycleViewRefresh;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.DefaultItemAnimator;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.app.merbng.mycodelibs.R;
import com.app.merbng.mycodelibs.adapters.MainAdapter;
import com.app.merbng.mycodelibs.base.BaseActivity;

import java.util.ArrayList;

/**
 * 简单的   RecycleView  下拉加载，上拉刷新
 */
public class StudyRecycleRefreshActivity extends BaseActivity implements OnItemClickListener, LFRecyclerView.LFRecyclerViewListener, LFRecyclerView.LFRecyclerViewScrollChange {

    private LFRecyclerView recycleview;
    private boolean b;
    private ArrayList<String> list;
    private MainAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_recycle_refresh);

        list = new ArrayList<String>();
        for (int i = 0; i < 10; i++) {
            list.add("leefeng.me" + i);
        }

        recycleview = (LFRecyclerView) findViewById(R.id.recycleview);
        recycleview.setLoadMore(true);
        recycleview.setRefresh(true);
        recycleview.setNoDateShow();
        recycleview.setAutoLoadMore(true);
        recycleview.setOnItemClickListener(this);
        recycleview.setLFRecyclerViewListener(this);
        recycleview.setScrollChangeListener(this);
        recycleview.setItemAnimator(new DefaultItemAnimator());
        adapter = new MainAdapter(list);
        recycleview.setAdapter(adapter);

        TextView tv = new TextView(mContext);
        tv.setText("");
        tv.setTextColor(Color.WHITE);
        tv.setGravity(Gravity.CENTER);
        tv.setBackgroundColor(Color.RED);
        recycleview.setHeaderView(tv);

    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                b = !b;
                list.add(0, "刷新刷新的新数据");
                recycleview.stopRefresh(b);
                adapter.notifyItemInserted(0);
            }
        }, 2000);
    }

    @Override
    public void onLoadMore() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                recycleview.stopLoadMore();
                list.add(list.size(), "加载出的更多数据");
                adapter.notifyItemInserted(list.size() - 1);
            }
        }, 2000);
    }

    @Override
    public void onRecyclerViewScrollChange(View view, int i, int i1, int i2, int i3) {

    }

    @Override
    public void onClick(int position) {
        show("点击了：" + position);
    }

    @Override
    public void onLongClick(int position) {
        show("长按：" + position);
    }
}
