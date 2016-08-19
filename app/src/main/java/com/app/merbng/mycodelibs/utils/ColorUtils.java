package com.app.merbng.mycodelibs.utils;

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
}
