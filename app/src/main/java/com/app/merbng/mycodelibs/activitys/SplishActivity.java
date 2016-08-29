package com.app.merbng.mycodelibs.activitys;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.app.merbng.mycodelibs.R;
import com.app.merbng.mycodelibs.base.BaseActivity;
import com.app.merbng.mycodelibs.utils.SharedPrefUtils;
import com.app.merbng.mycodelibs.widget.SplashView;

import java.util.ArrayList;

import za.co.riggaroo.materialhelptutorial.TutorialItem;
import za.co.riggaroo.materialhelptutorial.tutorial.MaterialTutorialActivity;

/**
 * 闪屏页
 */
public class SplishActivity extends AppCompatActivity {
    private static final int REQUEST_CODE = 1234;
    private boolean isLoged;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splish);
        isLoged = SharedPrefUtils.getBoolean(this, "isLogin");
        SplashView.showSplashView(this, 3, R.drawable.img_splash, new SplashView.OnSplashViewActionListener() {
            @Override
            public void onSplashImageClick(String actionUrl) {
                if(!isLoged){//未登录状态
                    Intent intent = new Intent(SplishActivity.this,
                            Login_ResisterActivity.class); // 从启动动画ui跳转到主ui
                    startActivity(intent);
                    overridePendingTransition(R.anim.excess_fade_anim_in, R.anim.excess_fade_anim_out);
                    finish();
                }else {
                Intent intent = new Intent(SplishActivity.this, MainActivity.class);
                startActivity(intent);
                loadTutorial();
                }
            }

            @Override
            public void onSplashViewDismiss(boolean initiativeDismiss) {
                if(!isLoged){//未登录状态
                    Intent intent = new Intent(SplishActivity.this,
                            Login_ResisterActivity.class); // 从启动动画ui跳转到主ui
                    startActivity(intent);
                    overridePendingTransition(R.anim.excess_fade_anim_in, R.anim.excess_fade_anim_out);
                    finish();
                }else {
                    Intent intent = new Intent(SplishActivity.this, MainActivity.class);
                    startActivity(intent);
                    loadTutorial();
                }
            }
        });
    }
    public void loadTutorial() {
        Intent mainAct = new Intent(SplishActivity.this, MaterialTutorialActivity.class);
        mainAct.putParcelableArrayListExtra(MaterialTutorialActivity.MATERIAL_TUTORIAL_ARG_TUTORIAL_ITEMS, getTutorialItems(this));
        startActivityForResult(mainAct, REQUEST_CODE);
    }

    private  ArrayList<TutorialItem> getTutorialItems(Context context) {
        TutorialItem tutorialItem1 = new TutorialItem(R.string.slide_1_african_story_books, R.string.slide_1_african_story_books,
                R.color.slide_1, R.drawable.tut_page_1_front,  R.drawable.tut_page_1_background);

        TutorialItem tutorialItem2 = new TutorialItem(R.string.slide_2_volunteer_professionals, R.string.slide_2_volunteer_professionals_subtitle,
                R.color.slide_2,  R.drawable.tut_page_2_front,  R.drawable.tut_page_2_background);

        TutorialItem tutorialItem3 = new TutorialItem(context.getString(R.string.slide_3_download_and_go), null,
                R.color.slide_3, R.drawable.tut_page_3_foreground);

        TutorialItem tutorialItem4 = new TutorialItem(R.string.slide_4_different_languages, R.string.slide_4_different_languages_subtitle,
                R.color.slide_4,  R.drawable.tut_page_4_foreground, R.drawable.tut_page_4_background);

        ArrayList<TutorialItem> tutorialItems = new ArrayList<>();
        tutorialItems.add(tutorialItem1);
        tutorialItems.add(tutorialItem2);
        tutorialItems.add(tutorialItem3);
        tutorialItems.add(tutorialItem4);

        return tutorialItems;
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //    super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE){
           finish();
        }
    }
}
