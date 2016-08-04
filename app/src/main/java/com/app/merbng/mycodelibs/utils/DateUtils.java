package com.app.merbng.mycodelibs.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

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
}
