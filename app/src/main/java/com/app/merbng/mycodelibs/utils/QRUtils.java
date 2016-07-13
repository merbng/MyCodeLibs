package com.app.merbng.mycodelibs.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.util.Hashtable;

public class QRUtils {
    private static int QR_WIDTH = 400, QR_HEIGHT = 400;

    // 生成的Logo的图片的宽
    private static int IMAGE_HALFWIDTH;

    /**
     * 生成带Logo的二维码
     *
     * @param url
     * @param bitmap
     * @return
     */
    public static Bitmap createHeardImgCode(Context mContext, String url, Bitmap bitmap) {
        Bitmap qrCodeBitmap = null;
        try {
            IMAGE_HALFWIDTH = DensityUtil.dip2px(mContext, 20);
            qrCodeBitmap = createBarCode(url, bitmap, DensityUtil.dip2px(mContext, 240));
        } catch (WriterException e) {
            e.printStackTrace();
        }
        return qrCodeBitmap;
    }

    /**
     * 生成二维码图片
     * 之前使用的纯二维码
     *
     * @param url
     * @return
     */
    public static Bitmap createQRImage(String url) {
        try {
            // 判断URL合法性
            if (url == null || "".equals(url) || url.length() < 1) {
                return null;
            }
            Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();
            hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
            // 图像数据转换，使用了矩阵转换
            BitMatrix bitMatrix = new QRCodeWriter().encode(url,
                    BarcodeFormat.QR_CODE, QR_WIDTH, QR_HEIGHT, hints);
            int[] pixels = new int[QR_WIDTH * QR_HEIGHT];
            // 下面这里按照二维码的算法，逐个生成二维码的图片，
            // 两个for循环是图片横列扫描的结果
            for (int y = 0; y < QR_HEIGHT; y++) {
                for (int x = 0; x < QR_WIDTH; x++) {
                    if (bitMatrix.get(x, y)) {
                        pixels[y * QR_WIDTH + x] = 0xff000000;
                    } else {
                        pixels[y * QR_WIDTH + x] = 0xffffffff;
                    }
                }
            }
            // 生成二维码图片的格式，使用ARGB_8888
            Bitmap bitmap = Bitmap.createBitmap(QR_WIDTH, QR_HEIGHT,
                    Bitmap.Config.ARGB_4444);
            bitmap.setPixels(pixels, 0, QR_WIDTH, 0, 0, QR_WIDTH, QR_HEIGHT);
            // 显示到一个ImageView上面
            return bitmap;
        } catch (WriterException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 生成带logo的二维码图片
     *
     * @param url            扫描的内容
     * @param mBitmap        插入的logo
     * @param widthAndHeight
     * @return
     * @throws WriterException
     */
    public static Bitmap createBarCode(String url, Bitmap mBitmap, int widthAndHeight)
            throws WriterException {
        Matrix m = new Matrix();
        float sx = (float) 2 * IMAGE_HALFWIDTH / mBitmap.getWidth();
        float sy = (float) 2 * IMAGE_HALFWIDTH / mBitmap.getHeight();
        m.setScale(sx, sy);// 设置缩放信息
        // 将logo图片按martix设置的信息缩放
        mBitmap = Bitmap.createBitmap(mBitmap, 0, 0, mBitmap.getWidth(),
                mBitmap.getHeight(), m, false);
        MultiFormatWriter writer = new MultiFormatWriter();
        @SuppressWarnings("rawtypes")
        Hashtable hst = new Hashtable();
        // 设置字符编码
        hst.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        // 生成二维码矩阵信息
        BitMatrix matrix = writer.encode(url, BarcodeFormat.QR_CODE,
                widthAndHeight, widthAndHeight, hst);
        // 矩阵高度
        int width = matrix.getWidth();
        // 矩阵宽
        int height = matrix.getHeight();
        int halfW = width / 2;
        int halfH = height / 2;
        // 定义数组长度为矩阵高度*矩阵宽度，用于记录矩阵中像素信息
        int[] pixels = new int[width * height];
        // 从行开始迭代矩阵
        for (int y = 0; y < height; y++) {
            // 迭代列
            for (int x = 0; x < width; x++) {
                if (x > halfW - IMAGE_HALFWIDTH && x < halfW + IMAGE_HALFWIDTH
                        && y > halfH - IMAGE_HALFWIDTH
                        && y < halfH + IMAGE_HALFWIDTH) {// 该位置用于存放图片信息
                    // 记录图片每个像素信息
                    pixels[y * width + x] = mBitmap.getPixel(x - halfW
                            + IMAGE_HALFWIDTH, y - halfH + IMAGE_HALFWIDTH);
                } else {
                    if (matrix.get(x, y)) {// 如果有黑块点，记录信息
                        pixels[y * width + x] = 0xff000000;// 记录黑块信息
                    }
                }
            }
        }
        Bitmap bitmap = Bitmap.createBitmap(width, height,
                Bitmap.Config.ARGB_4444);
        // 通过像素数组生成bitmap
        bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
        return bitmap;
    }

}
