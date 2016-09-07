package com.app.merbng.mycodelibs.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.AccelerateInterpolator;

import com.app.merbng.mycodelibs.R;


/**
 * Created by _高sir on 2016/6/16.
 */
public class ButtonView extends View implements View.OnClickListener {
    public static final int DEFAULT_DURATION = 500;
    //    内边距偏移量
    private static final int DEFAULT_PADDING_OFFSET = 2;
    private int width = 100;
    private int height = 100;

    private float drawWidth;
    //    drawWidth 的最大与最小值
    private float bigF, smallF;

    private Paint strokePaint, arcPaint;
    private TextPaint textPaint;
    private String text = "on click!";
    private ValueAnimator valueAnimator;

    private int textSize = 20;
    private int textColor = R.color.bg_white;
    private int strokeSize = 2;
    private int strokeColor = R.color.colorAccent;
    private int arcColor = R.color.arcColor;
    private float startAngle = 5f;
    private int paddingOffset = DEFAULT_PADDING_OFFSET;

    //    记录状态
    public enum State {
        RECT2CIRCLE,
        CIRCLE2RECT
    }

    private State state = State.RECT2CIRCLE;
    // 对外实现点击接口，因为我已经使用了本身的点击接口，防止无效
    public OnClickListener onListener;

    public void setOnListener(OnClickListener onListener) {
        this.onListener = onListener;
    }

    public ButtonView(Context context, AttributeSet attrs) {
        super(context, attrs);

        DisplayMetrics dm = getResources().getDisplayMetrics();
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ButtonView);

        text = (String) a.getText(R.styleable.ButtonView_text);
        if (TextUtils.isEmpty(text)) {
            text = "on click!";
        }
        textSize = a.getDimensionPixelSize(R.styleable.ButtonView_text_size, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, textSize, dm));
        strokeSize = a.getDimensionPixelSize(R.styleable.ButtonView_stroke_size, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, strokeSize, dm));
        textColor = a.getColor(R.styleable.ButtonView_text_color, getResources().getColor(textColor));
        strokeColor = a.getColor(R.styleable.ButtonView_stroke_color, getResources().getColor(strokeColor));
        arcColor = a.getColor(R.styleable.ButtonView_arc_color, getResources().getColor(arcColor));
        a.recycle();

        paddingOffset = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, paddingOffset, dm);

        initPaint();
    }

    private void initPaint() {
        strokePaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        strokePaint.setColor(strokeColor);
        strokePaint.setStyle(Paint.Style.STROKE);
        strokePaint.setStrokeWidth(strokeSize);

        textPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG | Paint.LINEAR_TEXT_FLAG);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setColor(textColor);
        textPaint.setTextSize(textSize);
        textPaint.setTypeface(Typeface.MONOSPACE);

        arcPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        arcPaint.setColor(arcColor);
        arcPaint.setStyle(Paint.Style.STROKE);
        arcPaint.setStrokeWidth(strokeSize);
        setOnClickListener(this);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        width = getViewWidth(widthMeasureSpec);
        height = getViewHeight(heightMeasureSpec);

        drawWidth = width;
        setMeasuredDimension(width, height);
        bigF = width - getPaddingLeft() - getPaddingRight();
        smallF = (width + height) / 2f;

    }

    private int getViewHeight(int heightMeasureSpec) {
        int result = 0;
        int mode = MeasureSpec.getMode(heightMeasureSpec);
        int size = MeasureSpec.getSize(heightMeasureSpec);
        if (mode == MeasureSpec.EXACTLY) {
            result = size;
        } else {
            result = height;
            if (mode == MeasureSpec.AT_MOST) {
                result = Math.min(result, size);
            }
        }
        return result;
    }

    private int getViewWidth(int widthMeasureSpec) {
        int result = 0;
        int mode = MeasureSpec.getMode(widthMeasureSpec);
        int size = MeasureSpec.getSize(widthMeasureSpec);
        if (mode == MeasureSpec.EXACTLY) {
            result = size;
        } else {
            result = width;
            if (mode == MeasureSpec.AT_MOST) {
                result = Math.min(result, size);
            }
        }
        return result;
    }

    @Override
    public void onClick(View v) {
        Log.d("aa", "on click!");
        v.setFocusable(false);
        v.setEnabled(false);
        switch (state) {
            case RECT2CIRCLE:
                rect2Circle();
                break;
            case CIRCLE2RECT:
                circle2Rect();
                break;
        }
        if (onListener != null) {
            onListener.onClick(v);
        }
    }

    private void circle2Rect() {
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        valueAnimator = ValueAnimator.ofFloat(smallF, bigF);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                drawWidth = (float) animation.getAnimatedValue();
                if (drawWidth == bigF) {
                    state = State.RECT2CIRCLE;
                    setFocusable(true);
                    setEnabled(true);
                    return;
                }
                postInvalidate();
            }
        });
        valueAnimator.setDuration(DEFAULT_DURATION);
        valueAnimator.setInterpolator(new AccelerateInterpolator(1.0f));
        valueAnimator.start();
    }

    private void rect2Circle() {
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        valueAnimator = ValueAnimator.ofFloat(bigF, smallF);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                drawWidth = (float) animation.getAnimatedValue();
                if (drawWidth == smallF) {
                    state = State.CIRCLE2RECT;
                    setFocusable(true);
                    setEnabled(true);
                    return;
                }

                postInvalidate();
            }
        });
        valueAnimator.setDuration(DEFAULT_DURATION);
        valueAnimator.setInterpolator(new AccelerateInterpolator(1.0f));
        valueAnimator.start();

    }

    @Override
    protected void onDraw(Canvas canvas) {

        canvas.drawRoundRect(getRectF(), height / 2, height / 2, strokePaint);
        if (drawWidth != smallF) {
            if (drawWidth >= bigF) {
                canvas.drawText(text, width / 2, height / 2 + Math.abs((textPaint.descent() + textPaint.ascent()) / 2), textPaint);
            }
        } else {
            canvas.drawArc(getRectF(), startAngle, 45, false, arcPaint);
            startAngle += 5;
            invalidate();
        }
    }


    private RectF getRectF() {
        return new RectF(width - drawWidth + getPaddingLeft() + paddingOffset,
                getPaddingTop() + paddingOffset,
                drawWidth - getPaddingRight() - paddingOffset,
                height - getPaddingBottom() - paddingOffset);
    }


}
