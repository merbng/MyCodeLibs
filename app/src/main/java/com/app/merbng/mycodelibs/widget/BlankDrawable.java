package com.app.merbng.mycodelibs.widget;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;

/**
 * Created by merbng on 2016/8/28.
 */
public class BlankDrawable extends Drawable {

    private static BlankDrawable mInstance;

    public static BlankDrawable getInstance(){
        if(mInstance == null)
            synchronized (BlankDrawable.class) {
                if(mInstance == null)
                    mInstance = new BlankDrawable();
            }

        return mInstance;
    }

    @Override
    public void draw(Canvas canvas) {}

    @Override
    public void setAlpha(int alpha) {}

    @Override
    public void setColorFilter(ColorFilter cf) {}

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSPARENT;
    }

}
