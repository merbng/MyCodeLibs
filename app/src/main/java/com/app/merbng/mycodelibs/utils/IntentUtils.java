package com.app.merbng.mycodelibs.utils;

import android.content.Context;
import android.content.Intent;

import com.app.merbng.mycodelibs.A_share.ShareEmptyActivity;
import com.app.merbng.mycodelibs.activitys.WebViewActivity;
import com.app.merbng.mycodelibs.model.ShareBean;

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

    /**分享下载
     * @param mContext
     * @param nickName
     * @return
     */
    public static Intent startShareDialogForLinkActivity(Context mContext, String nickName) {
        Intent intent = new Intent(mContext, ShareEmptyActivity.class);
        ShareBean shareBean = new ShareBean();
        shareBean.setNickName(nickName);
        shareBean.setMySave(true);
        shareBean.setType(ShareBean.TYPE_SHARE_APP);
        shareBean.setTitle("来大米社区一起摇摆吧！");
        shareBean.setIntroduction(nickName+"邀请你加入！");
        intent.putExtra("shareBean", shareBean);
        return intent;
    }
}
