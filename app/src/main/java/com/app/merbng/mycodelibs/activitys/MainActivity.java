package com.app.merbng.mycodelibs.activitys;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.ActivityOptionsCompat;
import android.transition.Explode;
import android.view.View;

import com.app.merbng.mycodelibs.A_MagicFloatView.exp1Barrang.BarrangActivity;
import com.app.merbng.mycodelibs.R;
import com.app.merbng.mycodelibs.base.BaseActivity;
import com.app.merbng.mycodelibs.utils.DialogUtils;
import com.app.merbng.mycodelibs.utils.LogUtil;
import com.app.merbng.mycodelibs.widget.SplashView;

public class MainActivity extends BaseActivity {
    ActivityOptionsCompat aoc;
    public static final int MESSAGE_SHOW_TIPS = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_classify);
        myHandler.sendEmptyMessageDelayed(MESSAGE_SHOW_TIPS, 5000);
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

    public void btnClick(View view) {
        //闪屏图
        showSplashUrl();
        //进入动画
        switch (view.getId()) {
            case R.id.tv_classify_notify:
                openActivity(ClassifyAnimActivity.class);
                break;
            case R.id.tv_classify_anim:
                openActivity(ClassifyAnimActivity.class);
                break;
            case R.id.tv_classify_other:
                openActivity(ClassifyOtherActivity.class);
                break;
            case R.id.tv_classify_test:

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
                url = "http://static.cnbetacdn.com/thumb/article/2016/0802/135ab34c04bd4f9.jpg_600x600.jpg";
                actionUrl = "http://f7.topitme.com";
                break;
            case 1:
                url = "http://static.cnbetacdn.com/thumb/article/2016/0802/1287ae9ff2ee319.jpg_600x600.jpg";
                actionUrl = "http://f7.topitme.com";
                break;
            case 2:
                url = "http://static.cnbetacdn.com/thumb/article/2016/0802/1cd52f011d9af3d.jpg_600x600.jpg";
                actionUrl = "http://f7.topitme.com";
                break;
            case 3:
                url = "http://static.cnbetacdn.com/thumb/article/2016/0802/3b3fff57c1d281d.jpg_600x600.jpg";
                actionUrl = "http://i10.topitme.com";
                break;
            case 4:
                url = "http://static.cnbetacdn.com/thumb/article/2016/0802/906de4e38b58e87.jpg_600x600.jpg";
                actionUrl = "http://i10.topitme.com";
                break;
            case 5:
                url = "https://avatars3.githubusercontent.com/u/11256126?v=3&s=460";
                actionUrl = "https://avatars3.githubusercontent.com";
                break;
            case 6:
                url = "http://static.cnbetacdn.com/thumb/article/2016/0802/f691eb5a692e46a.jpg_600x600.jpg";
                actionUrl = "http://imgsrc.baidu.com";
                break;
            case 7:
                url = "http://static.cnbetacdn.com/thumb/article/2016/0802/a435c3bb4a61eb3.jpg_600x600.jpg";
                actionUrl = "http://imgsrc.baidu.com";
                break;
            case 8:
                url = "http://static.cnbetacdn.com/thumb/article/2016/0802/878978d0ae6ec2d.jpg_600x600.jpg";
                actionUrl = "http://img4q.duitang.com";
                break;
            case 9:
                url = "http://static.cnbetacdn.com/thumb/article/2016/0802/faa9017db6a78a2.jpg_600x600.jpg";
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
}


