package com.app.merbng.mycodelibs.activitys;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.ActivityOptionsCompat;
import android.transition.Explode;
import android.view.View;

import com.app.merbng.mycodelibs.R;
import com.app.merbng.mycodelibs.base.BaseActivity;
import com.app.merbng.mycodelibs.utils.DialogUtils;
import com.app.merbng.mycodelibs.utils.LogUtil;
import com.app.merbng.mycodelibs.widget.SplashView;


public class MainActivity extends BaseActivity implements View.OnClickListener {
    ActivityOptionsCompat aoc;
    public static final int MESSAGE_SHOW_TIPS = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_classify);
        myHandler.sendEmptyMessageDelayed(MESSAGE_SHOW_TIPS, 5000);
        showSplashUrl();
        findViewById(R.id.tv_classify_anim).setOnClickListener(this);
        findViewById(R.id.tv_classify_web_artal).setOnClickListener(this);
        findViewById(R.id.tv_classify_other).setOnClickListener(this);
    }

    @Override
    public void hand_message(Message msg) {
        switch (msg.what) {
            case MESSAGE_SHOW_TIPS:
                DialogUtils.showTips(this);
                break;
            default:
                break;
        }
    }

    /**
     * 闪屏页图
     */
    private void showSplashUrl() {
        int Numround = (int) Math.round(Math.random() * 10);
        String url = null;
        String actionUrl = null;
        LogUtil.log.e("图：" + Numround);
        switch (Numround) {
            case 0:
                url = "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=594559231,2167829292&fm=27&gp=0.jpg";
                actionUrl = "http://f7.topitme.com";
                break;
            case 1:
                url = "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=594559231,2167829292&fm=27&gp=0.jpg";
                actionUrl = "http://f7.topitme.com";
                break;
            case 2:
                url = "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=594559231,2167829292&fm=27&gp=0.jpg";
                actionUrl = "http://f7.topitme.com";
                break;
            case 3:
                url = "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=594559231,2167829292&fm=27&gp=0.jpg";
                actionUrl = "http://i10.topitme.com";
                break;
            case 4:
                url = "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=594559231,2167829292&fm=27&gp=0.jpg";
                actionUrl = "http://i10.topitme.com";
                break;
            case 5:
                url = "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=594559231,2167829292&fm=27&gp=0.jpg";
                actionUrl = "https://avatars3.githubusercontent.com";
                break;
            case 6:
                url = "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=594559231,2167829292&fm=27&gp=0.jpg";
                actionUrl = "http://imgsrc.baidu.com";
                break;
            case 7:
                url = "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=594559231,2167829292&fm=27&gp=0.jpg";
                actionUrl = "http://imgsrc.baidu.com";
                break;
            case 8:
                url = "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=594559231,2167829292&fm=27&gp=0.jpg";
                actionUrl = "http://img4q.duitang.com";
                break;
            case 9:
                url = "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=594559231,2167829292&fm=27&gp=0.jpg";
                actionUrl = "http://bizhi.zhuoku.com";
                break;
        }
        SplashView.updateSplashData(this, url, actionUrl);
    }

    /**
     * 打开新页面（适配）
     *
     * @param target
     */
    private void openActivity(Class<?> target) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//5.0以上
            Explode explode = new Explode();
            explode.setDuration(1000);
            getWindow().setExitTransition(explode);
            getWindow().setEnterTransition(explode);
            aoc = ActivityOptionsCompat.makeSceneTransitionAnimation(this);
            startActivity(new Intent(mContext, target), aoc.toBundle());
        } else {
            startActivity(new Intent(thisActivity, target));
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_classify_web_artal://网页文章
                openActivity(WebArticleActivity.class);
                break;
            case R.id.tv_classify_anim://动画
                openActivity(ClassifyAnimActivity.class);
                break;
            case R.id.tv_classify_other://其他
                openActivity(ClassifyOtherActivity.class);
                break;
            case R.id.tv_classify_preview://预览
                openActivity(ClassifyPreviewActivity.class);
                break;
        }
    }
}


