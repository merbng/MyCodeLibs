package com.app.merbng.mycodelibs.activitys;

import android.os.Bundle;
import android.view.View;

import com.app.merbng.mycodelibs.R;
import com.app.merbng.mycodelibs.base.BaseActivity;

import cn.carbs.android.autozoominimageview.library.AutoZoomInImageView;

/**
 * 图片不断向中间放大的动画效果
 * https://github.com/Carbs0126/AutoZoomInImageView
 */
public class AutozoomActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autozoom);
        final AutoZoomInImageView zoomInImageView = (AutoZoomInImageView) findViewById(R.id.auto_zoomin_image_view);
        zoomInImageView.post(new Runnable() {
            @Override
            public void run() {
                //简单方式启动放大动画
                //放大增量是0.3，放大时间是1000毫秒，放大开始时间是1000毫秒以后
//                auto_zoomin_image_view.init()
//                  .startZoomInByScaleDeltaAndDuration(0.3f, 1000, 1000);

                //使用较为具体的方式启动放大动画
                zoomInImageView.init()
                        .setScaleDelta(0.2f)//放大的系数是原来的（1 + 0.2）倍
                        .setDurationMillis(11500)//动画的执行时间为1500毫秒
                        .setOnZoomListener(new AutoZoomInImageView.OnZoomListener() {
                            @Override
                            public void onStart(View view) {
                                //放大动画开始时的回调
                            }

                            @Override
                            public void onUpdate(View view, float progress) {
                                //放大动画进行过程中的回调 progress取值范围是[0,1]
                            }

                            @Override
                            public void onEnd(View view) {
                                //放大动画结束时的回调
                            }
                        })
                        .start(1000);//延迟1000毫秒启动
            }
        });
    }
}
