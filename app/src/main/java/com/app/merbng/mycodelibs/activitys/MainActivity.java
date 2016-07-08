package com.app.merbng.mycodelibs.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.app.merbng.mycodelibs.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btnClick(View view) {
        switch (view.getId()) {
            case R.id.btn_add_logo_qrCode:
                startActivity(new Intent(this, LogoQrCodeActivity.class));
                break;
            case R.id.btn_recycleView:
                startActivity(new Intent(this, RecyclerViewActivity.class));
                break;
            case R.id.btn_seeZoomImg:
                startActivity(new Intent(this, SeeZoomImgActivity.class));
                break;
            case R.id.btn_TipShow:
                startActivity(new Intent(this, TipShowActivity.class));
                break;
            case R.id.btn_titleBar:
                startActivity(new Intent(this, TitleBarActivity.class));
                break;
            case R.id.btn_zan:
                startActivity(new Intent(this, ZanActivity.class));
                break;
        }
    }
}


