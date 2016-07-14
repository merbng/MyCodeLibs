package com.app.merbng.mycodelibs.A_studyRecycleAddHeardFoot;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.app.merbng.mycodelibs.A_studyRecycleAddHeardFoot.weight.DemoFooter;
import com.app.merbng.mycodelibs.A_studyRecycleAddHeardFoot.weight.DemoHearder;
import com.app.merbng.mycodelibs.R;
import com.app.merbng.mycodelibs.adapters.DemoAdapter;
import com.app.merbng.mycodelibs.base.BaseActivity;
import com.app.merbng.mycodelibs.model.DataDemo;
import com.cundong.recyclerview.RecyclerViewUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * RecycleView
 * 加头  加脚
 * <p/>
 * 未完成：http://blog.csdn.net/jdsjlzx/article/details/51794220#rd
 */
public class StudyRecycleAddHeardFootActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_recycle_add_heard_foot);
        List<DataDemo> list = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            list.add(new DataDemo("item+" + i));
        }
        RecyclerView recycleView = (RecyclerView) findViewById(R.id.rv);

        recycleView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        RecyclerViewUtils.setHeaderView(recycleView, new DemoHearder(mContext));
        RecyclerViewUtils.setFooterView(recycleView, new DemoFooter(mContext));
        recycleView.setAdapter(new DemoAdapter(list, mContext));

    }
}
