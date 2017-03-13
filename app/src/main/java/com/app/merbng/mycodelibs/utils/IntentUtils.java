package com.app.merbng.mycodelibs.utils;

import android.content.Context;
import android.content.Intent;

import com.app.merbng.mycodelibs.activitys.WebViewActivity;

/**
 * Created by Merbng on 2017/3/13.
 */

public class IntentUtils {
    /**
     * 打开网页.
     *
     * @param context
     * @return
     */
    public static Intent startWebView(Context context, String url, String title) {
        Intent intent = new Intent(context, WebViewActivity.class);
        intent.putExtra(WebViewActivity.WEBURL, url);
        intent.putExtra(WebViewActivity.WEBTITLE, title);
        return intent;
    }
}
