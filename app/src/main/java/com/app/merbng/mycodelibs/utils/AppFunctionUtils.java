package com.app.merbng.mycodelibs.utils;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.view.View;

import com.app.merbng.mycodelibs.A_SharedBottomSheetDialog.SharedBottomSheetDialog;
import com.app.merbng.mycodelibs.Constent;
import com.app.merbng.mycodelibs.R;

import java.util.ArrayList;

import za.co.riggaroo.materialhelptutorial.TutorialItem;
import za.co.riggaroo.materialhelptutorial.tutorial.MaterialTutorialActivity;

/**
 * Created by Merbng on 2016/9/19.
 */

public class AppFunctionUtils {

    /**
     * @param mContext
     */
    public  static void loadTutorial(Context mContext) {
        Intent mainAct = new Intent(mContext, MaterialTutorialActivity.class);
        mainAct.putParcelableArrayListExtra(MaterialTutorialActivity.MATERIAL_TUTORIAL_ARG_TUTORIAL_ITEMS, getTutorialItems(mContext));
        mainAct.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(mainAct);
    }

    private  static ArrayList<TutorialItem> getTutorialItems(Context context) {
        TutorialItem tutorialItem1 = new TutorialItem(R.string.slide_1_african_story_books, R.string.slide_1_african_story_books,
                R.color.slide_1, R.drawable.tut_page_1_front, R.drawable.tut_page_1_background);

        TutorialItem tutorialItem2 = new TutorialItem(R.string.slide_2_volunteer_professionals, R.string.slide_2_volunteer_professionals_subtitle,
                R.color.slide_2, R.drawable.tut_page_2_front, R.drawable.tut_page_2_background);

        TutorialItem tutorialItem3 = new TutorialItem(context.getString(R.string.slide_3_download_and_go), null,
                R.color.slide_3, R.drawable.tut_page_3_foreground);

        TutorialItem tutorialItem4 = new TutorialItem(R.string.slide_4_different_languages, R.string.slide_4_different_languages_subtitle,
                R.color.slide_4, R.drawable.tut_page_4_foreground, R.drawable.tut_page_4_background);

        ArrayList<TutorialItem> tutorialItems = new ArrayList<>();
        tutorialItems.add(tutorialItem1);
        tutorialItems.add(tutorialItem2);
        tutorialItems.add(tutorialItem3);
        tutorialItems.add(tutorialItem4);

        return tutorialItems;
    }

    /**分享
     * @param localContext
     */
    public static void share(Context localContext) {
        SharedBottomSheetDialog.Builder builder = new SharedBottomSheetDialog.Builder(localContext).setTitle("Android 学习库")
                .setUrl(Constent.SHARE);
        builder.setImgUrl("http://static.cnbetacdn.com/thumb/article/2016/0802/faa9017db6a78a2.jpg_600x600.jpg");
        builder.show();
//        localContext.startActivity(shareText(Constent.SHARE));
    }
    public static Intent shareText(String url) {
        Intent localIntent = new Intent("android.intent.action.SEND");
        localIntent.setType("text/plain");
        localIntent.putExtra("android.intent.extra.TEXT", url);
        return localIntent;
    }






    public static boolean mIsAnimatingOut = false;

    /**底部导航栏隐藏
     * @param bottomBar
     */
    public static void animateOut(View bottomBar) {
        ViewCompat.animate(bottomBar).translationY(bottomBar.getMeasuredHeight())
                .setInterpolator(INTERPOLATOR).withLayer()
                .setListener(new ViewPropertyAnimatorListener() {
                    @Override
                    public void onAnimationStart(View view) {
                        mIsAnimatingOut = true;
                    }

                    @Override
                    public void onAnimationEnd(View view) {
                        mIsAnimatingOut = false;
                        view.setVisibility(View.GONE);
                    }

                    @Override
                    public void onAnimationCancel(View view) {
                        mIsAnimatingOut = false;

                    }
                }).start();
    }

    /**顶部标题栏隐藏
     * @param bottomBar
     */
    public static void animateTitleOut(View bottomBar) {
        ViewCompat.animate(bottomBar).translationY(-bottomBar.getMeasuredHeight())
                .setInterpolator(INTERPOLATOR).withLayer()
                .setListener(new ViewPropertyAnimatorListener() {
                    @Override
                    public void onAnimationStart(View view) {
                        mIsAnimatingOut = true;
                    }

                    @Override
                    public void onAnimationEnd(View view) {
                        mIsAnimatingOut = false;
                        view.setVisibility(View.GONE);
                    }

                    @Override
                    public void onAnimationCancel(View view) {
                        mIsAnimatingOut = false;

                    }
                }).start();
    }

    private static final android.view.animation.Interpolator INTERPOLATOR = new FastOutSlowInInterpolator();

    /**底部导航栏显示
     * @param bottomBar
     */
    public static void animateIn(View bottomBar) {
        bottomBar.setVisibility(View.VISIBLE);
        ViewCompat.animate(bottomBar).translationY(0)
                .setInterpolator(INTERPOLATOR).withLayer().setListener(null)
                .start();
    }

    /**顶部标题栏显示
     * @param bottomBar
     */
    public static void animateTitleIn(View bottomBar) {
        bottomBar.setVisibility(View.VISIBLE);
        ViewCompat.animate(bottomBar).translationY(0)
                .setInterpolator(INTERPOLATOR).withLayer().setListener(null)
                .start();
    }
}
