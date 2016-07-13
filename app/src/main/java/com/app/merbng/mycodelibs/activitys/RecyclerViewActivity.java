package com.app.merbng.mycodelibs.activitys;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.app.merbng.mycodelibs.ListItemDecoration;
import com.app.merbng.mycodelibs.R;
import com.app.merbng.mycodelibs.adapters.ListAdapter;
import com.app.merbng.mycodelibs.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends BaseActivity implements View.OnClickListener {
    private RecyclerView mRecyclerView;
    private ListAdapter mListAdapter;
    private List<String> mDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);
        initData();
        mRecyclerView = (RecyclerView) findViewById(R.id.recycle);
        mListAdapter = new ListAdapter(this, mDatas);
        mRecyclerView.setAdapter(mListAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new ListItemDecoration(this, LinearLayoutManager.VERTICAL));
//        设置item动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mListAdapter.setOnItemClickListener(new ListAdapter.OnItemClickListeners() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(RecyclerViewActivity.this, "Click" + mDatas.get(position), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLoneClick(View view, int position) {
                mListAdapter.removeItemData(position); //remove the item
                Toast.makeText(RecyclerViewActivity.this, "LongClick" + mDatas.get(position), Toast.LENGTH_SHORT).show();
            }
        });

    }

    protected void initData() {
        mDatas = new ArrayList<String>();
        for (int i = 0; i < 40; i++) {
            mDatas.add(String.valueOf(i));
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.id_btn_add:
                mListAdapter.addItemData(mListAdapter.getItemCount(), "这是新的");
                break;
            case R.id.id_btn_remove:
                mListAdapter.removeItemData(mListAdapter.getItemCount() - 1);
                break;
        }
    }
}
