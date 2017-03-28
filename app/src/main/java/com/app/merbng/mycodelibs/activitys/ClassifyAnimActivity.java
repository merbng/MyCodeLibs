package com.app.merbng.mycodelibs.activitys;

import android.os.Bundle;
import android.view.View;

import com.app.merbng.mycodelibs.A_3D.Layout3DActivity;
import com.app.merbng.mycodelibs.A_MagicFloatView.MagicFloatViewActivity;
import com.app.merbng.mycodelibs.A_MagicFloatView.exp1Barrang.BarrangActivity;
import com.app.merbng.mycodelibs.A_MagicFloatView.exp2DropItemAnimation.DropItemAnimationActivity;
import com.app.merbng.mycodelibs.A_StudyProcessButton.ProcessButtonActivity;
import com.app.merbng.mycodelibs.A_TumbleEditText.TumbeEditTextActivity;
import com.app.merbng.mycodelibs.A_circularMenu.CircularMenuActivity;
import com.app.merbng.mycodelibs.A_edittextfirework.EditFireWorkActivity;
import com.app.merbng.mycodelibs.A_galleryview.GalleryActivity;
import com.app.merbng.mycodelibs.A_loadProgress.LoadProssActivity;
import com.app.merbng.mycodelibs.A_materiallogin.MaterialLoginActivity;
import com.app.merbng.mycodelibs.A_progress.SectionProgressBarActivity;
import com.app.merbng.mycodelibs.A_ticker.TickerActivity;
import com.app.merbng.mycodelibs.A_viewpagerCard.ViewPagerCardActivity;
import com.app.merbng.mycodelibs.R;
import com.app.merbng.mycodelibs.base.BaseActivity;
import com.app.merbng.mycodelibs.utils.AppSystemUtils;
import com.janggwa.zkw.golddrop.GoldAnimationActivity;

/**
 * 动画类
 */
