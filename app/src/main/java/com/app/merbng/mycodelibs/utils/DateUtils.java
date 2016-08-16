package com.app.merbng.mycodelibs.utils;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by zx on 2016/8/4.
 */
public class DateUtils {
    public static String dateToString(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        if (date != null) {
            return simpleDateFormat.format(date);
        }
        return null;
    }

    /**
     * 转换毫秒到具体时间, 小时:分钟:秒
     * 参考: http://stackoverflow.com/questions/625433/how-to-convert-milliseconds-to-x-mins-x-seconds-in-java
     *
     * @param millis 毫秒
     * @return 时间字符串
     */
    public static String convertMillis2Time(long millis) {
        return String.format("%02d:%02d:%02d",
                TimeUnit.MILLISECONDS.toHours(millis),
                TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MICROSECONDS.toHours(millis)),
                TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.HOURS.toSeconds(TimeUnit.MICROSECONDS.toHours(millis)) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis))
        );
    }
    /**
     * 设置午夜定时器, 午夜12点发送广播, MIDNIGHT_ALARM_FILTER.
     * 实际测试可能会有一分钟左右的偏差.
     *
     * @param context 上下文
     */
    public static void setMidnightAlarm(Context context) {
        Context appContext = context.getApplicationContext();
        // FIXME: Intent intent = new Intent(IntentConsts.MIDNIGHT_ALARM_FILTER);
        Intent intent = new Intent();

        PendingIntent pi = PendingIntent.getBroadcast(appContext, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        AlarmManager am = (AlarmManager) appContext.getSystemService(Context.ALARM_SERVICE);

        // 午夜12点的标准计时, 来源于SO, 实际测试可能会有一分钟左右的偏差.
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.AM_PM, Calendar.AM);
        calendar.add(Calendar.DAY_OF_MONTH, 1);

        // 显示剩余时间
        long now = Calendar.getInstance().getTimeInMillis();
        Toast.makeText(context, "剩余时间(秒): " + ((calendar.getTimeInMillis() - now) / 1000), Toast.LENGTH_SHORT).show();
        // 设置之前先取消前一个PendingIntent
        am.cancel(pi);
        // 设置每一天的计时器
        am.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pi);
    }
}
