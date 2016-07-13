package com.app.merbng.mycodelibs.utils;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by zx on 2016/7/13.
 */
public class StringUtils {
    public static String inputStream2String(InputStream in) throws IOException {
        StringBuffer out = new StringBuffer();
        byte[] b = new byte[4096];
        for (int n; (n = in.read(b)) != -1; ) {
            out.append(new String(b, 0, n));
        }
        return out.toString();
    }
}
