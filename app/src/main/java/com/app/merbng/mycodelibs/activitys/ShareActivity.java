package com.app.merbng.mycodelibs.activitys;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.app.merbng.mycodelibs.Constent;
import com.app.merbng.mycodelibs.R;
import com.app.merbng.mycodelibs.base.BaseActivity;
import com.app.merbng.mycodelibs.beans.MIUser_Card_Folder;
import com.app.merbng.mycodelibs.interfaces.GetCallBack;
import com.app.merbng.mycodelibs.utils.AppSystemUtils;
import com.app.merbng.mycodelibs.utils.BitmapUtils;
import com.sina.weibo.sdk.api.ImageObject;
import com.sina.weibo.sdk.api.TextObject;
import com.sina.weibo.sdk.api.WeiboMultiMessage;
import com.sina.weibo.sdk.api.share.BaseResponse;
import com.sina.weibo.sdk.api.share.IWeiboHandler;
import com.sina.weibo.sdk.api.share.IWeiboShareAPI;
import com.sina.weibo.sdk.api.share.SendMultiMessageToWeiboRequest;
import com.sina.weibo.sdk.api.share.WeiboShareSDK;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WeiboAuthListener;
import com.sina.weibo.sdk.auth.sso.SsoHandler;
import com.sina.weibo.sdk.constant.WBConstants;
import com.sina.weibo.sdk.exception.WeiboException;
import com.tencent.connect.share.QQShare;
import com.tencent.connect.share.QzoneShare;
import com.tencent.mm.sdk.modelmsg.SendMessageToWX;
import com.tencent.mm.sdk.modelmsg.WXMediaMessage;
import com.tencent.mm.sdk.modelmsg.WXWebpageObject;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import java.util.ArrayList;

/**
 * 第三方分享
 * 新浪微博
 * 腾讯qq&qq空间
 * 微信&微信朋友圈
 */
