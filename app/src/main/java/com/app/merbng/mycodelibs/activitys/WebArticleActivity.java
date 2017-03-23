package com.app.merbng.mycodelibs.activitys;

import android.os.Bundle;
import android.view.View;

import com.app.merbng.mycodelibs.R;
import com.app.merbng.mycodelibs.base.BaseActivity;
import com.app.merbng.mycodelibs.utils.IntentUtils;

/**
 * 网页文章
 */
public class WebArticleActivity extends BaseActivity {
//    一个 TextView 完成显示全文与隐藏功能
    private static final String text_view_show_hide = "http://p.codekk.com/detail/Android/timqi/CollapsibleTextView";
    private static final String text_pay_for_zhifubao_weixin = "http://p.codekk.com/detail/Android/mayubao/Android-Pay";
    private static final String text_nine_phoneView = "http://p.codekk.com/detail/Android/Idtk/IKNinePhotoView";
    private static final String text_drag_delete_view = "http://p.codekk.com/detail/Android/yilylong/ChannelTagView";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_article);
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
        }
    }
}
