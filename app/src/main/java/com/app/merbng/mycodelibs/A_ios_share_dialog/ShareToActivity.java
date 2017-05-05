package com.app.merbng.mycodelibs.A_ios_share_dialog;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.merbng.mycodelibs.R;
import com.app.merbng.mycodelibs.beans.CommunityBean;
import com.app.merbng.mycodelibs.beans.TopicBean;

import de.greenrobot.event.EventBus;



public class ShareToActivity extends Activity {

    public static final String KEY_SHARE_TYPE = "key_share_type";
    public static final int TYPE_SHARE_TOPIC = 0;
    public static final int TYPE_SHARE_APP = 1;
    public static final int TYPE_SHARE_COMMUNITY = 2;
    private Context mContext;
    private int shareType;
    private TopicBean topicBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = ShareToActivity.this;
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }


        shareType = getIntent().getIntExtra(KEY_SHARE_TYPE, TYPE_SHARE_TOPIC);

        if (shareType == TYPE_SHARE_TOPIC) {//分享帖子
            shareDialog(mContext, topicBean);
        } else if (shareType == TYPE_SHARE_APP) { //分享app
//            TopicBean topicBean1 = new TopicBean();
//            String nickName = UserUtils.getCurrentUser().getNickName();
//            topicBean1.setNickName(nickName);
//            topicBean1.setType(ShareBean.TYPE_SHARE_APP);
//            topicBean1.setTitle("来大米社区一起摇摆吧！");
//            topicBean1.setContent(nickName + "邀请你加入！");
            shareDialog(mContext, topicBean);
        } else if (shareType == TYPE_SHARE_COMMUNITY) {
            shareDialog(mContext, topicBean);
        }
    }

    /**
     * 分享帖子/APp 的弹窗.
     *
     * @param mContext
     * @param topicBean
     */
    private static boolean isSave;

    public void shareDialog(final Context mContext, final TopicBean topicBean) {
        final BottomSheetDialog shareBottomSheetDialog = new BottomSheetDialog(mContext, R.style.transparent_bg_dialog);
        View shareLinearLayout = LayoutInflater.from(mContext).inflate(R.layout.dialog_share_ios, null);
        TextView tvShareTitle = (TextView) shareLinearLayout.findViewById(R.id.tv_share_title);
        TextView tvNoLoginTips = (TextView) shareLinearLayout.findViewById(R.id.tv_no_login_tips);
        //分享帖子&app 隐藏【社区的规则、简介】
        LinearLayout llShareBottomFunction = (LinearLayout) shareLinearLayout.findViewById(R.id.ll_share_bottom_function);
        if (shareType == TYPE_SHARE_APP) {//分享App
            tvShareTitle.setText("分享到");

        } else if (shareType == TYPE_SHARE_TOPIC) {
            tvShareTitle.setText("分享帖子到");

        }/*
        //是否登录
        if (UserUtils.isLogin()) { //已登录
            tvNoLoginTips.setVisibility(View.GONE);
            if (shareType == TYPE_SHARE_APP) {
                llShareBottomFunction.setVisibility(View.GONE);
            } else {
                llShareBottomFunction.setVisibility(View.VISIBLE);
            }
        } else {
            tvNoLoginTips.setVisibility(View.VISIBLE);
            llShareBottomFunction.setVisibility(View.GONE);
        }*/
        shareLinearLayout.findViewById(R.id.share_to_sinaweibo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ShareUtils.getInstance(mContext).share((Activity) mContext, TopicUtils.topicBean2ShareBean(topicBean, 4), 4);
//                dialogDis(shareBottomSheetDialog);
            }
        });
        shareLinearLayout.findViewById(R.id.share_to_weixin_friends).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ShareUtils.getInstance(mContext).share((Activity) mContext, TopicUtils.topicBean2ShareBean(topicBean, 0), 0);
//                dialogDis(shareBottomSheetDialog);
            }
        });
        shareLinearLayout.findViewById(R.id.share_to_weixin_quan).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ShareUtils.getInstance(mContext).share((Activity) mContext, TopicUtils.topicBean2ShareBean(topicBean, 1), 1);
//                dialogDis(shareBottomSheetDialog);
            }
        });
        shareLinearLayout.findViewById(R.id.share_to_qq).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ShareUtils.getInstance(mContext).share((Activity) mContext, TopicUtils.topicBean2ShareBean(topicBean, 2), 2);
            }
        });
        shareLinearLayout.findViewById(R.id.share_to_qqzone).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ShareUtils.getInstance(mContext).share((Activity) mContext, TopicUtils.topicBean2ShareBean(topicBean, 3), 3);
//                dialogDis(shareBottomSheetDialog);
            }
        });
        shareLinearLayout.findViewById(R.id.share_to_copy_link).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                TextsUtils.copyLinks(mContext, ShareUtils.getTopicUrl(TopicUtils.topicBean2ShareBean(topicBean, 1)));
                dialogDis(shareBottomSheetDialog);
            }
        });
//        收藏
        isSave = topicBean.isMySave();
        final TextView tvCollect = (TextView) shareLinearLayout.findViewById(R.id.tv_collect);
        final ImageView imgCollect = (ImageView) shareLinearLayout.findViewById(R.id.img_collect);
        initShowCollect(imgCollect, tvCollect);
        shareLinearLayout.findViewById(R.id.ll_collect).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 dialogDis(shareBottomSheetDialog);
            }
        });
//        举报
        LinearLayout llDelete = (LinearLayout) shareLinearLayout.findViewById(R.id.ll_delete);
        LinearLayout llReport = (LinearLayout) shareLinearLayout.findViewById(R.id.ll_report);