public class ShareActivity extends BaseActivity implements View.OnClickListener, IWeiboHandler.Response {
    private View popView;
    private PopupWindow popWindow;
    private MIUser_Card_Folder miUserCardFolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);
        fvbId();
        init();
    }

    private void init() {
        miUserCardFolder = new MIUser_Card_Folder();
//        qq分享的回调
        mIuListener = new IUiListener() {
            @Override
            public void onComplete(Object o) {
                show("分享成功");
            }

            @Override
            public void onError(UiError uiError) {
                show("分享失败");
            }

            @Override
            public void onCancel() {
                show("取消分享");
            }
        };
    }

    private void fvbId() {
        popView = LayoutInflater.from(mContext).inflate(R.layout.layout_share, null);
        popView.findViewById(R.id.share_to_sinaweibo).setOnClickListener(this);
        popView.findViewById(R.id.share_to_weixin_friends).setOnClickListener(this);
        popView.findViewById(R.id.share_to_qq).setOnClickListener(this);
        popView.findViewById(R.id.share_to_weixin_quan).setOnClickListener(this);
        popView.findViewById(R.id.share_to_qqzone).setOnClickListener(this);
        popView.findViewById(R.id.copylink).setOnClickListener(this);
        popView.findViewById(R.id.refresh_card).setOnClickListener(this);
        popView.findViewById(R.id.share_by_system).setOnClickListener(this);
        popView.findViewById(R.id.jubao_delete).setOnClickListener(this);
        popView.findViewById(R.id.share_create_qrcode).setOnClickListener(this);
        popView.findViewById(R.id.textView_cancel).setOnClickListener(this);
        popWindow = new PopupWindow(popView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popWindow.setBackgroundDrawable(new BitmapDrawable());
        popWindow.setAnimationStyle(R.style.popwindowStyle);
        popView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (popWindow != null && popWindow.isShowing()) {
                    popWindow.dismiss();
                }
                return false;
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.share_to_sinaweibo:
                queryUserCard(Constent.platform_Sina, miUserCardFolder);
                closePopWindow(popWindow);
                break;
            case R.id.share_to_weixin_friends:
                queryUserCard(Constent.platform_Weixin, miUserCardFolder);
                closePopWindow(popWindow);
                break;
            case R.id.share_to_qq:
                queryUserCard(Constent.platform_QQ, miUserCardFolder);
                closePopWindow(popWindow);
                break;
            case R.id.share_to_weixin_quan:
                queryUserCard(Constent.platform_WeixinQuan, miUserCardFolder);
                closePopWindow(popWindow);
                break;
            case R.id.share_to_qqzone:
                queryUserCard(Constent.platform_Qzone, miUserCardFolder);
                closePopWindow(popWindow);
                break;
            case R.id.copylink:
                android.content.ClipboardManager clipboard = (android.content.ClipboardManager) mContext
                        .getSystemService(Context.CLIPBOARD_SERVICE);

                clipboard.setPrimaryClip(ClipData.newPlainText(null, getShareUrl()));
                closePopWindow(popWindow);
                show("已复制");
                break;
            case R.id.share_by_system:
                queryUserCard(Constent.SYSTEM_SHARE, miUserCardFolder);
                closePopWindow(popWindow);
                break;
            case R.id.share_create_qrcode://生成二维码
                show("二维码");
                closePopWindow(popWindow);
                break;
            case R.id.jubao_delete:
                show("举报");
                break;
            case R.id.textView_cancel:
                if (popWindow != null && popWindow.isShowing()) {
                    popWindow.dismiss();
                }
                break;
            case R.id.refresh_card://刷新
                show("刷新");
                if (popWindow != null && popWindow.isShowing()) {
                    popWindow.dismiss();
                }
                break;
        }
    }

    /**
     * 关闭Popwindow
     *
     * @param popWindow
     */
    private void closePopWindow(PopupWindow popWindow) {
        if (popWindow != null && popWindow.isShowing()) {
            popWindow.dismiss();
        }
    }

    // 查询卡片
    private void queryUserCard(final String platform, MIUser_Card_Folder miUserCardFolder) {
        if (platform.equals(Constent.SYSTEM_SHARE)) {
            shareBySystem(miUserCardFolder);
        } else if (platform.equals(Constent.platform_Sina)) {
            show("正在启动微博，请稍等");
            shareToSinaWeibo();
        } else if (platform.equals(Constent.platform_WeixinQuan)) {//朋友圈
            show("正在启动微信，请稍等");
            shareToWeiXin(1, miUserCardFolder);
        } else if (platform.equals(Constent.platform_Weixin)) {//好友
            show("正在启动微信，请稍等");
            shareToWeiXin(0, miUserCardFolder);
        } else if (platform.equals(Constent.platform_QQ)) {
            show("正在启动QQ，请稍等");
            shareToQQ(miUserCardFolder);
        } else if (platform.equals(Constent.platform_Qzone)) {
            show("正在启动QQ空间，请稍等");
            shareToQzone(miUserCardFolder);
        }
    }

    /**
     * 通过系统分享
     *
     * @param miUserCardFolder
     */
    private void shareBySystem(MIUser_Card_Folder miUserCardFolder) {
        String title = miUserCardFolder.getTitle();
        Intent intent_share2 = new Intent(Intent.ACTION_SEND);
        intent_share2.setType("text/plain");
        intent_share2.putExtra(Intent.EXTRA_TITLE, "来自[项目]");
        intent_share2.putExtra(Intent.EXTRA_TEXT, getShareContent(title, getShareUrl()));
        intent_share2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(Intent.createChooser(intent_share2, getTitle()));
    }

    private IWeiboShareAPI mWeiboShareAPI;
    private SsoHandler mSsoHandler;

    // 分享到新浪微博
    private void shareToSinaWeibo() {
        AuthInfo mAuthInfo = new AuthInfo(mContext, Constent.SINA_APP_KEY, Constent.SINA_REDIRECT_URL, "all");
        mWeiboShareAPI = WeiboShareSDK.createWeiboAPI(this, Constent.SINA_APP_KEY);
        mWeiboShareAPI.registerApp();
        mSsoHandler = new SsoHandler(ShareActivity.this, mAuthInfo);
        mSsoHandler.authorize(new SinaAuthListener());
    }


    /**
     * 新浪微博分享登陆回调
     */
    class SinaAuthListener implements WeiboAuthListener {

        @Override
        public void onComplete(Bundle values) {
            // 从 Bundle 中解析 Token
            Oauth2AccessToken mAccessToken = Oauth2AccessToken.parseAccessToken(values);
            if (mAccessToken.isSessionValid()) {
                reqMsg(mContext);
            }
        }

        @Override
        public void onCancel() {
            show("取消分享");
        }

        @Override
        public void onWeiboException(WeiboException e) {
            e.printStackTrace();
        }
    }

    /**
     * 新浪微博分享的请求
     *
     * @param mContext
     */
    public void reqMsg(final Context mContext) {
        BitmapUtils.url2Bitmap(mContext, getCardCoverUrl(miUserCardFolder), new GetCallBack.GetCallBackInterface() {
            @Override
            public void onSuccess(Object o) {
                Bitmap bitmap = (Bitmap) o;
                   /*图片对象*/
                ImageObject imageobj = new ImageObject();
                if (bitmap != null) {
                    imageobj.setImageObject(bitmap);
                }
                /*微博数据的message对象*/
                WeiboMultiMessage multmess = new WeiboMultiMessage();
                TextObject textobj = new TextObject();
                String cardUrl = getCardCoverUrl(miUserCardFolder);
                textobj.text = getShareContent(getShareTitle(miUserCardFolder), cardUrl);
                multmess.textObject = textobj;
                multmess.imageObject = imageobj;
                /*微博发送的Request请求*/
                SendMultiMessageToWeiboRequest multRequest = new SendMultiMessageToWeiboRequest();
                multRequest.multiMessage = multmess;
                //以当前时间戳为唯一识别符
                multRequest.transaction = String.valueOf(System.currentTimeMillis());
                mWeiboShareAPI.sendRequest(ShareActivity.this, multRequest);
            }

            @Override
            public void onFail(String s) {
                show("分享失败");
            }
        });
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        mWeiboShareAPI.handleWeiboResponse(intent, this);
    }

    /**
     * 新浪微博分享回调
     *
     * @param baseResponse
     */
    @Override
    public void onResponse(BaseResponse baseResponse) {
        if (baseResponse != null) {
            switch (baseResponse.errCode) {
                case WBConstants.ErrorCode.ERR_OK:
                    show("分享成功");
                    break;
                case WBConstants.ErrorCode.ERR_CANCEL:
                    show("取消分享");
                    break;
                case WBConstants.ErrorCode.ERR_FAIL:
                    show("分享失败");
                    break;
            }
        }
    }

    private IWXAPI api;

    /**
     * 微信分享
     *
     * @param platform 1:朋友圈   0:好友
     *                 在 包名 WXEntryActivity 中回调
     */
    private void shareToWeiXin(final int platform, MIUser_Card_Folder miUser_card_folder) {
        // 微信注册初始化
        api = WXAPIFactory.createWXAPI(this, Constent.WXAPP_ID, true);
        api.registerApp(Constent.WXAPP_ID);
        if (!api.isWXAppInstalled()) {
            show("您还未安装微信客户端");
            return;
        }
        WXWebpageObject webpage = new WXWebpageObject();
        webpage.webpageUrl = getShareUrl();
        final WXMediaMessage msg = new WXMediaMessage(webpage);
        msg.title = getShareTitle(miUser_card_folder);//标题
        msg.description = getShareTitle(miUser_card_folder) + getShareUrl();//描述
        BitmapUtils.url2Bitmap(mContext, getCardCoverUrl(miUser_card_folder), new GetCallBack.GetCallBackInterface() {
            @Override
            public void onSuccess(Object o) {
                Bitmap thumb = (Bitmap) o;
                msg.setThumbImage(thumb);
                SendMessageToWX.Req req = new SendMessageToWX.Req();
                req.transaction = String.valueOf(System.currentTimeMillis());
                req.message = msg;
                req.scene = platform;
                api.sendReq(req);
            }

            @Override
            public void onFail(String s) {
                show("分享失败");
            }
        });
    }

    /**
     * 分享到qq
     *
     * @param avCard
     */
    private Tencent mTencent;
    private IUiListener mIuListener;

    public void shareToQQ(MIUser_Card_Folder avCard) {
        mTencent = Tencent.createInstance(Constent.APPID_QQ, this.getApplicationContext());//qq分享初始化
        final Bundle params = new Bundle();
        params.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_DEFAULT);
        params.putString(QQShare.SHARE_TO_QQ_TITLE, getShareTitle(avCard));
        params.putString(QQShare.SHARE_TO_QQ_SUMMARY, "来自[项目]");
        params.putString(QQShare.SHARE_TO_QQ_TARGET_URL, getShareUrl());
        params.putString(QQShare.SHARE_TO_QQ_IMAGE_URL, getCardCoverUrl(avCard));
        params.putString(QQShare.SHARE_TO_QQ_APP_NAME, AppSystemUtils.getAppName(mContext));
        mTencent.shareToQQ(this, params, mIuListener);
    }


    /**
     * 分享到 qq空间
     */
    public void shareToQzone(MIUser_Card_Folder miUser_card_folder) {
        final Bundle params = new Bundle();
        params.putInt(QzoneShare.SHARE_TO_QZONE_KEY_TYPE, QzoneShare.SHARE_TO_QZONE_TYPE_IMAGE_TEXT);
        params.putString(QzoneShare.SHARE_TO_QQ_TITLE, getShareTitle(miUser_card_folder));
        params.putString(QzoneShare.SHARE_TO_QQ_SUMMARY, "来自[项目]");
        params.putString(QzoneShare.SHARE_TO_QQ_TARGET_URL, getShareUrl());
        ArrayList<String> imageUrls = new ArrayList<>();
        imageUrls.add(getCardCoverUrl(miUser_card_folder));
        params.putStringArrayList(QzoneShare.SHARE_TO_QQ_IMAGE_URL, imageUrls);
        params.putInt(QzoneShare.SHARE_TO_QQ_EXT_INT, QQShare.SHARE_TO_QQ_FLAG_QZONE_AUTO_OPEN);
        Tencent mTencent = Tencent.createInstance(Constent.APPID_QQ, mContext);
        mTencent.shareToQzone(ShareActivity.this, params, mIuListener);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //新浪微博分享
        if (mSsoHandler != null) {
            mSsoHandler.authorizeCallBack(requestCode, resultCode, data);
        }
        //qq分享
        if (mTencent != null) {
            mTencent.onActivityResultData(requestCode, resultCode, data, mIuListener);
        }
    }

    /**
     * 获取分享的url
     *
     * @return
     */
    public String getShareUrl() {
        String userCardId = miUserCardFolder.getUserCardId();
        return Constent.USERCARDFOLDERDETAIL_SHARE + userCardId;
    }

    /**
     * 获取分享的title
     *
     * @return
     */
    public String getShareTitle(MIUser_Card_Folder miUserCardFolder) {
        String title = null;
        if (miUserCardFolder != null) {
            title = miUserCardFolder.getTitle();
        }
        return title;
    }

    /**
     * 获取分享的内容
     *
     * @param title
     * @param cardContentUrl
     * @return
     */
    public static String getShareContent(String title, String cardContentUrl) {
        if (title != null && !title.equals("")) {
        } else {
            title = "卡片";
        }
        if (!title.equals("")) {
            if (title.length() > 75) {
                title = title.substring(0, 75) + "...";
            }
            title = "【" + title + "】";
        } else {
            title = "卡片详情：";
        }
        String f = title + cardContentUrl;
        return f;
    }

    // 获取封面路径
    public String getCardCoverUrl(MIUser_Card_Folder miCard) {
        String url = null;
        if (miCard.getCardCover() != null) {
            url = miCard.getCardCover();
        }
        return url;
    }
}
