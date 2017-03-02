package com.app.merbng.mycodelibs.A_MagicFloatView.exp2DropItemAnimation;

import android.content.Context;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.view.ViewGroup;

import com.app.merbng.mycodelibs.R;
import com.app.merbng.mycodelibs.base.BaseActivity;

/**
 *仿直播送花的物品掉落动画
 * https://github.com/wincber/DropItemAnimation
 */
public class DropItemAnimationActivity extends BaseActivity {
     FloatingActionButton spring;
    FloatingActionButton summer;
    FloatingActionButton autumn;
    FloatingActionButton winter;
    DropItemAnimate springAnimate;
    DropItemAnimate summerAnimate;
    DropItemAnimate autumnAnimate;
    DropItemAnimate winterAnimate;
    ViewGroup mViewGroup;

    int []summerItemsId = {R.drawable.ebo,R.drawable.ecn,R.drawable.eco};
    int []autumnItemsId = {R.drawable.eep,R.drawable.eer,R.drawable.eft};
    int []winterItemsId = {R.drawable.bad,R.drawable.good,R.drawable.dec};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drop_item_animation);
        spring = (FloatingActionButton)findViewById(R.id.floatAB_spring);
        summer = (FloatingActionButton)findViewById(R.id.floatingAB_summer);
        autumn = (FloatingActionButton)findViewById(R.id.floatingAB_autumn);
        winter = (FloatingActionButton)findViewById(R.id.floatingAB_winter);
        mViewGroup = (ViewGroup)findViewById(R.id.activity_main);
        spring.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(springAnimate == null){
                    springAnimate = new DropItemAnimate(mContext,mViewGroup);
                }
                springAnimate.startDropP2P(new PointF(mViewGroup.getWidth()/2,mViewGroup.getHeight()),new PointF(mViewGroup.getWidth()/2,0));
            }
        });
        summer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAnimationOnClicked(summerAnimate,mContext,mViewGroup,getDrawableRes(summerItemsId),2);
            }
        });
        autumn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAnimationOnClicked(autumnAnimate,mContext,mViewGroup,getDrawableRes(autumnItemsId),3);
            }
        });
        winter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAnimationOnClicked(winterAnimate,mContext,mViewGroup,getDrawableRes(winterItemsId),4);
            }
        });
    }

    Drawable[] getDrawableRes(int []itemsId){
        Drawable []drawables = new Drawable[itemsId.length];
        for(int i = 0;i < itemsId.length;i++){
            drawables[i] = getResources().getDrawable(itemsId[i]);
        }
        return drawables;
    }
    void setAnimationOnClicked(DropItemAnimate animate, Context context, ViewGroup viewGroup, Drawable[] drawables, int type){
        if(animate == null){
            animate = new DropItemAnimate(context,viewGroup,drawables);
        }
        if(type == 2)
            animate.startDropDown();
        if(type == 3)
            animate.startDropUp();
        if(type == 4)
            animate.startDropRandom2P(new PointF(viewGroup.getWidth()/2,0));
    }

}
