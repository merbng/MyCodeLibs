package com.app.merbng.mycodelibs.A_windowManager;

/**
 * 监听粘贴板复制链接弹出的通知
 */

import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.app.merbng.mycodelibs.R;

public class UcNotification {
    private static final int DISMISS_INTERVAL = 8000;
    private WindowManager mWindowManager;
    private WindowManager.LayoutParams mWindowParams;
    private View mContentView;
    private ProgressBar progress;
    private Context mContext;
    private boolean isShowing = false;
    private OnClickNotificationListener mOnClickNotificationListener;
    private TextView mTvContent;

    public UcNotification(Builder builder) {
        mContext = builder.getContext();
        mWindowManager = (WindowManager)
                mContext.getSystemService(Context.WINDOW_SERVICE);
        mWindowParams = new WindowManager.LayoutParams();
        mWindowParams.format= PixelFormat.RGBA_8888;
        mWindowParams.type = WindowManager.LayoutParams.TYPE_TOAST;// 系统提示window
        mWindowParams.gravity = Gravity.CENTER | Gravity.RIGHT;
        mWindowParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        mWindowParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        mWindowParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL |
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS;
        mWindowParams.alpha = 0.95f;
        //设置进入和退出动画
        mWindowParams.windowAnimations = R.style.NotificationAnim;
        mWindowParams.x = 0;
        mWindowParams.y = 0;
        setContentView(mContext, builder);
    }

    private static final int HIDE_WINDOW = 0;
    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case HIDE_WINDOW:
                    dismiss();
                    break;
            }
            return true;
        }
    });

    /***
     * 设置内容视图
     *
     * @param context
     */
    private void setContentView(Context context, Builder builder) {
        mContentView = LayoutInflater.from(context).inflate(R.layout.layout_clip_notification, null);
        mTvContent = (TextView) mContentView.findViewById(R.id.tv_content);
        progress = (ProgressBar) mContentView.findViewById(R.id.progress);
        setOnClickNotificationListener(builder.listener);
        setContent(builder.content);
        mContentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnClickNotificationListener.onClickThenDismiss(progress,mContentView);

            }
        });
    }

    private void setOnClickNotificationListener(OnClickNotificationListener listener) {
        mOnClickNotificationListener = listener;
    }

    public void show() {
        if (!isShowing) {
            isShowing = true;
            mWindowManager.addView(mContentView, mWindowParams);
            autoDismiss();
        }
    }

    public void dismiss() {
        if (isShowing) {
            resetState();
            mWindowManager.removeView(mContentView);
        }
    }

    /**
     * 重置状态
     */
    private void resetState() {
        isShowing = false;
        mWindowParams.x = 0;
        mWindowParams.y = 0;
    }

    /**
     * 自动隐藏通知
     */
    private void autoDismiss() {
        mHandler.removeMessages(HIDE_WINDOW);
        mHandler.sendEmptyMessageDelayed(HIDE_WINDOW, DISMISS_INTERVAL);
    }

    public void setContent(String content) {
//        mTvContent.setText(content);
    }

    public static class Builder {

        private Context context;

        private String content = "";

        private OnClickNotificationListener listener;

        public Context getContext() {
            return context;
        }

        public Builder setContext(Context context) {
            this.context = context;
            return this;
        }

        public Builder setOnClickNotificationListener(OnClickNotificationListener listener) {
            this.listener = listener;
            return this;
        }

        public Builder setContent(String content) {
            this.content = content;
            return this;
        }

        public UcNotification build() {

            if (null == context)
                throw new IllegalArgumentException("The context is Null.");
            return new UcNotification(this);
        }
    }

    public interface OnClickNotificationListener {
        void onClickThenDismiss(ProgressBar progress,View mContentView );
    }
}

