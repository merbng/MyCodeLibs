package com.hitomi.transferimage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import com.bumptech.glide.Glide;

import java.util.concurrent.Executors;

public class TransMainActivity extends TransImageBaseActivity {

    private Button btnList, btnGrid, btngroupImage, btnGoTouchMove, btnClearGlide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.hitomi.transferimage.R.layout.activity_trans_img_main);

        btnList = (Button) findViewById(com.hitomi.transferimage.R.id.btn_list);
        btnGrid = (Button) findViewById(com.hitomi.transferimage.R.id.btn_grid);
        btngroupImage = (Button) findViewById(com.hitomi.transferimage.R.id.btn_group_image);
        btnGoTouchMove = (Button) findViewById(com.hitomi.transferimage.R.id.btn_touch_move);
        btnClearGlide = (Button) findViewById(com.hitomi.transferimage.R.id.btn_clear_glide);

        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TransMainActivity.this, ListViewActivity.class));
            }
        });

        btnGrid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TransMainActivity.this, GridViewActivity.class));
            }
        });

        btngroupImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TransMainActivity.this, GroupImageActivity.class));
            }
        });

        btnGoTouchMove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TransMainActivity.this, TouchMoveImageActivity.class));
            }
        });

        btnClearGlide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Executors.newSingleThreadExecutor().submit(new Runnable() {
                    @Override
                    public void run() {
                        Glide.get(getApplicationContext()).clearDiskCache();
                        Glide.get(getApplicationContext()).clearMemory();
                    }
                });
            }
        });

    }

}
