package com.app.merbng.mycodelibs.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.app.merbng.mycodelibs.A_fitpopupwindow.FitPopupWindowActivity;
import com.app.merbng.mycodelibs.A_recycleViewRefresh.StudyRecycleRefreshActivity;
import com.app.merbng.mycodelibs.A_share.ShareEmptyActivity;
import com.app.merbng.mycodelibs.A_studyRetrofit.StudyRetrofitActivity;
import com.app.merbng.mycodelibs.A_windowManager.UcNotifyActivity;
import com.app.merbng.mycodelibs.R;
import com.app.merbng.mycodelibs.base.BaseActivity;
import com.app.merbng.mycodelibs.fragments.statefragment.StateColorActivity;
import com.app.merbng.mycodelibs.utils.AppFunctionUtils;
import com.app.merbng.mycodelibs.utils.AppSystemUtils;
import com.hitomi.transferimage.TransMainActivity;
import com.zhy.changeskin.SkinManager;

/**
 * 其他分类
 */
public class ClassifyOtherActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classify_other);
    }

    public void btnClick(View view) {
        switch (view.getId()) {
            case R.id.share_window://分享。
                AppSystemUtils.openActivity(thisActivity, ShareEmptyActivity.class);
                break;
            case R.id.verificationcodeinputactivity://仿微信、支付宝等简洁的验证码、密码输入框。
                AppSystemUtils.openActivity(thisActivity, VerificationCodeInputActivity.class);
                break;
            case R.id.parallaxactivity://层叠效果RecycleView
                AppSystemUtils.openActivity(thisActivity, ParallaxActivity.class);
                break;
            case R.id.fitpopupwindow://点击位置自适应弹出位置的PopupWindow
                AppSystemUtils.openActivity(thisActivity, FitPopupWindowActivity.class);
                break;
            case R.id.top_alert_activity://顶部Alert Dialog
                AppSystemUtils.openActivity(thisActivity, TopAlertActivity.class);
                break;
            case R.id.groupimageactivity://仿微博、微信、qq 点击缩略图, 显示高清图 UI 组件
                AppSystemUtils.openActivity(thisActivity, TransMainActivity.class);
                break;
            case R.id.editdialogactivity://带编辑框的Dialog
                AppSystemUtils.openActivity(thisActivity, EditDialogActivity.class);
                break;
            case R.id.materialdialogactivity://MD风格的Dialog
                AppSystemUtils.openActivity(thisActivity, MaterialDialogActivity.class);
                break;
            case R.id.appmsgactivity://AppMsgActivity顶部Toast
                AppSystemUtils.openActivity(thisActivity, AppMsgActivity.class);
                break;
            case R.id.share://分享
                AppFunctionUtils.share(mContext);
                break;
            case R.id.imgtextsoundactivity://图文环绕
                AppSystemUtils.openActivity(thisActivity, ImgTextSoundActivity.class);
                break;
            case R.id.imagespanactivity://图文
                AppSystemUtils.openActivity(thisActivity, ImageSpanActivity.class);
                break;
            case R.id.studyRecycleView://学习RecycleView
                AppSystemUtils.openActivity(thisActivity, StudyRecycleActivity.class);
                break;
            case R.id.testGetConnection://测试Get请求
                AppSystemUtils.openActivity(thisActivity, TestGetConnection.class);
                break;
            case R.id.studyretrofit://学习Retrofit
                AppSystemUtils.openActivity(thisActivity, StudyRetrofitActivity.class);
                break;
            case R.id.recycleaddheardfootactivity://RecycleView加头加脚
                AppSystemUtils.openActivity(thisActivity, StudyRecycleRefreshActivity.class);
                break;
            case R.id.qqloginactivity://登陆
                AppSystemUtils.openActivity(thisActivity, QQLoginActivity.class);
                break;
            case R.id.sinaloginactivity://微博登陆
                AppSystemUtils.openActivity(thisActivity, SinaLoginActivity.class);
                break;
            case R.id.weixinloginactivity://微信登陆
                AppSystemUtils.openActivity(thisActivity, WeixinLoginActivity.class);
                break;
            case R.id.testedittextactivity://EditText值得注意的地方
                AppSystemUtils.openActivity(thisActivity, TestEditTextActivity.class);
                break;
            case R.id.qrcodeactivity://二维码生成/扫描
                AppSystemUtils.openActivity(thisActivity, QrCodeActivity.class);
                break;
            case R.id.shareactivity://三方分享
                AppSystemUtils.openActivity(thisActivity, ShareActivity.class);
                break;
            case R.id.spinneractivity://自定义下拉框
                AppSystemUtils.openActivity(thisActivity, SpinnerActivity.class);
                break;
            case R.id.tipswindowactivity://仿360底部提示
                AppSystemUtils.openActivity(thisActivity, TipsWindowActivity.class);
                break;
            case R.id.votecomparisonactivity://一个简单的投票排名对比图
                AppSystemUtils.openActivity(thisActivity, VoteComparisonActivity.class);
                break;
            case R.id.textwatermarkactivity://图片加文字水印
                AppSystemUtils.openActivity(thisActivity, TextWatermarkActivity.class);
                break;
            case R.id.suggestactivity://意见反馈
                AppSystemUtils.openActivity(thisActivity, SuggestActivity.class);
                break;
            case R.id.ucnotifyactivity://仿Uc  监听粘贴板
                AppSystemUtils.openActivity(thisActivity, UcNotifyActivity.class);
                break;
            case R.id.minutesagoactivity://几分钟后/几小时后
                AppSystemUtils.openActivity(thisActivity, MinutesAgoActivity.class);
                break;
            case R.id.undoeditactivity://EditText的撤销和恢复（反撤销）
                AppSystemUtils.openActivity(thisActivity, UndoEditActivity.class);
                break;
            case R.id.statecoloractivity://状态栏颜色
                AppSystemUtils.openActivity(thisActivity, StateColorActivity.class);
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
