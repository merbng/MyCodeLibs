package com.app.merbng.mycodelibs.activitys;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.transition.Explode;
import android.view.View;

import com.app.merbng.mycodelibs.R;
import com.app.merbng.mycodelibs.base.BaseActivity;
import com.app.merbng.mycodelibs.utils.IntentUtils;

public class WebArticleActivity extends BaseActivity {
//    一个 TextView 完成显示全文与隐藏功能
    private static final String text_view_show_hide = "http://p.codekk.com/detail/Android/timqi/CollapsibleTextView";


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
        }
    }
}
