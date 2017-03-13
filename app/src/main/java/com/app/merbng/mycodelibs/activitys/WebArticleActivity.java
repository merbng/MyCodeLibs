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
            case R.id.tv_classify_web_artal:
                startActivity(IntentUtils.startWebView(mContext, text_view_show_hide, "一个 TextView 完成显示全文与隐藏功能"));
                break;
        }
    }

    /**
     * 打开新页面（适配）
     *
     * @param target
     */
    ActivityOptionsCompat aoc;

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
