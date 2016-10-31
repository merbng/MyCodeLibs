package com.app.merbng.mycodelibs.utils;

import android.graphics.Color;

import java.util.Random;

/**
 * Created by zx on 2016/8/19.
 */
public class ColorUtils {
    /**随机颜色
     * @return
     */
    public static int getRandomColor() {
        Random random = new Random();
        return 0xff000000 | random.nextInt(0x00ffffff);
    }
    public static int getRandomColor2() {
        return Color.rgb((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255));
    }
}
