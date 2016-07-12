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
            case R.id.btn_viewpager:
                startActivity(new Intent(this, TabActivity.class));
                break;
            case R.id.time_line:
                startActivity(new Intent(MainActivity.this, TimeLineActivity.class));
                break;
            case R.id.Shine_text:
                startActivity(new Intent(MainActivity.this, ShineTextActivity.class));
                break;
            case R.id.loading:
                startActivity(new Intent(MainActivity.this, LoadingActivity.class));
                break;
            case R.id.search:
                startActivity(new Intent(MainActivity.this, SearchActivity.class));
                break;
            case R.id.toast:
                startActivity(new Intent(MainActivity.this, ToastActivity.class));
                break;
            case R.id.createcircular://圆形缩放
                startActivity(new Intent(MainActivity.this, CreateCircularActivity.class));
                break;
            case R.id.studyRecycleView://学习RecycleView
                startActivity(new Intent(MainActivity.this, StudyRecycleActivity.class));
                break;
        }
    }
}


