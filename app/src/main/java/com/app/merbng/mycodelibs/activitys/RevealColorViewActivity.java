package com.app.merbng.mycodelibs.activitys;

import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.view.View;

import com.app.merbng.mycodelibs.R;
import com.app.merbng.mycodelibs.base.BaseActivity;
import com.app.merbng.mycodelibs.widget.RevealColorView;

/**
 * Banner动画
 * https://github.com/markushi/android-ui
 */
public class RevealColorViewActivity extends BaseActivity implements View.OnClickListener {
    private RevealColorView revealColorView;
    private View selectedView;
    private int backgroundColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reveal_color_view);
        revealColorView = (RevealColorView) findViewById(R.id.reveal);
        backgroundColor = Color.parseColor("#212121");
        findViewById(R.id.btn_1).setOnClickListener(this);
        findViewById(R.id.btn_2).setOnClickListener(this);
        findViewById(R.id.btn_3).setOnClickListener(this);
        findViewById(R.id.btn_4).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        final int color = getColor(v);
        final Point p = getLocationInView(revealColorView, v);

        if (selectedView == v) {
            revealColorView.hide(p.x, p.y, backgroundColor, 0, 300, null);
            selectedView = null;
        } else {
            revealColorView.reveal(p.x, p.y, color, v.getHeight() / 2, 340, null);
            selectedView = v;
        }
    }

    private Point getLocationInView(View src, View target) {
        final int[] l0 = new int[2];
        src.getLocationOnScreen(l0);

        final int[] l1 = new int[2];
        target.getLocationOnScreen(l1);

        l1[0] = l1[0] - l0[0] + target.getWidth() / 2;
        l1[1] = l1[1] - l0[1] + target.getHeight() / 2;

        return new Point(l1[0], l1[1]);
    }

    private int getColor(View view) {
        return Color.parseColor((String) view.getTag());
    }
}
