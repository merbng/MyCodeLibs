package com.app.merbng.mycodelibs.activitys;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.app.merbng.mycodelibs.R;
import com.app.merbng.mycodelibs.base.BaseActivity;
import com.app.merbng.mycodelibs.interfaces.ShowTipsViewInterface;
import com.app.merbng.mycodelibs.showtips.ShowTipsBuilder;
import com.app.merbng.mycodelibs.showtips.ShowTipsView;

/**
 * 新用户引导提示页
 */
public class TipShowActivity extends BaseActivity {

    private ShowTipsView showtips2, showtip1, showtips3, showtips4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips_show);
        Button btn_one = (Button) findViewById(R.id.button1);
        Button btn_two = (Button) findViewById(R.id.btn_login_qq);
        Button btn_three = (Button) findViewById(R.id.btn_getInfo);
        Button btn_four = (Button) findViewById(R.id.button4);

        showtip1 = new ShowTipsBuilder(this)
                .setTarget(btn_one).setTitle("一、产品优势")
                .setDescription("产品开发之初，你就必须明白你的产品将具备哪些优势。别把人无我有人有我优挂在嘴上，优在哪里，你要以合适的方式表达出来")
                .setDelay(200)
                .setBackgroundAlpha(28)
                .setCloseButtonColor(Color.RED)
                .setCloseButtonTextColor(Color.GREEN).setButtonText("下一步>").setCallback(new ShowTipsViewInterface() {
                    @Override
                    public void gotItClicked() {
                        showtips2.show(TipShowActivity.this);
                    }
                }).build();
        showtips2 = new ShowTipsBuilder(this)
                .setTarget(btn_two).setTitle("二、两种热情")
                .setDescription("对一款产品的开发，宣传标语的重心应该有两种热情.一种是追求初恋般的热情，只有狂热和激情才会有最伟大的创新创意.一种是呵护孩子般的热情，只有用心，深入，十年如一，才会有最完美的赋予.")
                .setDelay(200)
                .setBackgroundAlpha(100)
                .setCloseButtonColor(Color.RED)
                .setCloseButtonTextColor(Color.GREEN).setButtonText("下一步>").setCallback(new ShowTipsViewInterface() {
                    @Override
                    public void gotItClicked() {
                        showtips3.show(TipShowActivity.this);
                    }
                }).build();
        showtips3 = new ShowTipsBuilder(this)
                .setTarget(btn_three).setTitle("三、关于细节")
                .setDescription("点击此处可以进行各种各样的好玩的操作唷点击此处可以进行各种各样的好玩的操作唷点击此处可以进行各种各样的好玩的操作唷")
                .setDelay(200)
                .setBackgroundAlpha(128)
                .setCloseButtonColor(Color.WHITE)
                .setCloseButtonTextColor(Color.BLACK).setButtonText("下一步>").setCallback(new ShowTipsViewInterface() {
                    @Override
                    public void gotItClicked() {
                        showtips4.show(TipShowActivity.this);
                    }
                }).build();
        showtips4 = new ShowTipsBuilder(this)
                .setTarget(btn_four).setTitle("四、一笔的学问")
                .setDescription("宣传标语只须一笔，可画龙点睛，也可画蛇添足\n我儿子两岁的时候我要教他编程")
                .setDelay(200)
                .setBackgroundAlpha(128)
                .setCloseButtonColor(Color.WHITE).setButtonText("完成>").setTarget(btn_four,200,200,  20)
                .setCloseButtonTextColor(Color.BLACK).build();
        
        btn_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showtip1.show(TipShowActivity.this);
            }
        });

    }


}
