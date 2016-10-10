package com.app.merbng.mycodelibs.utils;

import android.content.Context;
import android.os.CountDownTimer;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.merbng.mycodelibs.R;

/**
 * Created by Merbng on 2016/10/10.
 */

public class TimeUtils {

    /**
     * 获取验证码倒计时
     *
     * @param mContext
     * @param tv_getCode
     * @param ll_getCode
     */
    public static void timerContent(final Context mContext, final TextView tv_getCode, final LinearLayout ll_getCode) {
        //发送验证码之后倒计时的计时器
        class TimeCount extends CountDownTimer {
            public TimeCount(long millisInFuture, long countDownInterval) {
                super(millisInFuture, countDownInterval);
            }

            @Override
            public void onTick(long millisUntilFinished) {
                tv_getCode.setClickable(false);
                tv_getCode.setTextColor(mContext.getResources().getColor(R.color.black));
                tv_getCode.setText("剩" + millisUntilFinished / 1000 + "秒");
            }

            @Override
            public void onFinish() {
                tv_getCode.setTextColor(mContext.getResources().getColor(R.color.black));
                tv_getCode.setText("重新发送");
                ll_getCode.setClickable(true);
            }
        }
        new TimeCount(60000, 1000).start();
    }
}
