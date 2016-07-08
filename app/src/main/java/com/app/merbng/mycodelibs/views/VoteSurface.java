package com.app.merbng.mycodelibs.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

import com.app.merbng.mycodelibs.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 点赞
 */
public class VoteSurface extends SurfaceView implements Callback, Runnable {

    private SurfaceHolder sfh;
    private Paint paint;
    private Thread th;
    private boolean flag;// flag
    private Canvas canvas;
    public static int screenW, screenH;

    private Bitmap bitmap;

    private List<PathObj> list = new ArrayList<PathObj>();// 路径

    private Canvas pagerCanvas;
    private Bitmap pagerBitmap;// 每次使用这个bitmap刷新

    private Random random = new Random();

    private long start_time = 0;

    /**
     * SurfaceView初始化函数
     */
    public VoteSurface(Context context) {
        super(context);
        init(context);
    }

    public VoteSurface(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    public VoteSurface(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        sfh = this.getHolder();
        sfh.addCallback(this);

        getHolder().setFormat(PixelFormat.TRANSLUCENT);

        paint = new Paint();
        paint.setXfermode(new PorterDuffXfermode(Mode.CLEAR));

        setFocusable(true);


//		InputStream is00 = MyApplication.getInstance().getResources().openRawResource(R.drawable.heart0);
//		bitmap =  GlobalFunctions.
//				GetBitmapFromBitmap(MyApplication.getInstance(), is00);
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.guide_page01);

        setZOrderOnTop(true);

        startThread();

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right,
                            int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    /**
     * SurfaceView视图创建，响应此函数
     */
    public void surfaceCreated(SurfaceHolder holder) {
        if(pagerBitmap == null) {
            screenW = this.getWidth();
            screenH = this.getHeight();
            pagerBitmap = Bitmap.createBitmap(screenW, screenH, Config.ARGB_8888);
            pagerCanvas = new Canvas(pagerBitmap);
        }
    }

    /**
     * 游戏绘图
     */
    public void myDraw() {
        try {
            canvas = sfh.lockCanvas();
            if (canvas != null) {
                paint.setXfermode(new PorterDuffXfermode(Mode.CLEAR));
                pagerCanvas.drawPaint(paint);
                paint.setXfermode(new PorterDuffXfermode(Mode.SRC));
                pagerCanvas.save(Canvas.ALL_SAVE_FLAG);// 保存
                drawQpath(pagerCanvas);
                pagerCanvas.restore();// 存储
                paint.setXfermode(new PorterDuffXfermode(Mode.CLEAR));
                canvas.drawPaint(paint);
                paint.setXfermode(new PorterDuffXfermode(Mode.SRC));
                canvas.drawBitmap(pagerBitmap, 0, 0, null);
            }
        } catch (Exception e) {
        } finally {
            try {
                if (canvas != null)
                    sfh.unlockCanvasAndPost(canvas);
            } catch (Exception e2) {
            }

        }
    }

    /**
     * 绘制贝赛尔曲线
     *
     * @param canvas
     *            主画布
     */
    public void drawQpath(Canvas canvas) {
        if(list.size()<=0){
            flag = false;
        }
        for (int i = 0; i < list.size(); i++) {
            try {
                PathObj obj = list.get(i);
                if (obj.getAlpha() <= 0) {
                    list.remove(i);
                    i--;
                    continue;
                }
                Rect src = obj.getSrcRect();
                Rect dst = obj.getDstRect();
                if (dst == null) {
                    list.remove(i);
                    i--;
                    continue;
                }
                canvas.drawBitmap(obj.getBitmap(), src, dst, obj.getPaint());
            } catch (Exception e) {
                list.remove(i);
                i--;
            }

        }
    }

    /**
     * 点赞
     */
    public void click(Bitmap bitmap) {
        if(System.currentTimeMillis() - start_time >10){
            //禁止同时显示多个赞
            start_time = System.currentTimeMillis();
            list.add(new PathObj(getWidth(), getHeight(), bitmap));
            startThread();
        }
    }

    // 启动回话线程
    private void startThread() {
        if(!flag || th == null || !th.isAlive()){
            flag = true;
            th = new Thread(this);
            th.setPriority(Thread.MAX_PRIORITY);
            th.start();
        }
    }

//	/**
//	 * 触屏事件监听
//	 */
//	@Override
//	public boolean onTouchEvent(MotionEvent event) {
//		click(bitmap);
////		list.add(new PathObj(getWidth(), getHeight(), bitmap));
//		return true;
//	}

    /**
     * 按键事件监听
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return super.onKeyDown(keyCode, event);
    }

    public void run() {
        while (flag) {
//			long start = System.currentTimeMillis();
            myDraw();
//			long end = System.currentTimeMillis();
            try {
				/*if (end - start < 30) {
					Thread.sleep(30 - (end - start));
				}*/
                Thread.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();

            }
        }
    }

    /**
     * SurfaceView视图状态发生改变，响应此函数
     */
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {
    }

    /**
     * SurfaceView视图消亡时，响应此函数
     */
    public void surfaceDestroyed(SurfaceHolder holder) {
        flag = false;
        list.clear();
    }


    /**
     * 随机路径
     * @author zhangjinyuan
     *
     */
    public class PathObj {
        private Paint paint;

        public Path path;
        public PathMeasure pathMeasure;

        public float speed;// 速度
        private final float acceleratedSpeed = 0.15f;// 加速度，目前恒定
        private final float speedMax = 5;

        public int curPos;
        public int length;// 路径总长度
        private int startX = 300, startY = 0, controlX, controlY, controlX2, controlY2, endX, endY;
        private float[] p = new float[2];//坐标点，很重要

        private int time;// 执行的次数
        private int scaleTime = 20;// 放大

        private Rect src;
        private Rect dst;

        private Bitmap bitmap;

        private int bitmapWidth;// 图片宽度
        private int bitmapHeight;// 图片高度

        private int bitmapWidthDst;// 最终放大宽度
        private int bitmapHeightDst;// 最终放大高度


        private int alpha = 255;// 透明度范围 0-255
        private final int alphaOffset = 5;// 透明度偏移量

        /**
         * 初始化路径
         * @param width 控件宽度
         * @param height 控件高度
         * @param bitmap 绘制的图片
         */
        public PathObj(int width, int height, Bitmap bitmap) {
            this.bitmap = bitmap;
            this.bitmapWidth = bitmap.getWidth();
            this.bitmapHeight = bitmap.getHeight();

            bitmapWidthDst = bitmapWidth;// + bitmapWidth/4;
            bitmapHeightDst = bitmapHeight;// + bitmapHeight/4;

            startX = width/2;
            startY = height;
            endY = 0;
            curPos = 0;
            endX = random.nextInt(width/2) + width/4;
            controlX = random.nextInt(width - bitmapWidthDst/2) + bitmapWidthDst/4;
            controlY = random.nextInt(height/2) + height/2;
            controlX2 = random.nextInt(width - bitmapWidthDst/2) + bitmapWidthDst/4;
            controlY2 = random.nextInt(height/2) + height/4;

            src = new Rect(0,0,bitmapWidth, bitmapHeight);
            dst = new Rect(0,0,bitmapWidthDst/2, bitmapHeightDst/2);

            paint = new Paint();
            paint.setAntiAlias(true);

            path = new Path();
            pathMeasure = new PathMeasure();
            path.moveTo(startX, startY);
            path.cubicTo(controlX, controlY, controlX2, controlY2, endX, endY);
            pathMeasure.setPath(path, false);
            length = (int) pathMeasure.getLength();
//			speed = random.nextFloat()*3f;
            speed = random.nextInt(1) + 1f;

        }

        /**
         * 获取bitmap，用来canvas
         * @return
         */
        public Bitmap getBitmap() {
            return bitmap;
        }

        /**
         * 画笔
         * @return
         */
        public Paint getPaint() {
            return paint;
        }

        /**
         * 获取起始Rect
         * @return
         */
        public Rect getSrcRect() {
            return src;
        }

        /**
         * 暂时不用了
         * @return
         */
        @Deprecated
        public float[] getPos() {
            curPos += speed;
            speed += acceleratedSpeed;
            if(curPos > length) {
                curPos = length;
                return null;
            }
            pathMeasure.getPosTan(curPos, p, null);
            time++;
            alpha();
            return p;
        }


        /**
         * 获取目标Rect，根据当前点坐标计算Rect，已经加入放大/淡出动画
         * @return
         */
        public Rect getDstRect() {

            curPos += speed;
            if(speed < speedMax) {
                speed += acceleratedSpeed;
            }
            if(curPos > length) {
                curPos = length;
                return null;
            }

            pathMeasure.getPosTan(curPos, p, null);

            if(time < scaleTime) {
                // 放大动画
                float s = (float)time/scaleTime;
                dst.left = (int) (p[0] - bitmapWidthDst/2 *s);
                dst.right = (int) (p[0] + bitmapWidthDst/2 *s);
                dst.top = (int) ((p[1] - bitmapHeightDst * s));
                dst.bottom = (int) (p[1]);
            } else {
                dst.left = (int) (p[0] - bitmapWidthDst/2);
                dst.right = (int) (p[0] + bitmapWidthDst/2);
                dst.top = (int) (p[1] - bitmapHeightDst);
                dst.bottom = (int) (p[1]);
            }
            time++;
            // 淡出动画
            alpha();
            return dst;
        }

        private int alpha() {
//			if(time > 120) {
//				alpha -= 10;
//				if(alpha < 0) {
//					alpha = 0;
//				}
//				paint.setAlpha(alpha);
//			}

            int offset = length - curPos;
            if(offset < length/2) {
                alpha -= alphaOffset;
                if(alpha < 0) {
                    alpha = 0;
                }
                paint.setAlpha(alpha);
            } else if (offset <= 10) {
                alpha = 0;
                paint.setAlpha(alpha);
            }
            return 0;
        }

        public int getAlpha(){
            return alpha;
        }

        /**
         * 获取当前执行次数
         * @return
         */
        public int getTime() {
            return time;
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        flag = false;
        th = null;
        if(bitmap != null && !bitmap.isRecycled()) {
            bitmap.recycle();
        }
        if(pagerBitmap != null && !pagerBitmap.isRecycled()) {
            pagerBitmap.recycle();
        }
    }


}