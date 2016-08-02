package com.app.merbng.mycodelibs.activitys;

import android.os.Bundle;

import com.app.merbng.mycodelibs.R;
import com.app.merbng.mycodelibs.base.BaseActivity;
import com.flaviofaria.kenburnsview.KenBurnsView;
import com.flaviofaria.kenburnsview.Transition;

/**
 * https://github.com/flavioarfaria/KenBurnsView
 * 图片在切换之前，会缓慢在页面移动或者放大缩小，
 * 然后再慢慢切换过去。
 * 这样的效果使得每一张静止图片都有动态的效果感觉。
 * 类似的效果在电子相册，
 * 或者在电影视频对静态图片的处理中经常可见。
 */
public class KenBurnsViewActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ken_burns_view);

        KenBurnsView kbv = (KenBurnsView) findViewById(R.id.image);

        kbv.setTransitionListener(new KenBurnsView.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {
            }

            @Override
            public void onTransitionEnd(Transition transition) {
            }
        });
//        可以设置动画的插值和过度时间
//        RandomTransitionGenerator generator = new RandomTransitionGenerator(duration, interpolator);
//        kbv.setTransitionGenerator(generator);

//        可以用kbv.pause() 和 kbv.resume();暂停和恢复动画。
    }
}
