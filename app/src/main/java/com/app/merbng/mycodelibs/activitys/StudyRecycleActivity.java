package com.app.merbng.mycodelibs.activitys;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.app.merbng.mycodelibs.R;
import com.app.merbng.mycodelibs.adapters.StudyAdapter;
import com.app.merbng.mycodelibs.base.BaseActivity;
import com.app.merbng.mycodelibs.model.DataDemo;

import java.util.ArrayList;

/**
 * 学习RecycleView
 */
public class StudyRecycleActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studyrecycle);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycleView);
        if (recyclerView != null) {
            recyclerView.setLayoutManager(new LinearLayoutManager(StudyRecycleActivity.this, LinearLayoutManager.VERTICAL, false));
            ArrayList<DataDemo> list = new ArrayList<>();
            for (int i = 0; i < 100; i++) {
                list.add(new DataDemo("第" + i + "项"));
            }
//            DemoAdapter demoAdapter = new DemoAdapter(list, StudyRecycleActivity.this);
//            recyclerView.setAdapter(demoAdapter);
//            封装的万能的adapter    有bug！！！
            StudyAdapter adapter = new StudyAdapter(recyclerView, list, R.layout.item_demo);
            recyclerView.setAdapter(adapter);
        }
    }
}
