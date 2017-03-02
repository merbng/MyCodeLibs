package com.app.merbng.mycodelibs.activitys;

import android.os.Bundle;

import com.app.merbng.mycodelibs.R;
import com.app.merbng.mycodelibs.adapters.ParallaxTestAdapter;
import com.app.merbng.mycodelibs.base.BaseActivity;
import com.app.merbng.mycodelibs.widget.ParallaxRecyclerView;

/**
 * 层叠效果RecycleView
 * http://www.jianshu.com/p/7ddb265f6250?utm_source=desktop&utm_medium=timeline
 * https://github.com/SouthernBox/ParallaxRecyclerView
 */
public class ParallaxActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parallax);
        ((ParallaxRecyclerView) findViewById(R.id.parallax_rv)).setAdapter(new ParallaxTestAdapter(this));
    }
}
