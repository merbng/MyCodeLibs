package com.app.merbng.mycodelibs.activitys;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.app.merbng.mycodelibs.R;
import com.app.merbng.mycodelibs.widget.VerticalDrawerLayout;

/**
 * 竖直的DrawerLayout
 * https://github.com/corerzhang/VerticalDrawerLayout
 */
public class VerticalDrawerLayoutActivity extends AppCompatActivity {

    private VerticalDrawerLayout mDrawerLayout;
    private ImageView mArrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vertical_drawer_layout);


        mDrawerLayout = (VerticalDrawerLayout) findViewById(R.id.vertical);
        mArrow = (ImageView) findViewById(R.id.img);


        mDrawerLayout.setDrawerListener(new VerticalDrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                mArrow.setRotation(slideOffset * 180);
            }
        });
    }

    public void onClick(View v) {
        if (mDrawerLayout.isDrawerOpen()) {
            mDrawerLayout.closeDrawer();
        } else {
            mDrawerLayout.openDrawerView();
        }
    }
}
