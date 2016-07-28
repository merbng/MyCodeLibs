package com.app.merbng.mycodelibs.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.animation.OvershootInterpolator;

import com.app.merbng.mycodelibs.A_StudyProcessButton.ProcessButtonActivity;
import com.app.merbng.mycodelibs.A_recycleViewRefresh.StudyRecycleRefreshActivity;
import com.app.merbng.mycodelibs.A_studyRetrofit.StudyRetrofitActivity;
import com.app.merbng.mycodelibs.R;
import com.app.merbng.mycodelibs.base.BaseActivity;
import com.robinhood.ticker.TickerUtils;
import com.robinhood.ticker.TickerView;
import com.zhy.changeskin.SkinManager;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showTickerView();
    }

    private void showTickerView() {
        TickerView tickerView = (TickerView) findViewById(R.id.tickerView);
        tickerView.setTextColor(R.color.color_error);
        tickerView.setTextSize(16); 
        tickerView.setAnimationDuration(500);
        tickerView.setAnimationInterpolator(new OvershootInterpolator());
        tickerView.setGravity(Gravity.START);
        tickerView.setCharacterList(TickerUtils.getDefaultNumberList());
    }

    public void btnClick(View view) {
        switch (view.getId()) {
            case R.id.btn_add_logo_qrCode:
                startActivity(new Intent(mContext, LogoQrCodeActivity.class));
                break;
            case R.id.btn_recycleView:
                startActivity(new Intent(mContext, RecyclerViewActivity.class));
                break;
            case R.id.btn_seeZoomImg:
                startActivity(new Intent(mContext, SeeZoomImgActivity.class));
                break;
            case R.id.btn_TipShow:
                startActivity(new Intent(mContext, TipShowActivity.class));
                break;
            case R.id.btn_titleBar:
                startActivity(new Intent(mContext, TitleBarActivity.class));
                break;
            case R.id.btn_zan:
                startActivity(new Intent(mContext, ZanActivity.class));
                break;
            case R.id.btn_viewpager:
                startActivity(new Intent(mContext, TabActivity.class));
                break;
            case R.id.time_line:
                startActivity(new Intent(mContext, TimeLineActivity.class));
                break;
            case R.id.Shine_text:
                startActivity(new Intent(mContext, ShineTextActivity.class));
                break;
            case R.id.loading:
                startActivity(new Intent(mContext, LoadingActivity.class));
                break;
            case R.id.search:
                startActivity(new Intent(mContext, SearchActivity.class));
                break;
            case R.id.toast:
                startActivity(new Intent(mContext, ToastActivity.class));
                break;
            case R.id.createcircular://圆形缩放
                startActivity(new Intent(mContext, CreateCircularActivity.class));
                break;
            case R.id.studyRecycleView://学习RecycleView
                startActivity(new Intent(mContext, StudyRecycleActivity.class));
                break;
            case R.id.testGetConnection://测试Get请求
                startActivity(new Intent(mContext, TestGetConnection.class));
                break;
            case R.id.studyRetrofit://学习Retrofit
                startActivity(new Intent(mContext, StudyRetrofitActivity.class));
                break;
            case R.id.StudyRecycleAddHeardFootActivity://RecycleView加头加脚
                startActivity(new Intent(mContext, StudyRecycleRefreshActivity.class));
                break;
            case R.id.AppMsgActivity://AppMsgActivity顶部Toast
                startActivity(new Intent(mContext, AppMsgActivity.class));
                break;
            case R.id.processButtonActivity://进度条Button
                startActivity(new Intent(mContext, ProcessButtonActivity.class));
                break;
            case R.id.QQLoginActivity://登陆
                startActivity(new Intent(mContext, QQLoginActivity.class));
                break;
            case R.id.SinaLoginActivity://微博登陆
                startActivity(new Intent(mContext, SinaLoginActivity.class));
                break;
            case R.id.WeixinLoginActivity://微信登陆
                startActivity(new Intent(mContext, WeixinLoginActivity.class));
                break;
            case R.id.TestEditTextActivity://EditText值得注意的地方
                startActivity(new Intent(mContext, TestEditTextActivity.class));
                break;


            case R.id.changeSkin://换肤 白天模式
                SkinManager.getInstance().changeSkin("night");
                show("白天模式");
                break;
            case R.id.changeSkin2://换肤2 夜间模式
                SkinManager.getInstance().changeSkin("day");
                show("夜间模式");
                break;
        }
    }
}


