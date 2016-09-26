package com.app.merbng.mycodelibs.utils;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/**
 * Created by zx on 2016/8/4.
 */
public class DateUtils {
    /**
     * 获取当前时间
     *
     * @return
     */
    public static String getCurrentUTCTime() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS", Locale.getDefault());
        return formatter.format(currentTime);
    }
    /**
     * 时间转长整形
     *
     * @param time
     * @return
     */
    public static long string2long(String time) {
        if (TextUtils.isEmpty(time)) {
            return 0l;
        }
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS", Locale.getDefault());
            return simpleDateFormat.parse(time).getTime();
        } catch (ParseException e) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
            try {
                return simpleDateFormat.parse(time).getTime();
            } catch (ParseException exception) {
                LogUtil.log.e("Parser time fail!");
                return -1;
            }
        }
    }
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
//    ----------------------------------------------------------------------------------------------------------------------------
    /**
     * 将时间戳转换为友好显示的时间
     * @param time
     * @return
     */
    public static String convertTimeToFriendly(long time) {
        String str = "";
        long currentTime = System.currentTimeMillis();
        long dxTime = currentTime - time;
        if(dxTime < 60 * 1000) {
            //几秒前
            str = "刚刚";
        }else if(60 * 1000 < dxTime && dxTime < 60*60*1000){
            //几分钟前
            str = (int)(dxTime / (60*1000)) + "分钟前";
        }else if(dxTime > 60*60*1000){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Date d = new Date(time);
            Date cd = new Date(currentTime);
            String currentStr = sdf.format(cd);
            String timeStr = sdf.format(d);
            if(currentStr.split(" ")[0].equals(timeStr.split(" ")[0])) {
                str = "今天"+timeStr.substring(11);
            }else if(Integer.parseInt(currentStr.substring(8, 9)) - Integer.parseInt(timeStr.substring(8, 9)) == 1) {
                str = "昨天"+timeStr.substring(11);
            }else if(Integer.parseInt(currentStr.substring(8, 9)) - Integer.parseInt(timeStr.substring(8, 9)) == 2) {
                str = "前天"+timeStr.substring(11);
            }else {
                str = timeStr.substring(5);
            }
        }
        return str;
    }
    /**
     * 将时间戳转换为友好显示的时间,用于聊天
     * @param time
     * @return
     */
    public static String convertTimeToFriendlyForChat(long time) {
        String str = "";
        long currentTime = System.currentTimeMillis();
        long dxTime = currentTime - time;
        if(dxTime < 60 * 1000) {
            //几秒前
            str = "";
        }else if(60 * 1000 < dxTime && dxTime < 60*60*1000){
            //几分钟前
            str = (int)(dxTime / (60*1000)) + "分钟前";
        }else if(dxTime > 60*60*1000){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Date d = new Date(time);
            Date cd = new Date(currentTime);
            String currentStr = sdf.format(cd);
            String timeStr = sdf.format(d);
            if(currentStr.split(" ")[0].equals(timeStr.split(" ")[0])) {
                str = "今天"+timeStr.substring(11);
            }else if(Integer.parseInt(currentStr.substring(8, 9)) - Integer.parseInt(timeStr.substring(8, 9)) == 1) {
                str = "昨天"+timeStr.substring(11);
            }else if(Integer.parseInt(currentStr.substring(8, 9)) - Integer.parseInt(timeStr.substring(8, 9)) == 2) {
                str = "前天"+timeStr.substring(11);
            }else {
                str = timeStr.substring(5);
            }
        }
        return str;
    }
}
