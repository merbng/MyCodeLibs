package com.app.merbng.mycodelibs.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.transition.Explode;
import android.view.View;

import com.app.merbng.mycodelibs.A_StudyProcessButton.ProcessButtonActivity;
import com.app.merbng.mycodelibs.A_TumbleEditText.TumbeEditTextActivity;
import com.app.merbng.mycodelibs.A_galleryview.GalleryActivity;
import com.app.merbng.mycodelibs.A_materiallogin.MaterialLoginActivity;
import com.app.merbng.mycodelibs.A_recycleViewRefresh.StudyRecycleRefreshActivity;
import com.app.merbng.mycodelibs.A_studyRetrofit.StudyRetrofitActivity;
import com.app.merbng.mycodelibs.A_ticker.TickerActivity;
import com.app.merbng.mycodelibs.R;
import com.app.merbng.mycodelibs.base.BaseActivity;
import com.app.merbng.mycodelibs.utils.LogUtil;
import com.app.merbng.mycodelibs.widget.SplashView;
import com.zhy.changeskin.SkinManager;

public class MainActivity extends BaseActivity {
    ActivityOptionsCompat aoc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Explode explode = new Explode();
        explode.setDuration(500);
        getWindow().setExitTransition(explode);
        getWindow().setEnterTransition(explode);
        aoc = ActivityOptionsCompat.makeSceneTransitionAnimation(this);
    }

    public void btnClick(View view) {
        //闪屏图
        showSplashUrl();
        //进入动画

        switch (view.getId()) {
            case R.id.btn_add_logo_qrCode://带logo的二维码
                startActivity(new Intent(mContext, LogoQrCodeActivity.class));
                break;
            case R.id.btn_recycleView://recycleView
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
            case R.id.studyretrofit://学习Retrofit
                startActivity(new Intent(mContext, StudyRetrofitActivity.class));
                break;
            case R.id.recycleaddheardfootactivity://RecycleView加头加脚
                startActivity(new Intent(mContext, StudyRecycleRefreshActivity.class));
                break;
            case R.id.appmsgactivity://AppMsgActivity顶部Toast
                startActivity(new Intent(mContext, AppMsgActivity.class));
                break;
            case R.id.processbuttonactivity://进度条Button
                startActivity(new Intent(mContext, ProcessButtonActivity.class));
                break;
            case R.id.qqloginactivity://登陆
                startActivity(new Intent(mContext, QQLoginActivity.class));
                break;
            case R.id.sinaloginactivity://微博登陆
                startActivity(new Intent(mContext, SinaLoginActivity.class));
                break;
            case R.id.weixinloginactivity://微信登陆
                startActivity(new Intent(mContext, WeixinLoginActivity.class));
                break;
            case R.id.testedittextactivity://EditText值得注意的地方
                startActivity(new Intent(mContext, TestEditTextActivity.class));
                break;
            case R.id.tickeractivityticker://滚动文本的简单的Android UI组件
                startActivity(new Intent(mContext, TickerActivity.class));
                break;
            case R.id.magicbuttonactivity://从侧边滑出的Button
                startActivity(new Intent(mContext, MagicButtonActivity.class));
                break;
            case R.id.tumbeedittextactivity://Android 3D立体无限旋转容器
                startActivity(new Intent(mContext, TumbeEditTextActivity.class));
                break;
            case R.id.autozoominimageview://一种图片不断向中间放大的动画效果
                startActivity(new Intent(mContext, AutozoomActivity.class));
                break;
            case R.id.kenburnsviewactivity://一张静止图片都有动态的效果感觉
                startActivity(new Intent(mContext, KenBurnsViewActivity.class));
                break;
            case R.id.glideactivity://引导
                startActivity(new Intent(mContext, GlideActivity.class));
                break;
            case R.id.qrcodeactivity://二维码生成/扫描
                startActivity(new Intent(mContext, QrCodeActivity.class));
                break;
            case R.id.tastytoastactivity://有趣的Toast
                startActivity(new Intent(mContext, TastyToastActivity.class));
                break;
            case R.id.shareactivity://三方分享
                startActivity(new Intent(mContext, ShareActivity.class));
                break;
            case R.id.galleryactivity://仿 【即刻】 app  首页滚动效果
                startActivity(new Intent(mContext, GalleryActivity.class), aoc.toBundle());
                break;
            case R.id.materialloginactivity://MD风格的登陆注册
                startActivity(new Intent(mContext, MaterialLoginActivity.class), aoc.toBundle());
                break;
            case R.id.spinneractivity://自定义下拉框
                startActivity(new Intent(mContext, SpinnerActivity.class), aoc.toBundle());
                break;
            case R.id.snackbartopactivity://顶部Snackbar
                startActivity(new Intent(mContext, SnackbarTopActivity.class), aoc.toBundle());
                break;
            case R.id.editanimactivity://修改个人资料--编辑框右侧滑出动画
                startActivity(new Intent(mContext, EditAnimActivity.class), aoc.toBundle());
                break;
            case R.id.specialprogressbaractivity://一个炫酷的进度条效果实现
                startActivity(new Intent(mContext, SpecialProgressBarActivity.class), aoc.toBundle());
                break;


            // FIXME: 2016/8/2 
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
}


