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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btnClick(View view) {
        //闪屏图
        showSplashUrl();
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
                Explode explode = new Explode();
                explode.setDuration(500);
                getWindow().setExitTransition(explode);
                getWindow().setEnterTransition(explode);
                ActivityOptionsCompat oc2 = ActivityOptionsCompat.makeSceneTransitionAnimation(this);
                startActivity(new Intent(mContext, GalleryActivity.class),oc2.toBundle());
                break;
            case R.id.materialloginactivity://MD风格的登陆注册
                Explode explode2 = new Explode();
                explode2.setDuration(500);
                getWindow().setExitTransition(explode2);
                getWindow().setEnterTransition(explode2);
                ActivityOptionsCompat oc = ActivityOptionsCompat.makeSceneTransitionAnimation(this);
                startActivity(new Intent(mContext, MaterialLoginActivity.class),oc.toBundle());
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

    private void showSplashUrl() {
        int Numround = (int) Math.round(Math.random() * 10);
        String url = null;
        String actionUrl = null;
        LogUtil.log.e("图：" + Numround);
        switch (Numround) {
            case 0:
                url = "http://f7.topitme.com/7/7f/70/1178561279d91707f7o.jpg";
                actionUrl = "http://f7.topitme.com";
                break;
            case 1:
                url = "http://ff.topitme.com/f/6a/90/1145105479248906afl.jpg";
                actionUrl = "http://f7.topitme.com";
                break;
            case 2:
                url = "http://f10.topitme.com/o/201101/21/12955798998303.jpg";
                actionUrl = "http://f7.topitme.com";
                break;
            case 3:
                url = "http://i10.topitme.com/l075/1007532813d981f7d7.jpg";
                actionUrl = "http://i10.topitme.com";
                break;
            case 4:
                url = "http://f10.topitme.com/l/200912/21/12613633952350.jpg";
                actionUrl = "http://i10.topitme.com";
                break;
            case 5:
                url = "https://avatars3.githubusercontent.com/u/11256126?v=3&s=460";
                actionUrl = "https://avatars3.githubusercontent.com";
                break;
            case 6:
                url = "http://imgsrc.baidu.com/forum/pic/item/530d8ad4b31c8701b0dc0207277f9e2f0608ff63.jpg";
                actionUrl = "http://imgsrc.baidu.com";
                break;
            case 7:
                url = "http://imgsrc.baidu.com/forum/pic/item/caef76094b36acaf62e089647cd98d1001e99c21.jpg";
                actionUrl = "http://imgsrc.baidu.com";
                break;
            case 8:
                url = "http://img4q.duitang.com/uploads/item/201503/21/20150321233324_eYQSE.jpeg";
                actionUrl = "http://img4q.duitang.com";
                break;
            case 9:
                url = "http://bizhi.zhuoku.com/2012/01/06/jingxuan/jingxuan063.jpg";
                actionUrl = "http://bizhi.zhuoku.com";
                break;
        }
        SplashView.updateSplashData(this, url, actionUrl);
    }
}


