package com.app.merbng.mycodelibs.activitys;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.app.merbng.mycodelibs.R;
import com.app.merbng.mycodelibs.adapters.DemoAdapter;
import com.app.merbng.mycodelibs.model.DataDemo;

import java.util.ArrayList;

/**
 * 学习RecycleView
 */
public class StudyRecycleActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studyrecycle);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycleView);
        if (recyclerView != null) {
            recyclerView.setLayoutManager(new GridLayoutManager(StudyRecycleActivity.this, 5));
            ArrayList<DataDemo> list = new ArrayList<>();
            for (int i = 0; i < 100; i++) {
                list.add(new DataDemo("第" + i + "项"));
            }
            DemoAdapter demoAdapter = new DemoAdapter(list, StudyRecycleActivity.this);
            recyclerView.setAdapter(demoAdapter);
        }
    }
}
