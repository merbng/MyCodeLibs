package com.app.merbng.mycodelibs.activitys;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.ActivityOptionsCompat;
import android.transition.Explode;
import android.view.View;

import com.app.merbng.mycodelibs.A_3D.Layout3DActivity;
import com.app.merbng.mycodelibs.A_MagicFloatView.MagicFloatViewActivity;
import com.app.merbng.mycodelibs.A_StudyProcessButton.ProcessButtonActivity;
import com.app.merbng.mycodelibs.A_TumbleEditText.TumbeEditTextActivity;
import com.app.merbng.mycodelibs.A_WeatherAnimation.WeatherAnimationActivity;
import com.app.merbng.mycodelibs.A_drag_swip.DragSwipeActivity;
import com.app.merbng.mycodelibs.A_edittextfirework.EditFireWorkActivity;
import com.app.merbng.mycodelibs.A_galleryview.GalleryActivity;

import com.app.merbng.mycodelibs.A_loadProgress.LoadProssActivity;
import com.app.merbng.mycodelibs.A_materiallogin.MaterialLoginActivity;
import com.app.merbng.mycodelibs.A_recycleViewRefresh.StudyRecycleRefreshActivity;
import com.app.merbng.mycodelibs.A_studyRetrofit.StudyRetrofitActivity;
import com.app.merbng.mycodelibs.A_ticker.TickerActivity;
import com.app.merbng.mycodelibs.A_viewpagerCard.ViewPagerCardActivity;
import com.app.merbng.mycodelibs.A_windowManager.UcNotifyActivity;
import com.app.merbng.mycodelibs.R;
import com.app.merbng.mycodelibs.base.BaseActivity;
import com.app.merbng.mycodelibs.fragments.statefragment.StateColorActivity;
import com.app.merbng.mycodelibs.utils.AppFunctionUtils;
import com.app.merbng.mycodelibs.utils.DialogUtils;
import com.app.merbng.mycodelibs.utils.LogUtil;
import com.app.merbng.mycodelibs.widget.SplashView;
import com.hitomi.transferimage.TransMainActivity;
import com.janggwa.zkw.golddrop.GoldAnimationActivity;
import com.zhy.changeskin.SkinManager;

