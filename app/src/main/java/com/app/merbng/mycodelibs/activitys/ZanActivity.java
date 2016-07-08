package com.app.merbng.mycodelibs.activitys;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;

import com.app.merbng.mycodelibs.R;
import com.app.merbng.mycodelibs.views.VoteSurface;

public class ZanActivity extends AppCompatActivity {
    private VoteSurface sur;
    private Bitmap b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_zan);
        sur = (VoteSurface) findViewById(R.id.view);

        b = BitmapFactory.decodeResource(getResources(), R.drawable.zan);

    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        sur.click(b);
        return true;
    }

}
