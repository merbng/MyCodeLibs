package com.app.merbng.mycodelibs.A_studyRecycleAddHeardFoot.weight;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.app.merbng.mycodelibs.R;


/**
 * RecyclerView的FooterView，简单的展示一个TextView
 */
public class DemoFooter extends RelativeLayout {

    public DemoFooter(Context context) {
        super(context);
        init(context);
    }

    public DemoFooter(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public DemoFooter(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void init(Context context) {

        inflate(context, R.layout.demo_footer, this);
    }
}