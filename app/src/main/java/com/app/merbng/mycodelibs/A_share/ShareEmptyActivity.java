package com.app.merbng.mycodelibs.A_share;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.merbng.mycodelibs.A_TumbleEditText.utils.ToastUtil;

import com.app.merbng.mycodelibs.Constent;
import com.app.merbng.mycodelibs.R;
import com.app.merbng.mycodelibs.interfaces.GetCallBack;
import com.app.merbng.mycodelibs.model.ShareBean;
import com.app.merbng.mycodelibs.utils.AppSystemUtils;
import com.app.merbng.mycodelibs.utils.ImageUtils;
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
import com.tencent.connect.common.Constants;
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
 * 提供一个透明的activity。 回调成功 关闭此页。
 * 目的是 收到 分享后 反馈
 * Created by Merbng on 2016/12/28.
 */

@SuppressWarnings("CheckStyle")
public class ShareEmptyActivity extends Activity implements IWeiboHandler.Response, View.OnClickListener {
    private Context mContext;
    // 分享到新浪微博
    private IWeiboShareAPI mWeiboShareAPI;
    private SsoHandler mSsoHandler;
    private Oauth2AccessToken mAccessToken; //提供Oauth2认证功能
    private ShareBean shareBean;
    private TextView tvCollect;
    private ImageView imgCollect;
    private boolean isSave;
    private Tencent mTencent;
    private IUiListener mIuListener;
    private BottomSheetDialog shareBottomSheetDialog;

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = ShareEmptyActivity.this;
        fvb();
    }

    /**
     * 初始化
     */
    public void fvb() {
        shareBean = (ShareBean) getIntent().getSerializableExtra("shareBean");
        shareBottomSheetDialog = new BottomSheetDialog(mContext, R.style.transparent_bg_dialog);
        View shareLinearLayout = LayoutInflater.from(mContext).inflate(R.layout.dialog_share, null);
        shareLinearLayout.findViewById(R.id.share_to_sinaweibo).setOnClickListener(this);
        shareLinearLayout.findViewById(R.id.share_to_weixin_friends).setOnClickListener(this);
        shareLinearLayout.findViewById(R.id.share_to_weixin_quan).setOnClickListener(this);
        shareLinearLayout.findViewById(R.id.share_to_qq).setOnClickListener(this);
        shareLinearLayout.findViewById(R.id.share_to_qqzone).setOnClickListener(this);
        tvCollect = (TextView) shareLinearLayout.findViewById(R.id.tv_collect);
        imgCollect = (ImageView) shareLinearLayout.findViewById(R.id.img_collect);
        LinearLayout llDelete = (LinearLayout) shareLinearLayout.findViewById(R.id.share_to_delete);
        LinearLayout llReport = (LinearLayout) shareLinearLayout.findViewById(R.id.share_to_report);
/*        if (!UserUtils.isMe(shareBean.getUserId())) {
            llDelete.setVisibility(View.GONE);
            llReport.setVisibility(View.VISIBLE);
        } else { //自己
            llDelete.setVisibility(View.VISIBLE);
            llReport.setVisibility(View.GONE);
        }*/
        View tvNoLoginTips = shareLinearLayout.findViewById(R.id.tv_no_login_tips);
        LinearLayout llShareBottomFunction = (LinearLayout) shareLinearLayout.findViewById(R.id.ll_share_bottom_function);
        LinearLayout llShareCenterLine = (LinearLayout) shareLinearLayout.findViewById(R.id.ll_share_center_line);
//        如果是下载app  则隐藏 下面的功能
        if (shareBean.getType() == ShareBean.TYPE_SHARE_APP) {
            llShareBottomFunction.setVisibility(View.GONE);
            llShareCenterLine.setVisibility(View.GONE);
        } else {
            llShareBottomFunction.setVisibility(View.VISIBLE);
            llShareCenterLine.setVisibility(View.VISIBLE);
        }
        //是否登录
/*        if (UserUtils.isLogin()) { //已登录
            if (shareBean.getType() != ShareBean.TYPE_SHARE_APP) {
                llShareBottomFunction.setVisibility(View.VISIBLE);
            }
            tvNoLoginTips.setVisibility(View.GONE);
        } else {
            llShareBottomFunction.setVisibility(View.GONE);
            tvNoLoginTips.setVisibility(View.VISIBLE);
        }*/
        llDelete.setOnClickListener(this);
        shareLinearLayout.findViewById(R.id.share_to_collect).setOnClickListener(this);
        llReport.setOnClickListener(this);
        shareLinearLayout.findViewById(R.id.img_close).setOnClickListener(this);
        shareBottomSheetDialog.setContentView(shareLinearLayout);
/*        topicBean = new TopicBean();
        topicBean.setNickName(shareBean.getNickName());
        topicBean.setTopicId(shareBean.getTargetId());*/
        isSave = shareBean.isMySave();
        initShowCollect(tvCollect);

        mTencent = Tencent.createInstance(Constent.APPID_QQ, mContext); //qq分享初始化
        mIuListener = new IUiListener() {
            @Override
            public void onComplete(Object o) {
                ToastUtil.showInfo(mContext,mContext.getResources().getString(R.string.share_success));
                isClickQq = false;
                dis();
            }

            @Override
            public void onError(UiError uiError) {
                ToastUtil.showInfo(mContext,getResources().getString(R.string.share_fail));
            }

            @Override
            public void onCancel() {
                ToastUtil.showInfo(mContext,getResources().getString(R.string.share_cancel));
            }
        };
        shareBottomSheetDialog.show();
        shareBottomSheetDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                finish();
            }
        });
    }

    /**
     * dialog消失
     */
    private void dis() {
        if (shareBottomSheetDialog.isShowing()) {
            shareBottomSheetDialog.dismiss();
        }
    }

    /**
     * 点击的是否是qq
     * 不区分的话在onactivityResult里面每次都会走qq的
     */
    private boolean isClickQq = false;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.share_to_sinaweibo:
                queryUserCard(Constent.PLATFORM_SINA);
                break;
            case R.id.share_to_weixin_friends:
                queryUserCard(Constent.PLATFORM_WEIXIN);
                dis();
                break;
            case R.id.share_to_weixin_quan:
                queryUserCard(Constent.PLATFORM_WEIXIN_QUAN);
                dis();
                break;
            case R.id.share_to_qq:
                isClickQq = true;
                queryUserCard(Constent.PLATFORM_QQ);
                break;
            case R.id.share_to_qqzone:
                isClickQq = true;
                queryUserCard(Constent.PLATFORM_QZONE);
                dis();
                break;
            case R.id.share_to_delete: //删除
                ToastUtil.showInfo(mContext, "删除成功");
                dis();
                break;
            case R.id.share_to_collect://收藏
                ToastUtil.showInfo(mContext, "收藏成功");
                dis();
                break;
            case R.id.share_to_report: //举报
                ToastUtil.showInfo(mContext, "举报成功");
                dis();
                break;
            case R.id.img_close:
                dis();
                break;
        }
    }

    /**
     * 查询卡片
     *
     * @param platform 平台
     */
    private void queryUserCard(final String platform) {
        if (platform.equals(Constent.PLATFORM_SINA)) {
            ToastUtil.showInfo(mContext, "正在启动微博，请稍等");
            shareToSinaWeibo();
        } else if (platform.equals(Constent.PLATFORM_WEIXIN_QUAN)) { //朋友圈
            ToastUtil.showInfo(mContext, "正在启动微信，请稍等");
            shareToWeiXin(1, shareBean);
        } else if (platform.equals(Constent.PLATFORM_WEIXIN)) { //好友
            ToastUtil.showInfo(mContext, "正在启动微信，请稍等");
            shareToWeiXin(0, shareBean);
        } else if (platform.equals(Constent.PLATFORM_QQ)) {
            ToastUtil.showInfo(mContext, "正在启动QQ，请稍等");
            shareToQQ(shareBean);
        } else if (platform.equals(Constent.PLATFORM_QZONE)) {
            ToastUtil.showInfo(mContext, "正在启动QQ空间，请稍等");
            shareToQzone(shareBean);
        }
    }

    /**
     * 1.分享到新浪微博
     */
    private void shareToSinaWeibo() {
        AuthInfo mAuthInfo = new AuthInfo(this, Constent.SINA_APP_KEY, Constent.SINA_REDIRECT_URL, "all");
        mWeiboShareAPI = WeiboShareSDK.createWeiboAPI(this, Constent.SINA_APP_KEY);
        mWeiboShareAPI.registerApp();

        // 如果未安装微博客户端
        if (!mWeiboShareAPI.isWeiboAppInstalled()) {
            ToastUtil.showInfo(mContext,getResources().getString(R.string.tip_no_weibo_client));
            finish();
            return;
        }

        if (null == mSsoHandler) {
            mSsoHandler = new SsoHandler(this, mAuthInfo);
        }
        // 第一次启动本应用，AccessToken 不可用
        mAccessToken = getCurrentUserSinaToken();
        if (null != mAccessToken && mAccessToken.isSessionValid()) {
            reqMsg(ShareEmptyActivity.this);
        } else {
            mSsoHandler.authorize(new AuthListener());
        }
    }

    /**
     * 2.分享到微信（朋友圈）
     *
     * @param platform  1:朋友圈   0:好友
     * @param shareBean
     */
    private void shareToWeiXin(final int platform, final ShareBean shareBean) {
        // 微信注册初始化
        final IWXAPI api = WXAPIFactory.createWXAPI(mContext, Constent.WXAPP_ID, true);
        api.registerApp(Constent.WXAPP_ID);
        if (!api.isWXAppInstalled()) {
            ToastUtil.showInfo(mContext, getResources().getString(R.string.tip_no_weixin_client));
            return;
        }
        WXWebpageObject webpage = new WXWebpageObject();
        webpage.webpageUrl = getCardUrl();
        final WXMediaMessage msg = new WXMediaMessage(webpage);
        msg.title = shareTitleSet(shareBean); //标题
        msg.description = shareInduration(shareBean); //描述
        String cardCoverUrl = getCardCoverUrl(shareBean);
        if (cardCoverUrl != null) {
            ImageUtils.url2Bitmap(mContext, ImageUtils.getWeiXinThumbnialUrl(cardCoverUrl, 200, 200), new GetCallBack.GetCallBackInterface<Bitmap>() {
                @Override
                public void onSuccess(Bitmap o) {
                    if (o != null) {
                        msg.setThumbImage(o);
                    }
                    shareWeixinOption(msg, platform, api, shareBean);
                }

                @Override
                public void onFail(String s) {
                    ToastUtil.showInfo(mContext,"分享失败");
                }

            });
        } else {
            shareWeixinOption(msg, platform, api, shareBean);
        }
    }

    /**
     * 3.分享到qq
     *
     * @param shareBean 分享benan
     */
    public void shareToQQ(ShareBean shareBean) {
        final Bundle params = new Bundle();
        params.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_DEFAULT);
        params.putString(QQShare.SHARE_TO_QQ_TITLE, shareTitleSet(shareBean));
        params.putString(QQShare.SHARE_TO_QQ_SUMMARY, shareInduration(shareBean));
        params.putString(QQShare.SHARE_TO_QQ_TARGET_URL, getCardUrl());
        params.putString(QQShare.SHARE_TO_QQ_IMAGE_URL, getCardCoverUrl(shareBean));
        params.putString(QQShare.SHARE_TO_QQ_APP_NAME, AppSystemUtils.getAppName(mContext));
        mTencent.shareToQQ(this, params, mIuListener);
    }

    /**
     * 4.分享到 qq空间
     *
     * @param shareBean 分享bean
     */
    public void shareToQzone(ShareBean shareBean) {
        final Bundle params = new Bundle();
        params.putInt(QzoneShare.SHARE_TO_QZONE_KEY_TYPE, QzoneShare.SHARE_TO_QZONE_TYPE_IMAGE_TEXT);
        params.putString(QzoneShare.SHARE_TO_QQ_TITLE, shareTitleSet(shareBean));
        params.putString(QzoneShare.SHARE_TO_QQ_SUMMARY, shareInduration(shareBean));
        params.putString(QzoneShare.SHARE_TO_QQ_TARGET_URL, getCardUrl());
        ArrayList<String> imageUrls = new ArrayList<>();
        String cardCoverUrl = getCardCoverUrl(shareBean);
        if (cardCoverUrl != null) {
            imageUrls.add(cardCoverUrl);
            params.putStringArrayList(QzoneShare.SHARE_TO_QQ_IMAGE_URL, imageUrls);
        }
        params.putInt(QzoneShare.SHARE_TO_QQ_EXT_INT, QQShare.SHARE_TO_QQ_FLAG_QZONE_AUTO_OPEN);
        mTencent.shareToQzone(this, params, mIuListener);
    }


    /**
     * 分享到微信的操作
     *
     * @param wxMediaMessage
     * @param platform
     * @param api
     * @param shareBean
     */
    private void shareWeixinOption(WXMediaMessage wxMediaMessage, int platform, IWXAPI api, ShareBean shareBean) {
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = String.valueOf(System.currentTimeMillis());
        req.message = wxMediaMessage;
        req.scene = platform;
        boolean b = api.sendReq(req);
        /*EventBusUtils.shareToWeixinOrCircle(type, shareBean.getTargetId());*/
        if (!b) {
            api.sendReq(req);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
        if (mSsoHandler != null) {
            mSsoHandler.authorizeCallBack(requestCode, resultCode, data);
        }

        if (mIuListener != null && isClickQq) {
            Tencent.onActivityResultData(requestCode, resultCode, data, mIuListener);
            if (requestCode == Constants.REQUEST_API) {
                if (resultCode == Constants.REQUEST_QQ_SHARE
                        || resultCode == Constants.REQUEST_QZONE_SHARE
                        || resultCode == Constants.REQUEST_OLD_SHARE) {
                    Tencent.handleResultData(data, mIuListener);
                    isClickQq = false;
                }
            }
        }
    }

    @Override
    public void onResponse(BaseResponse baseResponse) {
        if (baseResponse != null) {
            switch (baseResponse.errCode) {
                case WBConstants.ErrorCode.ERR_OK:
                    //统计分享次数
                    /*TopicUtils.recordShare(shareBean.getTargetId(), TopicUtils.TYPE_SINA);*/
                    ToastUtil.showInfo(mContext,getResources().getString(R.string.share_success));
                    break;
                case WBConstants.ErrorCode.ERR_CANCEL:
                    ToastUtil.showInfo(mContext,getResources().getString(R.string.share_cancel));
                    break;
                case WBConstants.ErrorCode.ERR_FAIL:
                    ToastUtil.showInfo(mContext,getResources().getString(R.string.share_fail));
                    break;
                default:
                    break;
            }
        }
        finish();
    }

    /**
     * 新浪微博分享登陆回调
     */
    private class AuthListener implements WeiboAuthListener {

        @Override
        public void onComplete(Bundle values) {
            // 从 Bundle 中解析 Token
            mAccessToken = Oauth2AccessToken.parseAccessToken(values);

            if (mAccessToken.isSessionValid()) {
                //保存Token到SharePreferences
                reqMsg(ShareEmptyActivity.this);
            }

        }

        @Override
        public void onCancel() {
            ToastUtil.showInfo(mContext,getResources().getString(R.string.share_cancel));
            finish();
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
    private WeiboMultiMessage multmess;

    /**
     * @param mContext 上下文
     */
    public void reqMsg(final Context mContext) {
        if (shareBean == null) {

            return;
        }
        String cardCoverUrl = getCardCoverUrl(shareBean);
        multmess = new WeiboMultiMessage();
        if (cardCoverUrl != null) { //有封面图
            ImageUtils.url2Bitmap(mContext, cardCoverUrl, new GetCallBack.GetCallBackInterface<Bitmap>() {
                @Override
                public void onSuccess(Bitmap o) {
                    /*微博数据的message对象*/
                    if (o != null) {
                        Bitmap bitmap = o;
                        /*图片对象*/
                        ImageObject imageobj = new ImageObject();
                        if (bitmap != null) {
                            imageobj.setImageObject(bitmap);
                        }
                        multmess.imageObject = imageobj;
                    }
                    /*微博发送的Request请求*/
                    shareSinaOption(multmess, mContext);
                }

                @Override
                public void onFail(String s) {
                    ToastUtil.showInfo(mContext,"取消分享");
                    finish();
                }
            });
        } else { //无封面图
            shareSinaOption(multmess, mContext);
        }
    }

    /**
     * 分享微博的操作
     * 有无封面图 -分开调用
     *
     * @param multmess
     * @param mContext
     */
    private void shareSinaOption(WeiboMultiMessage multmess, Context mContext) {
        TextObject textobj = new TextObject();
        textobj.text = getShareSinaWeiboContent(mContext, shareBean);
        multmess.textObject = textobj;
        SendMultiMessageToWeiboRequest multRequest = new SendMultiMessageToWeiboRequest();
        multRequest.multiMessage = multmess;
        //以当前时间戳为唯一识别符
        multRequest.transaction = String.valueOf(System.currentTimeMillis());
        mWeiboShareAPI.sendRequest(ShareEmptyActivity.this, multRequest);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        mWeiboShareAPI.handleWeiboResponse(intent, this);
    }

    /**
     * 获取当前帖子封面路径
     *
     * @param shareBean
     * @return
     */
    private String getCardCoverUrl(ShareBean shareBean) {
        String url = null;
        if (shareBean != null && shareBean.getCoverUrl() != null) {
            url = shareBean.getCoverUrl();
        }
        return url;
    }

    /**
     * 获取当前卡片详情url
     * 分享专用
     *
     * @return
     */
    public String getCardUrl() {
        if (shareBean.getType() == ShareBean.TYPE_SHARE_TOPIC) { //分享卡片
            return Constent.SHARE_URL + shareBean.getTargetId();
        } else if (shareBean.getType() == ShareBean.TYPE_SHARE_APP) { //分享下载APP
            return Constent.SHARE_APP;
        }
        return "";
    }

    /**
     * @param mContext
     * @param shareBean
     * @return
     */
    private String getShareSinaWeiboContent(Context mContext, ShareBean shareBean) {
        //推荐专辑：专辑名字 描述 详情链接:ssssssssssssss
        String shareTitle = shareBean.getTitle();
        if (shareTitle != null && !shareTitle.equals("")) {
            if (shareTitle.length() > Constent.SINA_CONTENT_CNT) {
                shareTitle = shareTitle.substring(0, Constent.SINA_CONTENT_CNT) + "…";
            }
        } else {
            shareTitle = mContext.getResources().getString(R.string.app_slogan2);
        }

/*        int type = shareBean.getType();
        switch (type) {
            case ShareBean.TYPE_SHARE_TOPIC:
                title =  title + " 「" + introductionOrFolder + "」";
                break;
            case ShareBean.TYPE_SHARE_APP:
                title =  "「" + title + "」 " + introductionOrFolder;
                break;
            default:
                break;
        }*/
        // http://ac-7bgq5smq.clouddn.com/fzkNkyOsxROz4OAS7EoqLKB.jpg
        String f = shareTitle + shareBean.getIntroduction() + " 详情链接:" + getCardUrl();
        return f;
    }

    /**
     * 三方 分享出去的 标题
     *
     * @param shareBean
     * @return
     */
    public static String shareTitleSet(ShareBean shareBean) {
        return shareBean.getTitle();
    }

    /**
     * 三方 分享出去的 描述
     *
     * @param shareBean
     * @return
     */
    private static String shareInduration(ShareBean shareBean) {
        return shareBean.getIntroduction();
    }

    /**
     * 获取当前已登录的用户新浪token
     *
     * @return 当前已登录的用户新浪token
     */
    @Nullable
    public static Oauth2AccessToken getCurrentUserSinaToken() {
        Oauth2AccessToken token = null;
        // TODO: 2017/2/18 暂无数据库
     /*   String sinaToken = ConfigUtils.getCurrentUserSinaToken(DmiApplication.getSCApplication().getApplicationContext());
        if (sinaToken.isEmpty()) {
            List<User> lu = DmiApplication.getSCApplication().getDaoSession().getUserDao().queryBuilder().limit(1).where(UserDao.Properties.LoginType.eq(1)).list();
            if (lu == null || lu.size() == 0) {
                return null;
            } else {
                if (sinaToken.isEmpty()){
                    return null;
                }
                sinaToken = lu.get(0).getSinaToken();
                ConfigUtils.setCurrentUserSinaToken(DmiApplication.getSCApplication().getApplicationContext(),sinaToken);
                try {
                    JSONObject jsonObject = new JSONObject(sinaToken);
                    if (jsonObject.has("token")) {
                        token = new Oauth2AccessToken();
                        token.setUid(jsonObject.getString("uid"));
                        token.setToken(jsonObject.getString("token"));
                        token.setExpiresTime(jsonObject.getLong("expirestime"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }else {
            try {
                JSONObject jsonObject = new JSONObject(sinaToken);
                if (jsonObject.has("token")) {
                    token = new Oauth2AccessToken();
                    token.setUid(jsonObject.getString("uid"));
                    token.setToken(jsonObject.getString("token"));
                    token.setExpiresTime(jsonObject.getLong("expirestime"));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
*/
        return token;
    }


    /**
     * 收藏了显示：取消收藏
     * 未收藏显示：收藏
     */
    private void initShowCollect(TextView tvCollect) {
//        查询是否已经收藏
        if (isSave) { //收藏了显示：取消收藏
            tvCollect.setText("取消收藏");
            imgCollect.setSelected(true);
        } else {
            tvCollect.setText("收藏");
            imgCollect.setSelected(false);
        }
    }

    @Override
    public void finish() {
        super.finish();
        //关闭窗体动画显示
        this.overridePendingTransition(R.anim.bottom_out, 0);
    }
}