public class MainActivity extends BaseActivity {
    ActivityOptionsCompat aoc;
    public static final int MESSAGE_SHOW_TIPS = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
            case R.id.groupimageactivity://仿微博、微信、qq 点击缩略图, 显示高清图 UI 组件
                openActivity(TransMainActivity.class);
                break;
            case R.id.magicfloatviewactivity://自定义拓展漂浮路径的迷你版轻量级漂浮控件
                openActivity(MagicFloatViewActivity.class);
                break;
            case R.id.dragswipeactivity://类似支付宝应用管理界面
                openActivity(DragSwipeActivity.class);
                break;
            case R.id.btn_add_logo_qrCode://带logo的二维码
                openActivity(LogoQrCodeActivity.class);
                break;
            case R.id.btn_recycleView://recycleView
                openActivity(RecyclerViewActivity.class);
                break;
            case R.id.startanimactivity://跳转  动画
                openActivity(StartAnimActivity.class);
                break;
            case R.id.btn_TipShow:
                openActivity(TipShowActivity.class);
                break;
            case R.id.btn_titleBar:
                openActivity(TitleBarActivity.class);
                break;
            case R.id.btn_zan:
                openActivity(ZanActivity.class);
                break;
            case R.id.btn_viewpager:
                openActivity(TabActivity.class);
                break;
            case R.id.time_line:
                openActivity(TimeLineActivity.class);
                break;
            case R.id.Shine_text://流光字
                openActivity(ShineTextActivity.class);
                break;
            case R.id.loading:
                openActivity(LoadingActivity.class);
                break;
            case R.id.search:
                openActivity(SearchActivity.class);
                break;
            case R.id.toast:
                openActivity(ToastActivity.class);
                break;
            case R.id.createcircular://圆形缩放
                openActivity(CreateCircularActivity.class);
                break;
            case R.id.studyRecycleView://学习RecycleView
                openActivity(StudyRecycleActivity.class);
                break;
            case R.id.testGetConnection://测试Get请求
                openActivity(TestGetConnection.class);
                break;
            case R.id.studyretrofit://学习Retrofit
                openActivity(StudyRetrofitActivity.class);
                break;
            case R.id.recycleaddheardfootactivity://RecycleView加头加脚
                openActivity(StudyRecycleRefreshActivity.class);
                break;
            case R.id.appmsgactivity://AppMsgActivity顶部Toast
                openActivity(AppMsgActivity.class);
                break;
            case R.id.processbuttonactivity://进度条Button
                openActivity(ProcessButtonActivity.class);
                break;
            case R.id.qqloginactivity://登陆
                openActivity(QQLoginActivity.class);
                break;
            case R.id.sinaloginactivity://微博登陆
                openActivity(SinaLoginActivity.class);
                break;
            case R.id.weixinloginactivity://微信登陆
                openActivity(WeixinLoginActivity.class);
                break;
            case R.id.testedittextactivity://EditText值得注意的地方
                openActivity(TestEditTextActivity.class);
                break;
            case R.id.tickeractivityticker://滚动文本的简单的Android UI组件
                openActivity(TickerActivity.class);
                break;
            case R.id.magicbuttonactivity://从侧边滑出的Button
                openActivity(MagicButtonActivity.class);
                break;
            case R.id.tumbeedittextactivity://Android 3D立体无限旋转容器
                openActivity(TumbeEditTextActivity.class);
                break;
            case R.id.autozoominimageview://一种图片不断向中间放大的动画效果
                openActivity(AutozoomActivity.class);
                break;
            case R.id.kenburnsviewactivity://一张静止图片都有动态的效果感觉
                openActivity(KenBurnsViewActivity.class);
                break;
            case R.id.glideactivity://引导
                openActivity(GlideActivity.class);
                break;
            case R.id.qrcodeactivity://二维码生成/扫描
                openActivity(QrCodeActivity.class);
                break;
            case R.id.tastytoastactivity://有趣的Toast
                openActivity(TastyToastActivity.class);
                break;
            case R.id.shareactivity://三方分享
                openActivity(ShareActivity.class);
                break;
            case R.id.galleryactivity://仿 【即刻】 app  首页滚动效果
                openActivity(GalleryActivity.class);
                break;
            case R.id.materialloginactivity://MD风格的登陆注册
                openActivity(MaterialLoginActivity.class);
                break;
            case R.id.spinneractivity://自定义下拉框
                openActivity(SpinnerActivity.class);
                break;
            case R.id.snackbartopactivity://顶部Snackbar
                openActivity(SnackbarTopActivity.class);
                break;
            case R.id.editanimactivity://修改个人资料--编辑框右侧滑出动画
                openActivity(EditAnimActivity.class);
                break;
            case R.id.specialprogressbaractivity://一个炫酷的进度条效果实现
                openActivity(SpecialProgressBarActivity.class);
                break;
            case R.id.goldanimationactivity://掉红包
                openActivity(GoldAnimationActivity.class);
                break;
            case R.id.tipswindowactivity://仿360底部提示
                openActivity(TipsWindowActivity.class);
                break;
            case R.id.votecomparisonactivity://一个简单的投票排名对比图
                openActivity(VoteComparisonActivity.class);
                break;
            case R.id.verticaldrawerlayoutactivity://竖直的DrawerLayout
                openActivity(VerticalDrawerLayoutActivity.class);
                break;
            case R.id.textwatermarkactivity://图片加文字水印
                openActivity(TextWatermarkActivity.class);
                break;
            case R.id.suggestactivity://意见反馈
                openActivity(SuggestActivity.class);
                break;
            case R.id.revealcolorviewactivity://Banner动画
                openActivity(RevealColorViewActivity.class);
                break;
            case R.id.editdialogactivity://带编辑框的Dialog
                openActivity(EditDialogActivity.class);
                break;
            case R.id.materialdialogactivity://MD风格的Dialog
                openActivity(MaterialDialogActivity.class);
                break;
            case R.id.buttonviewactivity://Button View
                openActivity(ButtonViewActivity.class);
                break;
            case R.id.viewpagercardactivity://ViewPagerCard
                openActivity(ViewPagerCardActivity.class);
                break;
            case R.id.bottommenuactivity://bottommenuactivity
                openActivity(BottomMenuActivity.class);
                break;
            case R.id.ucnotifyactivity://仿Uc  监听粘贴板
                openActivity(UcNotifyActivity.class);
                break;
            case R.id.meituanshopingactivity://仿美团  购物车动画
                openActivity(MeiTuanShopingActivity.class);
                break;
            case R.id.beziercurveanimateractivity://把商品添加到购物车的动画效果（贝塞尔曲线）
                openActivity(BeziercurveanimaterActivity.class);
                break;
            case R.id.zananimactivity://赞放大动画
                openActivity(ZanAnimActivity.class);
                break;
            case R.id.switchbuttonactivity://夜间模式切换按钮
                openActivity(SwitchButtonActivity.class);
                break;
            case R.id.bottomsheetdialogactivity://BottomSheet
                openActivity(BottomSheetDialogActivity.class);
                break;
            case R.id.minutesagoactivity://几分钟后/几小时后
                openActivity(MinutesAgoActivity.class);
                break;
            case R.id.authcodecountdownactivity://验证码倒计时
                openActivity(AuthCodeCountdownActivity.class);
                break;
            case R.id.circlemenuactivity://圆形菜单动画
                openActivity(CircleMenuActivity.class);
                break;
            case R.id.editfireworkactivity://输入框动画
                openActivity(EditFireWorkActivity.class);
                break;
            case R.id.undoeditactivity://EditText的撤销和恢复（反撤销）
                openActivity(UndoEditActivity.class);
                break;
            case R.id.share://EditText的撤销和恢复（反撤销）
                AppFunctionUtils.share(MainActivity.this);
                break;
            case R.id.imgtextsoundactivity://图文环绕
                openActivity(ImgTextSoundActivity.class);
                break;
            case R.id.statecoloractivity://图文环绕
                openActivity(StateColorActivity.class);
                break;
            case R.id.loadprossactivity://加载进度条
                openActivity(LoadProssActivity.class);
                break;
            case R.id.circleimageactivity://加载进度条
                openActivity(CircleImageActivity.class);
                break;
            case R.id.weatheranimationactivity://下雪动画
                openActivity(WeatherAnimationActivity.class);
                break;
            case R.id.imagespanactivity://下雪动画
                openActivity(ImageSpanActivity.class);
                break;
            case R.id.randomtextviewactivity://滚动显示TextView的数字
                openActivity(RandomTextViewActivity.class);
                break;
            case R.id.layout3dactivity://一秒让你的view拥有3D效果
                openActivity(Layout3DActivity.class);
                break;
            case R.id.guideanimactivity://引导动画-过渡效果
                openActivity(GuideAnimActivity.class);
                break;
            case R.id.myqrcodeactivity://我的二维码
                Intent intent_qrcode = new Intent(mContext, MyQrCodeActivity.class);
                intent_qrcode.putExtra(MyQrCodeActivity.USERID_KEY, "https://github.com/Merbn");
                intent_qrcode.putExtra(MyQrCodeActivity.NICKNAME_KEY, "Merbng");
                intent_qrcode.putExtra(MyQrCodeActivity.COVER_KEY, "https://avatars3.githubusercontent.com/u/11256126?v=3&s=460");
                intent_qrcode.putExtra(MyQrCodeActivity.INTRO_KEY, "一直以来，不敢止步。");
                intent_qrcode.putExtra(MyQrCodeActivity.TYPE, MyQrCodeActivity.TYPE_HEARD);
                startActivity(intent_qrcode);
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


