package com.app.merbng.mycodelibs.A_fitpopupwindow;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.app.merbng.mycodelibs.R;

import java.util.ArrayList;

public class SecondNextActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, SecondNextActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_next);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        init();
    }

    private void init() {

        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add(i + "");
        }

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);

        ListAdapter listAdapter = new ListAdapter(this, list);

        mRecyclerView.setAdapter(listAdapter);
    }


}
