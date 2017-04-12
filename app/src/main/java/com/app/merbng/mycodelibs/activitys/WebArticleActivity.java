package com.app.merbng.mycodelibs.activitys;

import android.os.Bundle;
import android.view.View;

import com.app.merbng.mycodelibs.R;
import com.app.merbng.mycodelibs.base.BaseActivity;
import com.app.merbng.mycodelibs.utils.IntentUtils;
import com.jakewharton.scalpel.ScalpelFrameLayout;

/**
 * 网页文章
 */
public class WebArticleActivity extends BaseActivity {
//    一个 TextView 完成显示全文与隐藏功能
    private static final String text_view_show_hide = "http://p.codekk.com/detail/Android/timqi/CollapsibleTextView";
    private static final String text_pay_for_zhifubao_weixin = "http://p.codekk.com/detail/Android/mayubao/Android-Pay";
    private static final String text_nine_phoneView = "http://p.codekk.com/detail/Android/Idtk/IKNinePhotoView";
    private static final String text_drag_delete_view = "http://p.codekk.com/detail/Android/yilylong/ChannelTagView";
    private static final String text_fast_dex = "https://github.com/typ0520/fastdex";
    private static final String text_webView_leak = "http://www.jianshu.com/p/3a345d27cd42?utm_source=desktop&utm_medium=timeline";
    private static final String text_jni = "http://www.jcodecraeer.com/a/anzhuokaifa/2017/0401/7769.html";
    private static final String text_mediation_pattern = "http://www.jianshu.com/p/d2110986cb93";
    private static final String text_video_edit = "http://p.codekk.com/detail/Android/lizhifeng-sky/VideoEdit";
    private static final String text_android_lib = "http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2017/0216/7122.html";
    private static final String text_toolbar_use = "http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2017/0312/7667.html";
    private static final String text_translation_plug = "http://p.codekk.com/detail/Android/BolexLiu/ReciteWords";
    private static final String text_loadinglayout = "直接拿去用！每个App都会用到的LoadingLayout";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*http://www.jianshu.com/p/8ea577c60865*/
        View mainView = getLayoutInflater().inflate(R.layout.activity_web_article, null);
        ScalpelFrameLayout mScalpelFrameLayout = new ScalpelFrameLayout(this);
        mScalpelFrameLayout.addView(mainView);
        mScalpelFrameLayout.setLayerInteractionEnabled(true); //开启 3D 效果
//mScalpelFrameLayout.setDrawIds(true); //是否显示控件 id
//mScalpelFrameLayout.setDrawViews(false); //是否展示控件内容，默认为 true
//mScalpelFrameLayout.setChromeColor(Color.RED); //修改边框颜色
//mScalpelFrameLayout.setChromeShadowColor(Color.YELLOW); //修改阴影颜色
        setContentView(mScalpelFrameLayout);
    }
    public void btnClick(View view) {
        switch (view.getId()) {
            case R.id.tv_article_CollapsibleTextView:
                startActivity(IntentUtils.startWebView(mContext, text_view_show_hide, "一个 TextView 完成显示全文与隐藏功能"));
                break;
            case R.id.tv_pay_for_zhifubao_weixin:
                startActivity(IntentUtils.startWebView(mContext, text_pay_for_zhifubao_weixin, "支持微信和支付宝两种主流支付的集成库， 两行代码实现微信支付， 三行代码实现支付宝支付"));
                break;
            case R.id.tv_nine_phoneView:
                startActivity(IntentUtils.startWebView(mContext, text_nine_phoneView, "IKNinePhotoView 是一个开源的 Android 九宫格控件，可以自适应宽高主要用于满足九宫格图片展示器及选择器的需求。"));
                break;
            case R.id.tv_drag_delete_view:
                startActivity(IntentUtils.startWebView(mContext, text_drag_delete_view, "一个频道管理 view，可拖拽排序，滑动删除"));
                break;
            case R.id.tv_fast_dex:
                startActivity(IntentUtils.startWebView(mContext, text_fast_dex, "加快 apk 的编译速度"));
                break;
            case R.id.tv_webView_leak:
                startActivity(IntentUtils.startWebView(mContext, text_webView_leak, "Android WebView 使用漏洞"));
                break;
            case R.id.tv_text_jni:
                startActivity(IntentUtils.startWebView(mContext, text_jni, "Android Studio jni开发入门——看我就够了！"));
                break;
            case R.id.tv_mediation_pattern:
//           Android设计模式     http://www.jianshu.com/u/cb3133f5a1bd
                startActivity(IntentUtils.startWebView(mContext, text_mediation_pattern, "Android设计模式（十六）-中介者模式"));
                break;
            case R.id.tv_video_edit:
                startActivity(IntentUtils.startWebView(mContext, text_video_edit, "视频剪切 音频剪切 音视频合成 更换视频背景声音"));
                break;
            case R.id.tv_android_lib:
                startActivity(IntentUtils.startWebView(mContext, text_android_lib, "2017年初你绝对想尝试的25个新安卓库"));
                break;
            case R.id.tv_toolbar_use:
                startActivity(IntentUtils.startWebView(mContext, text_toolbar_use, "ToolBar使用心得(如何改变item的位置)"));
                break;
            case R.id.tv_translation_plug:
                startActivity(IntentUtils.startWebView(mContext, text_translation_plug, "这是一个 androidStudio 翻译与陌生单词记录插件"));
                break;
            case R.id.tv_loadinglayout:
                startActivity(IntentUtils.startWebView(mContext, text_loadinglayout, "直接拿去用！每个App都会用到的LoadingLayout"));
                break;
        }
    }
}