/*        if (UserUtils.isMe(topicBean.getUserId())) {
            llDelete.setVisibility(View.VISIBLE);
            llReport.setVisibility(View.GONE);
        } else {//别人
            llDelete.setVisibility(View.GONE);
            llReport.setVisibility(View.VISIBLE);
        }*/
        llReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                mContext.startActivity(IntentUtils.startReportActivity(mContext, topicBean));
                dialogDis(shareBottomSheetDialog);
            }
        });
//        删除
        llDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteOption(mContext, topicBean.getTopicId());
                dialogDis(shareBottomSheetDialog);
            }
        });
        shareLinearLayout.findViewById(R.id.img_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogDis(shareBottomSheetDialog);
            }
        });
        shareBottomSheetDialog.setContentView(shareLinearLayout);
        shareBottomSheetDialog.show();
        shareBottomSheetDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                finish();
            }
        });
    }

    /**
     * 删除话题.
     */
    private static void deleteOption(final Context mContext, final String topicId) {
        // 删除话题
    }

    /**
     * 收藏了显示：取消收藏
     * 未收藏显示：收藏.
     *
     * @param tvCollect
     */
    private static void initShowCollect(ImageView imgCollect, TextView tvCollect) {
//        查询是否已经收藏
        if (isSave) { //收藏了显示：取消收藏
            tvCollect.setText("取消收藏");
            imgCollect.setSelected(true);
        } else {
            tvCollect.setText("收藏");
            imgCollect.setSelected(false);
        }
    }

    /**
     * 分享社区的弹窗.
     *
     * @param mContext
     * @param communityBean
     */
    boolean isDissDialog = false;

    public void shareDialog(final Context mContext, final CommunityBean communityBean) {
        final BottomSheetDialog shareBottomSheetDialog = new BottomSheetDialog(mContext, R.style.transparent_bg_dialog);
        View shareLinearLayout = LayoutInflater.from(mContext).inflate(R.layout.dialog_share_community, null);
        TextView tvShareTitle = (TextView) shareLinearLayout.findViewById(R.id.tv_share_title);
        TextView tvNoLoginTips = (TextView) shareLinearLayout.findViewById(R.id.tv_no_login_tips);
        LinearLayout llFunction = (LinearLayout) shareLinearLayout.findViewById(R.id.ll_share_bottom_function);

        tvShareTitle.setText("分享到");
/*        //是否登录
        if (UserUtils.isLogin()) { //已登录
            tvNoLoginTips.setVisibility(View.GONE);
            llFunction.setVisibility(View.VISIBLE);
        } else {
            tvNoLoginTips.setVisibility(View.VISIBLE);
            llFunction.setVisibility(View.GONE);
        }*/
        shareLinearLayout.findViewById(R.id.share_to_sinaweibo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ShareUtils.getInstance(mContext).share((Activity) mContext, TopicUtils.communityBean2ShareBean(communityBean), 4);
            }
        });
        shareLinearLayout.findViewById(R.id.share_to_weixin_friends).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ShareUtils.getInstance(mContext).share((Activity) mContext, TopicUtils.communityBean2ShareBean(communityBean), 0);
            }
        });
        shareLinearLayout.findViewById(R.id.share_to_weixin_quan).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ShareUtils.getInstance(mContext).share((Activity) mContext, TopicUtils.communityBean2ShareBean(communityBean), 1);
            }
        });
        shareLinearLayout.findViewById(R.id.share_to_qq).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ShareUtils.getInstance(mContext).share((Activity) mContext, TopicUtils.communityBean2ShareBean(communityBean), 2);
            }
        });
        shareLinearLayout.findViewById(R.id.share_to_qqzone).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ShareUtils.getInstance(mContext).share((Activity) mContext, TopicUtils.communityBean2ShareBean(communityBean), 3);
            }
        });
        shareLinearLayout.findViewById(R.id.share_to_copy_link).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                TextsUtils.copyLinks(mContext, ShareUtils.getTopicUrl(TopicUtils.communityBean2ShareBean(communityBean)));
                dialogDis(shareBottomSheetDialog);
            }
        });
        shareLinearLayout.findViewById(R.id.img_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogDis(shareBottomSheetDialog);
//                EventBusUtils.clickConfim();
            }
        });
        shareLinearLayout.findViewById(R.id.ll_rules).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isDissDialog = true;
                dialogDis(shareBottomSheetDialog);
            }
        });
        shareLinearLayout.findViewById(R.id.ll_intro).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isDissDialog = true;
                dialogDis(shareBottomSheetDialog);
            }
        });
        shareBottomSheetDialog.setContentView(shareLinearLayout);
        shareBottomSheetDialog.show();
        shareBottomSheetDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                if (!isDissDialog) {
                    finish();
                }
            }
        });
    }

    private void dialogDis(BottomSheetDialog dialog) {
        if (dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    @Override
    public void finish() {
        super.finish();
        //关闭窗体动画显示
        this.overridePendingTransition(R.anim.bottom_out, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
/*        if (ShareUtils.getmSocialApi() != null) {
            ShareUtils.getmSocialApi().onActivityResult(requestCode, resultCode, data);
        }*/
    }
/*
    public void onEventMainThread(ShareCompleteEvent event) {
        //统计分享次数
        if (topicBean != null) {
            TopicUtils.recordShare(topicBean.getTopicId(), event.getShareType());
            ToastUtils.showToast(this, R.string.share_success, 0);
        }
        finish();
    }*/

/*
    public void onEventMainThread(ClickConfimEvent event) {
        finish();
    }
*/

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(mContext)) {
            EventBus.getDefault().unregister(mContext);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            finish();
        }
        return super.onTouchEvent(event);
    }
}
