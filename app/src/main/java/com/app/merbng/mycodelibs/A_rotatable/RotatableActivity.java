package com.app.merbng.mycodelibs.A_rotatable;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.OrientationEventListener;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.app.merbng.mycodelibs.R;
import com.app.merbng.mycodelibs.base.BaseActivity;


/**
 * 旋转ImageView
 * http://blog.csdn.net/yanzi1225627/article/details/22439119
 */
public class RotatableActivity extends BaseActivity {
    private static final String tag = "yan";
    RotateImageView rotateImg1;
    RotateImageView rotateImg2;
    ImageView commonImg;
    Button fadeBtn, btnClickOpen,
    btnClickOclose;
    MyOrientationEventListener mOrientationListener;
    Bitmap a;
    Bitmap b;
    boolean flag = true;
    int mOrientation = OrientationEventListener.ORIENTATION_UNKNOWN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rotatable);
        initUI();
        mOrientationListener = new MyOrientationEventListener(this);
        b = BitmapFactory.decodeResource(getResources(), R.drawable.deg);
        a = BitmapFactory.decodeResource(getResources(), R.drawable.dec);
        fadeBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (flag) {
                    rotateImg1.setBitmap(b);
                    flag = false;
                } else {
                    rotateImg1.setBitmap(a);
                    flag = true;
                }
            }
        });

        btnClickOpen
        .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    rotateImg2.setOrientation(-90, true);
            }
        });

        btnClickOclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    rotateImg2.setOrientation(0, true);

            }
        });


    }


    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        mOrientationListener.enable();
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        mOrientationListener.disable();
    }

    private void initUI() {
        rotateImg1 = (RotateImageView) findViewById(R.id.rotate_img_1);
        rotateImg1.setImageResource(R.drawable.deg);
        rotateImg2 = (RotateImageView) findViewById(R.id.rotate_img_2);
        rotateImg2.setImageResource(R.drawable.dec);
        commonImg = (ImageView) findViewById(R.id.common_img);
        fadeBtn = (Button) findViewById(R.id.btn_fade);
        btnClickOpen = (Button) findViewById(R.id.btn_click_open);
        btnClickOclose = (Button) findViewById(R.id.btn_click_close);
    }

    private class MyOrientationEventListener extends OrientationEventListener {

        public MyOrientationEventListener(Context context) {
            super(context);
            // TODO Auto-generated constructor stub
        }

        @Override
        public void onOrientationChanged(int orientation) {
            // TODO Auto-generated method stub
            if (orientation == OrientationEventListener.ORIENTATION_UNKNOWN) {
                return;
            }
            mOrientation = Util.roundOrientation(orientation, mOrientation);
            Log.i(tag, "MyOrientationEventListener mOrientation = " + mOrientation);

            rotateImg1.setOrientation(mOrientation, true);
//            rotateImg2.setOrientation(mOrientation, true);
            commonImg.setRotation(-mOrientation);
        }

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        // TODO Auto-generated method stub
        super.onConfigurationChanged(newConfig);
        int degree = newConfig.orientation;
        Log.i("yan", "onConfigurationChanged = " + degree);
    }
}