public class ClassifyAnimActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classify_anim);
    }

    public void btnClick(View view) {
        switch (view.getId()) {
            case R.id.sectionprogressbaractivity://带节点的进度条
                AppSystemUtils.openActivity(thisActivity, SectionProgressBarActivity.class);
                break;
            case R.id.fadingtextviewactivity://每隔几秒自动更改其内容的textview
                AppSystemUtils.openActivity(thisActivity, FadingTextViewActivity.class);
                break;
            case R.id.dropitemanimationactivity://上升下降的效果
                AppSystemUtils.openActivity(thisActivity, DropItemAnimationActivity.class);
                break;
            case R.id.barrangactivity://气球上升的效果
                AppSystemUtils.openActivity(thisActivity, BarrangActivity.class);
                break;
            case R.id.circularmenuactivity://圆形扩散菜单
                AppSystemUtils.openActivity(thisActivity, CircularMenuActivity.class);
                break;
            case R.id.magicfloatviewactivity://自定义拓展漂浮路径的迷你版轻量级漂浮控件
                AppSystemUtils.openActivity(thisActivity, MagicFloatViewActivity.class);
                break;
            case R.id.startanimactivity://跳转  动画
                AppSystemUtils.openActivity(thisActivity, StartAnimActivity.class);
                break;
            case R.id.btn_zan:
                AppSystemUtils.openActivity(thisActivity, ZanActivity.class);
                break;
            case R.id.Shine_text://流光字
                AppSystemUtils.openActivity(thisActivity, ShineTextActivity.class);
                break;
            case R.id.loading:
                AppSystemUtils.openActivity(thisActivity, LoadingActivity.class);
                break;
            case R.id.search:
                AppSystemUtils.openActivity(thisActivity, SearchActivity.class);
                break;
            case R.id.toast:
                AppSystemUtils.openActivity(thisActivity, ToastActivity.class);
                break;
            case R.id.createcircular://圆形缩放
                AppSystemUtils.openActivity(thisActivity, CreateCircularActivity.class);
                break;
            case R.id.processbuttonactivity://进度条Button
                AppSystemUtils.openActivity(thisActivity, ProcessButtonActivity.class);
                break;
            case R.id.tickeractivityticker://滚动文本的简单的Android UI组件
                AppSystemUtils.openActivity(thisActivity, TickerActivity.class);
                break;
            case R.id.magicbuttonactivity://从侧边滑出的Button
                AppSystemUtils.openActivity(thisActivity, MagicButtonActivity.class);
                break;
            case R.id.tumbeedittextactivity://Android 3D立体无限旋转容器
                AppSystemUtils.openActivity(thisActivity, TumbeEditTextActivity.class);
                break;
            case R.id.autozoominimageview://一种图片不断向中间放大的动画效果
                AppSystemUtils.openActivity(thisActivity, AutozoomActivity.class);
                break;
            case R.id.kenburnsviewactivity://一张静止图片都有动态的效果感觉
                AppSystemUtils.openActivity(thisActivity, KenBurnsViewActivity.class);
                break;
            case R.id.glideactivity://引导
                AppSystemUtils.openActivity(thisActivity, GlideActivity.class);
                break;
            case R.id.tastytoastactivity://有趣的Toast
                AppSystemUtils.openActivity(thisActivity, TastyToastActivity.class);
                break;
            case R.id.galleryactivity://仿 【即刻】 app  首页滚动效果
                AppSystemUtils.openActivity(thisActivity, GalleryActivity.class);
                break;
            case R.id.materialloginactivity://MD风格的登陆注册
                AppSystemUtils.openActivity(thisActivity, MaterialLoginActivity.class);
                break;
            case R.id.snackbartopactivity://顶部Snackbar
                AppSystemUtils.openActivity(thisActivity, SnackbarTopActivity.class);
                break;
            case R.id.editanimactivity://修改个人资料--编辑框右侧滑出动画
                AppSystemUtils.openActivity(thisActivity, EditAnimActivity.class);
                break;
            case R.id.specialprogressbaractivity://一个炫酷的进度条效果实现
                AppSystemUtils.openActivity(thisActivity, SpecialProgressBarActivity.class);
                break;
            case R.id.goldanimationactivity://掉红包
                AppSystemUtils.openActivity(thisActivity, GoldAnimationActivity.class);
                break;
            case R.id.verticaldrawerlayoutactivity://竖直的DrawerLayout
                AppSystemUtils.openActivity(thisActivity, VerticalDrawerLayoutActivity.class);
                break;
            case R.id.revealcolorviewactivity://Banner动画
                AppSystemUtils.openActivity(thisActivity, RevealColorViewActivity.class);
                break;
            case R.id.buttonviewactivity://Button View
                AppSystemUtils.openActivity(thisActivity, ButtonViewActivity.class);
                break;
            case R.id.viewpagercardactivity://ViewPagerCard
                AppSystemUtils.openActivity(thisActivity, ViewPagerCardActivity.class);
                break;
            case R.id.bottommenuactivity://bottommenuactivity
                AppSystemUtils.openActivity(thisActivity, BottomMenuActivity.class);
                break;
            case R.id.meituanshopingactivity://仿美团  购物车动画
                AppSystemUtils.openActivity(thisActivity, MeiTuanShopingActivity.class);
                break;
            case R.id.beziercurveanimateractivity://把商品添加到购物车的动画效果（贝塞尔曲线）
                AppSystemUtils.openActivity(thisActivity, BeziercurveanimaterActivity.class);
                break;
            case R.id.zananimactivity://赞放大动画
                AppSystemUtils.openActivity(thisActivity, ZanAnimActivity.class);
                break;
            case R.id.switchbuttonactivity://夜间模式切换按钮
                AppSystemUtils.openActivity(thisActivity, SwitchButtonActivity.class);
                break;
            case R.id.bottomsheetdialogactivity://BottomSheet
                AppSystemUtils.openActivity(thisActivity, BottomSheetDialogActivity.class);
                break;
            case R.id.authcodecountdownactivity://验证码倒计时
                AppSystemUtils.openActivity(thisActivity, AuthCodeCountdownActivity.class);
                break;
            case R.id.circlemenuactivity://圆形菜单动画
                AppSystemUtils.openActivity(thisActivity, CircleMenuActivity.class);
                break;
            case R.id.editfireworkactivity://输入框动画
                AppSystemUtils.openActivity(thisActivity, EditFireWorkActivity.class);
                break;
            case R.id.loadprossactivity://加载进度条
                AppSystemUtils.openActivity(thisActivity, LoadProssActivity.class);
                break;
            case R.id.circleimageactivity://加载进度条
                AppSystemUtils.openActivity(thisActivity, CircleImageActivity.class);
                break;
            case R.id.randomtextviewactivity://滚动显示TextView的数字
                AppSystemUtils.openActivity(thisActivity, RandomTextViewActivity.class);
                break;
            case R.id.layout3dactivity://一秒让你的view拥有3D效果
                AppSystemUtils.openActivity(thisActivity, Layout3DActivity.class);
                break;
            case R.id.guideanimactivity://引导动画-过渡效果
                AppSystemUtils.openActivity(thisActivity, GuideAnimActivity.class);
                break;
        }
    }

    /**
     * 打开新页面（适配）
     *
     * @param target
     */


}
