package com.app.merbng.mycodelibs.A_windowManager;

import android.app.Service;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.merbng.mycodelibs.R;
import com.app.merbng.mycodelibs.activitys.SplishActivity;
import com.app.merbng.mycodelibs.utils.DensityUtil;

public class ClipboardService extends Service {
    private ClipboardManager clipboardManager;
    private String clipString;
    UcNotification notification = null;
    private static final int HIDE_WINDOW = 0;
    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case HIDE_WINDOW:
                    notification.dismiss();
                    break;
            }
            return true;
        }
    });

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(final Intent intent, int flags, int startId) {
        if (clipboardManager == null) {
            clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        }
        if (clipString == null) {
            clipString = "";
        }
        clipboardManager.addPrimaryClipChangedListener(new ClipboardManager.OnPrimaryClipChangedListener() {
            @Override
            public void onPrimaryClipChanged() {
                CharSequence text = clipboardManager.getPrimaryClip().getItemAt(0).getText();
                if (text != null) {
                    final String textStr = text.toString();
                    if (!TextUtils.equals(textStr, clipString)) {
                        notification =
                                new UcNotification.Builder()
                                        .setContext(ClipboardService.this)
                                        .setContent(textStr)
                                        .setOnClickNotificationListener(new UcNotification.OnClickNotificationListener() {
                                            @Override
                                            public void onClickThenDismiss(final ProgressBar progress, final View mContentView) {
                                                int wh = DensityUtil.dip2px(ClipboardService.this, 20);
                                                RelativeLayout.LayoutParams rlp = new RelativeLayout.LayoutParams(wh, wh);
                                                rlp.addRule(RelativeLayout.CENTER_IN_PARENT);
                                                progress.setLayoutParams(rlp);
                                                final TextView tv_content = (TextView) mContentView.findViewById(R.id.tv_content);
//                                                if (UserUtils.is_login()) {
//                                                    progress.setVisibility(View.VISIBLE);
//                                                    mContentView.setClickable(false);
//                                                    tv_content.setText("正在保存...");
//                                                    //抓标题
//                                                    MIQuery.getWebTitleAndCover(textStr, new GetCallBack.GetCallBackInterface<WebTitleAndCover>() {
//                                                        @Override
//                                                        public void onSuccess(final WebTitleAndCover webTitleAndCover) {
//                                                            LogUtil.log.e("抓标题：" + webTitleAndCover.getTitle());
//                                                            //创建卡片
//                                                            MICreate.fastCreateCard(webTitleAndCover.getTitle(), textStr, new GetCallBack.CommonInterface() {
//                                                                @Override
//                                                                public void onSuccess() {
//                                                                    tv_content.setText("创建成功");
//                                                                    mContentView.setClickable(true);
//                                                                    if (!AppSystemUtils.isRunningForeground(ClipboardService.this)) {
//                                                                        tv_content.setOnClickListener(new View.OnClickListener() {
//                                                                            @Override
//                                                                            public void onClick(View v) {
//                                                                                openApp();
//                                                                            }
//                                                                        });
//                                                                    } else {
//                                                                        LogUtil.log.e("前台不可以点击");
//                                                                    }
//                                                                    hideNotify(5000);
//                                                                    if (progress.isShown()) {
//                                                                        progress.setVisibility(View.GONE);
//                                                                    }
//                                                                }
//
//                                                                @Override
//                                                                public void onFail(String s) {
//                                                                    hideNotify(300);
//                                                                    TispToastFactory.showToast(ClipboardService.this, s, 1);
//                                                                    AppFunctionUtils.saveLinkAsDrafts(ClipboardService.this, 1, textStr, webTitleAndCover.getTitle(), null);
//                                                                    if (progress.isShown()) {
//                                                                        progress.setVisibility(View.GONE);
//                                                                    }
//                                                                }
//                                                            });
//                                                        }
//
//                                                        @Override
//                                                        public void onFail(String s) {
//                                                            TispToastFactory.showToast(ClipboardService.this, s, 1);
//                                                            AppFunctionUtils.saveLinkAsDrafts(ClipboardService.this, 1, textStr, "标题", null);
//                                                            hideNotify(300);
//                                                            if (progress.isShown()) {
//                                                                progress.setVisibility(View.GONE);
//                                                            }
//                                                        }
//                                                    });
//                                                } 
// else {
//                                                    mContentView.setClickable(true);
//                                                    tv_content.setText("请登录..");
//                                                    tv_content.setOnClickListener(new View.OnClickListener() {
//                                                        @Override
//                                                        public void onClick(View v) {
//                                                            openApp();
//                                                        }
//                                                    });
//                                                }

                                            }
                                        }).build();
                        notification.show();
                    }
                    clipString = textStr;
                }
            }
        });
        return Service.START_STICKY;
    }

    private void openApp() {
        Intent intent = new Intent(ClipboardService.this,
                SplishActivity.class); // 从启动动画ui跳转到主ui
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        hideNotify(200);
    }

    /**
     * 自动隐藏通知
     */
    private void hideNotify(int time) {
        mHandler.removeMessages(HIDE_WINDOW);
        mHandler.sendEmptyMessageDelayed(HIDE_WINDOW, time);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}