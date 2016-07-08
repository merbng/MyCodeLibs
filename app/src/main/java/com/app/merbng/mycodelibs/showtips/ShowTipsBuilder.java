package com.app.merbng.mycodelibs.showtips;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.view.View;

import com.app.merbng.mycodelibs.interfaces.ShowTipsViewInterface;


/**
 * 提示引导页
 * Created by wxp on 2016/3/31.
 */
public class ShowTipsBuilder {
    ShowTipsView showtipsView;

    public ShowTipsBuilder(Activity activity) {
        this.showtipsView = new ShowTipsView(activity);
    }

    /**
     * Set highlight view. All view will be highlighted
     *
     * @param v Target view
     * @return ShowTipsBuilder
     */
    public ShowTipsBuilder setTarget(View v) {
        this.showtipsView.setTarget(v);
        return this;
    }

    /**
     * Set highlighted view with custom center and radius
     *
     * @param v      Target View
     * @param x      circle center x according target
     * @param y      circle center y according target
     * @param radius
     * @return
     */
    public ShowTipsBuilder setTarget(View v, int x, int y, int radius) {
        showtipsView.setTarget(v, x, y, radius);

        return this;
    }

    public ShowTipsView build() {
        return showtipsView;
    }

    public ShowTipsBuilder setTitle(String text) {
        this.showtipsView.setTitle(text);
        return this;
    }

    public ShowTipsBuilder setDescription(String text) {
        this.showtipsView.setDescription(text);
        return this;
    }

    public ShowTipsBuilder displayOneTime(int showtipId) {
        this.showtipsView.setDisplayOneTime(true);
        this.showtipsView.setDisplayOneTimeID(showtipId);
        return this;
    }

    public ShowTipsBuilder setCallback(ShowTipsViewInterface callback) {
        this.showtipsView.setCallback(callback);
        return this;
    }

    public ShowTipsBuilder setDelay(int delay) {
        showtipsView.setDelay(delay);
        return this;
    }

    public ShowTipsBuilder setTitleColor(int color) {
        showtipsView.setTitle_color(color);
        return this;
    }

    public ShowTipsBuilder setDescriptionColor(int color) {
        showtipsView.setDescription_color(color);
        return this;
    }

    public ShowTipsBuilder setBackgroundColor(int color) {
        showtipsView.setBackground_color(color);
        return this;
    }

    public ShowTipsBuilder setCircleColor(int color) {
        showtipsView.setCircleColor(color);
        return this;
    }

    public ShowTipsBuilder setButtonMargin(int rightMargin, int bottomMargin) {
        this.showtipsView.setButtonTextMargin(rightMargin, bottomMargin);
        return this;
    }

    public ShowTipsBuilder setButtonText(String text) {
        this.showtipsView.setButtonText(text);
        return this;
    }

    public ShowTipsBuilder setCloseButtonColor(int color) {
        this.showtipsView.setButtonColor(color);
        return this;
    }

    public ShowTipsBuilder setCloseButtonTextColor(int color) {
        this.showtipsView.setButtonTextColor(color);
        return this;
    }

    public ShowTipsBuilder setButtonBackground(Drawable drawable) {
        this.showtipsView.setCloseButtonDrawableBG(drawable);
        return this;
    }

    /**
     * Set transparecy for background layer. 0-255 range
     *
     * @param alpha
     * @return ShowTipsbuilder
     */
    public ShowTipsBuilder setBackgroundAlpha(int alpha) {
        this.showtipsView.setBackground_alpha(alpha);
        return this;
    }
}