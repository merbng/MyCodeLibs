package com.app.merbng.mycodelibs.utils;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore.Images;
import android.util.Log;


import com.app.merbng.mycodelibs.Constent;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 图片压缩工具类
 *
 * @author Administrator
 */
public class BitmapCompressUtils {
    public static final String CONTENT = "content";
    public static final String FILE = "file";

    /**
     * 图片压缩参数
     *
     * @author Administrator
     */
    public static class CompressOptions {
        public static final int DEFAULT_WIDTH = 400;
        public static final int DEFAULT_HEIGHT = 800;

        public int maxWidth = DEFAULT_WIDTH;
        public int maxHeight = DEFAULT_HEIGHT;
        /**
         * 压缩后图片保存的文件
         */
        public File destFile;
        /**
         * 图片压缩格式,默认为jpg格式
         */
        public CompressFormat imgFormat = CompressFormat.JPEG;

        /**
         * 图片压缩比例 默认为30
         */
        public int quality = 70;

        public Uri uri;
        public String path;
    }
    //不缩放 不压缩
    public Bitmap getBitmap(CompressOptions compressOptions){
        String filePath = compressOptions.path;
        if (null == filePath) {
            return null;
        }

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;

        BitmapFactory.decodeFile(filePath, options);

        int actualWidth = options.outWidth;
        int actualHeight = options.outHeight;
        int desiredWidth = getResizedDimension(compressOptions.maxWidth,
                compressOptions.maxHeight, actualWidth, actualHeight);
        int desiredHeight = getResizedDimension(compressOptions.maxHeight,
                compressOptions.maxWidth, actualHeight, actualWidth);

        options.inJustDecodeBounds = false;
        options.inSampleSize = findBestSampleSize(actualWidth, actualHeight,
                desiredWidth, desiredHeight);

        Bitmap bitmap = null;

        Bitmap destBitmap = BitmapFactory.decodeFile(filePath, options);

        // If necessary, scale down to the maximal acceptable size.
      /* if (destBitmap.getHeight() > 8000) {
               bitmap = Bitmap.createScaledBitmap(destBitmap, desiredWidth,
                       destBitmap.getHeight()/2, true);
               destBitmap.recycle();
           } else {
               bitmap = destBitmap;
        }*/
        bitmap = destBitmap;
        // compress file if need
        if (null != compressOptions.destFile) {
            compressFile(compressOptions, bitmap);
        }

        return bitmap;
    }
    //有缩放 有压缩的图片
    public Bitmap compressFromUri(Context context,
                                  CompressOptions compressOptions) {

        // uri指向的文件路径
        // String filePath = getFilePath(context, compressOptions.uri);
        String filePath = compressOptions.path;
        if (null == filePath) {
            return null;
        }

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;

        BitmapFactory.decodeFile(filePath, options);

        int actualWidth = options.outWidth;
        int actualHeight = options.outHeight;
        int desiredWidth = getResizedDimension(compressOptions.maxWidth,
                compressOptions.maxHeight, actualWidth, actualHeight);
        int desiredHeight = getResizedDimension(compressOptions.maxHeight,
                compressOptions.maxWidth, actualHeight, actualWidth);

        options.inJustDecodeBounds = false;
        options.inSampleSize = findBestSampleSize(actualWidth, actualHeight,
                desiredWidth, desiredHeight);

        Bitmap bitmap = null;

        Bitmap destBitmap = BitmapFactory.decodeFile(filePath, options);

        // If necessary, scale down to the maximal acceptable size.
      /* if (destBitmap.getHeight() > 8000) {
               bitmap = Bitmap.createScaledBitmap(destBitmap, desiredWidth,
                       destBitmap.getHeight()/2, true);
               destBitmap.recycle();
           } else {
               bitmap = destBitmap;
        }*/
        bitmap = destBitmap;
        // compress file if need
        if (null != compressOptions.destFile) {
            compressFile(compressOptions, bitmap);
        }

        return bitmap;
    }

    /**
     * compress file from bitmap with compressOptions
     *
     * @param compressOptions
     * @param bitmap
     */
    private void compressFile(CompressOptions compressOptions, Bitmap bitmap) {
        OutputStream stream = null;
        try {
            stream = new FileOutputStream(compressOptions.destFile);
        } catch (FileNotFoundException e) {
            Log.e("ImageCompress", e.getMessage());
        }

        bitmap.compress(compressOptions.imgFormat, compressOptions.quality,
                stream);
    }

    private static int findBestSampleSize(int actualWidth, int actualHeight,
                                          int desiredWidth, int desiredHeight) {
        double wr = (double) actualWidth / desiredWidth;
        double hr = (double) actualHeight / desiredHeight;
        double ratio = Math.min(wr, hr);
        float n = 1.0f;
        while ((n * 2) <= ratio) {
            n *= 2;
        }

        return (int) n;
    }

    private static int getResizedDimension(int maxPrimary, int maxSecondary,
                                           int actualPrimary, int actualSecondary) {
        // If no dominant value at all, just return the actual.
        if (maxPrimary == 0 && maxSecondary == 0) {
            return actualPrimary;
        }

        // If primary is unspecified, scale primary to match secondary's scaling
        // ratio.
        if (maxPrimary == 0) {
            double ratio = (double) maxSecondary / (double) actualSecondary;
            return (int) (actualPrimary * ratio);
        }

        if (maxSecondary == 0) {
            return maxPrimary;
        }

        double ratio = (double) actualSecondary / (double) actualPrimary;
        int resized = maxPrimary;
        if (resized * ratio > maxSecondary) {
            resized = (int) (maxSecondary / ratio);
        }
        return resized;
    }

    /**
     * 获取文件的路径
     *
     * @param
     * @return
     */
    private String getFilePath(Context context, Uri uri) {

        String filePath = null;

        if (CONTENT.equalsIgnoreCase(uri.getScheme())) {

            Cursor cursor = context.getContentResolver().query(uri,
                    new String[]{Images.Media.DATA}, null, null, null);

            if (null == cursor) {
                return null;
            }

            try {
                if (cursor.moveToNext()) {
                    filePath = cursor.getString(cursor
                            .getColumnIndex(Images.Media.DATA));
                }
            } finally {
                cursor.close();
            }
        }

        // 从文件中选择
        if (FILE.equalsIgnoreCase(uri.getScheme())) {
            filePath = uri.getPath();
        }

        return filePath;
    }

    // 流转File
    public static File Stream2File(InputStream stream, String Filename) {
        File file = null;
        FileOutputStream fileOutputStream = null;
        try {
            file = new File(Constent.DOWNLOAD_IMAGE_PATH + Filename);
            if (file.exists()) {
                if (file.delete()) {
                    fileOutputStream = new FileOutputStream(file);
                    int length;
                    byte[] buf = new byte[1024];
                    while ((length = stream.read(buf, 0, buf.length)) != -1) {
                        fileOutputStream.write(buf, 0, length);
                        fileOutputStream.flush();
                    }
                    return file;
                }
            } else {
                fileOutputStream = new FileOutputStream(file);
                int length;
                byte[] buf = new byte[1024];
                while ((length = stream.read(buf, 0, buf.length)) != -1) {
                    fileOutputStream.write(buf, 0, length);
                    fileOutputStream.flush();
                }
                return file;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return file;
    }
}

