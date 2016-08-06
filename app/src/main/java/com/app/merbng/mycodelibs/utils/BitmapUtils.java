package com.app.merbng.mycodelibs.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.app.merbng.mycodelibs.interfaces.GetCallBack;

import java.io.File;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by merbng on 2016/7/10.
 */
public class BitmapUtils {

    /**
     * 从【资源文件】转【bitmap】
     *
     * @param context
     * @param resId
     * @return
     */
    public static Bitmap readBitMap(Context context, int resId) {
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inPreferredConfig = Bitmap.Config.RGB_565;
        opt.inPurgeable = true;
        opt.inInputShareable = true;

        InputStream is = context.getResources().openRawResource(resId);
        return BitmapFactory.decodeStream(is, null, opt);
    }

    /**图片网址 转成bitmap
     * @param mContext
     * @param urlPath
     * @param callBack
     */
    public static void url2Bitmap(final Context mContext, final String urlPath, final GetCallBack.GetCallBackInterface callBack) {
        AsyncThread.getInstance().start(new AsyncThread.OnDoInBackgroundListener() {
            @Override
            public void doInBackground() {
                Bitmap bitamp = null;
                final String fileName = urlPath.substring(urlPath.length() - 13, urlPath.length()) + ".jpg";
                try {
                    URL url = new URL(urlPath);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setDoInput(true);
                    conn.connect();
                    InputStream is = conn.getInputStream();
                    File file = BitmapCompressUtils.Stream2File(is, fileName);
                    BitmapCompressUtils compress = new BitmapCompressUtils();
                    BitmapCompressUtils.CompressOptions options = new BitmapCompressUtils.CompressOptions();
                    options.path = file.getPath();
                    bitamp = compress.compressFromUri(mContext, options);
                    callBack.onSuccess(bitamp);
                } catch (Exception e) {
                    e.printStackTrace();
                    callBack.onFail(e.getMessage());
                }
            }
        });
    }
}
