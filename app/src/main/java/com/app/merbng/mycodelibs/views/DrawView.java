package com.app.merbng.mycodelibs.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by merbng on 2016/4/7.
 */
public class DrawView extends View {
    public DrawView(Context context) {
        super(context);
    }

    public DrawView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setAlpha(0x10);
        paint.setTextSize(100);
        canvas.drawText("哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈", 200, 200, paint);

    }
}
