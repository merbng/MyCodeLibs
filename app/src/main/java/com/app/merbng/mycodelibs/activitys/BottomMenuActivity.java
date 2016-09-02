package com.app.merbng.mycodelibs.activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.app.merbng.mycodelibs.R;
import com.app.merbng.mycodelibs.base.BaseActivity;
import com.app.merbng.mycodelibs.widget.NotifyingScrollView;
import com.app.merbng.mycodelibs.widget.PoppyViewHelper;

public class BottomMenuActivity extends BaseActivity {
    private PoppyViewHelper mPoppyViewHelper;
    private LayoutInflater layoutInflater;
    private NotifyingScrollView scrollView;
    private View poppyview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_menu);
        layoutInflater=LayoutInflater.from(this);
        scrollView=(NotifyingScrollView)findViewById(R.id.scrollView);
        poppyview=layoutInflater.inflate(R.layout.poppyview, null);
        mPoppyViewHelper = new PoppyViewHelper(this, PoppyViewHelper.PoppyViewPosition.TOP);
        ViewGroup poppyView = mPoppyViewHelper.createPoppyViewOnScrollView(scrollView, poppyview);

        poppyView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show("Click me!");
            }
        });
    }
}
