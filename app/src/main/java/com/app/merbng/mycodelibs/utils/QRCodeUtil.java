package com.app.merbng.mycodelibs.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

/**
 * 二维码生成工具类
 */
public class QRCodeUtil {
    /**
     * 生成二维码Bitmap
     *
     * @param content   内容
     * @param widthPix  图片宽度
     * @param heightPix 图片高度
     * @param logoBm    二维码中心的Logo图标（可以为null）
     * @param filePath  用于存储二维码图片的文件路径
     * @return 生成二维码及保存文件是否成功
     */
    public static boolean createQRImage(String content, int widthPix, int heightPix, Bitmap logoBm, String filePath) {
        try {
            if (content == null || "".equals(content)) {
                return false;
            }

            //配置参数  
            Map<EncodeHintType, Object> hints = new HashMap<>();
            hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
            //容错级别  
            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
            //设置空白边距的宽度  
            //            hints.put(EncodeHintType.MARGIN, 2); //default is 4  

            // 图像数据转换，使用了矩阵转换  
            BitMatrix bitMatrix = new QRCodeWriter().encode(content, BarcodeFormat.QR_CODE, widthPix, heightPix, hints);
            int[] pixels = new int[widthPix * heightPix];
            // 下面这里按照二维码的算法，逐个生成二维码的图片，  
            // 两个for循环是图片横列扫描的结果  
            for (int y = 0; y < heightPix; y++) {
                for (int x = 0; x < widthPix; x++) {
                    if (bitMatrix.get(x, y)) {
                        pixels[y * widthPix + x] = 0xff000000;
                    } else {
                        pixels[y * widthPix + x] = 0xffffffff;
                    }
                }
            }

            // 生成二维码图片的格式，使用ARGB_8888  
            Bitmap bitmap = Bitmap.createBitmap(widthPix, heightPix, Bitmap.Config.ARGB_8888);
            bitmap.setPixels(pixels, 0, widthPix, 0, 0, widthPix, heightPix);

            if (logoBm != null) {
                bitmap = addLogo(bitmap, logoBm);
            }

            //必须使用compress方法将bitmap保存到文件中再进行读取。直接返回的bitmap是没有任何压缩的，内存消耗巨大！  
            return bitmap != null && bitmap.compress(Bitmap.CompressFormat.JPEG, 100, new FileOutputStream(filePath));
        } catch (WriterException | IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * 在二维码中间添加Logo图案
     */
    private static Bitmap addLogo(Bitmap src, Bitmap logo) {
        if (src == null) {
            return null;
        }

        if (logo == null) {
            return src;
        }

        //获取图片的宽高  
        int srcWidth = src.getWidth();
        int srcHeight = src.getHeight();
        int logoWidth = logo.getWidth();
        int logoHeight = logo.getHeight();

        if (srcWidth == 0 || srcHeight == 0) {
            return null;
        }

        if (logoWidth == 0 || logoHeight == 0) {
            return src;
        }

        //logo大小为二维码整体大小的1/5  
        float scaleFactor = srcWidth * 1.0f / 5 / logoWidth;
        Bitmap bitmap = Bitmap.createBitmap(srcWidth, srcHeight, Bitmap.Config.ARGB_8888);
        try {
            Canvas canvas = new Canvas(bitmap);
            canvas.drawBitmap(src, 0, 0, null);
            canvas.scale(scaleFactor, scaleFactor, srcWidth / 2, srcHeight / 2);
            canvas.drawBitmap(logo, (srcWidth - logoWidth) / 2, (srcHeight - logoHeight) / 2, null);

            canvas.save(Canvas.ALL_SAVE_FLAG);
            canvas.restore();
        } catch (Exception e) {
            bitmap = null;
            e.getStackTrace();
        }

        return bitmap;
    }

    /**
     * 图像二维码
     *
     * @param content
     * @param size
     * @return
     */
    public static Bitmap CreateQRCodeBitmap(String content, int size, Bitmap[] bitmaps, Bitmap bitmapKey) {
        if (bitmaps == null || bitmaps.length == 0) {
            return null;
        }
        try {

            int count = bitmaps.length;
            // 生成一维条码,编码时指定大小,不要生成了图片以后再进行缩放,这样会模糊导致识别失败

            Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
            hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
            hints.put(EncodeHintType.MARGIN, 0);
            BitMatrix matrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, size, size, hints);

            Rect codeRect = new Rect();
            int cellWidth = checkParam(matrix, codeRect);

            int width = matrix.getWidth();
            int height = matrix.getHeight();

            Paint paint = new Paint();
            paint.setAntiAlias(true);
            int hcellWidth = cellWidth / 2;
            int startXp = codeRect.left + hcellWidth;
            int startYp = codeRect.top + hcellWidth;
            Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            int pw = 7 * cellWidth;
            for (int x = startXp; x <= codeRect.right; x += cellWidth) {
                for (int y = startYp; y <= codeRect.bottom; y += cellWidth) {
                    if (matrix.get(x, y)) {
                        Bitmap bm = null;
                        if ((x > codeRect.left + pw || y > codeRect.top + pw) && (x < codeRect.right - pw || y > codeRect.top + pw) && (x > codeRect.left + pw || y < codeRect.bottom - pw)) {

                            if (count == 1) {
                                bm = bitmaps[0];
                            } else {
                                int i = (int) (Math.random() * count);
                                if (i >= count) {
                                    i = count - 1;
                                }
                                bm = bitmaps[i];
                            }
                        } else {
                            bm = bitmapKey;
                        }
                        Rect rect = new Rect(0, 0, bm.getWidth(), bm.getHeight());
                        RectF rectf = new RectF(x - hcellWidth, y - hcellWidth, x + hcellWidth, y + hcellWidth);
                        canvas.drawBitmap(bm, rect, rectf, paint);
                    }
                }
            }
            return bitmap;
        } catch (Exception e) {
        }
        return null;
    }


    /**
     * 获取二维码位置信息
     *
     * @param matrix
     * @param rect 带回二维码边界
     * @return 返回单个信息点的宽
     */
    private static int checkParam(BitMatrix matrix, Rect rect) {
        int width = matrix.getWidth();
        int height = matrix.getHeight();

        int startX = 0;
        int startY = 0;

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (matrix.get(x, y)) {
                    startX = x;
                    startY = y;
                    x = width;
                    y = height;
                }
            }
        }
        int endX = 0;
        for (int x = width - 1; x >= 0; x--) {
            if (matrix.get(x, startY)) {
                endX = x;
                x = -1;
            }
        }
        int endY = 0;
        for (int y = height - 1; y >= 0; y--) {
            if (matrix.get(startX, y)) {
                endY = y;
                y = -1;
            }
        }
        int cellWidth = 1;
        while (true) {
            int pX = startX + cellWidth;
            int pY = startY + cellWidth;
            if (pX <= endX && pY <= endY && matrix.get(pX, pY)) {
                cellWidth++;
                continue;
            }
            break;
        }
        rect.left = startX;
        rect.top = startY;
        rect.right = endX;
        rect.bottom = endY;
        return cellWidth;
    }

    /**
     * 附加icon
     *
     * @param QRCode
     * @param icon
     * @param scale
     * @return
     */
    public static Bitmap withIcon(Bitmap QRCode, Bitmap icon, float scale) {
        Bitmap bitmap = null;
        try {
            bitmap = QRCode;
            Canvas canvas = new Canvas(bitmap);

            int width = bitmap.getWidth();
            int heigth = bitmap.getHeight();
            int iwidth = icon.getWidth();
            int iheigth = icon.getHeight();

            Rect src = new Rect();
            src.left = 0;
            src.top = 0;
            src.right = iwidth;
            src.bottom = iheigth;

            float aIWidth = width * scale;
            float aIHeigth = heigth * scale;

            RectF dst = new RectF();
            dst.left = (width - aIWidth) / 2;
            dst.top = (heigth - aIHeigth) / 2;
            dst.right = dst.left + aIWidth;
            dst.bottom = dst.top + aIHeigth;
            Paint paint = new Paint();
            canvas.drawBitmap(icon, src, dst, paint);
        } catch (Exception e) {

        }

        return bitmap;
    }
}