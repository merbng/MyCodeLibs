package com.app.merbng.mycodelibs.activitys;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.CharacterStyle;
import android.text.style.ImageSpan;
import android.widget.TextView;

import com.app.merbng.mycodelibs.R;
import com.app.merbng.mycodelibs.base.BaseActivity;

public class ImageSpanActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_span);
        TextView tv_userPref = (TextView) findViewById(R.id.tv_userPref);
        Drawable drawable =getDrawable(R.drawable.iconfont_left_quotes);
        Drawable drawable2 = mContext.getDrawable(R.drawable.iconfont_right_quotes);
        drawable.setBounds( 0,10,drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        drawable2.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());


        String text ="%碗里来浪费了送礼物啦碗里来浪费了送礼物啦碗里来浪费了送礼物啦碗里来浪费了送礼物啦碗里来浪费了送礼物啦碗里来浪费了送礼物啦碗里来浪费了送礼物啦%";
        SpannableStringBuilder spannable=new SpannableStringBuilder(text);
        CharacterStyle span_1=new ImageSpan(drawable);
        CharacterStyle span_2=new ImageSpan(drawable2);
        spannable.setSpan(span_1, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannable.setSpan(span_2, text.length()-1, text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv_userPref.setText(spannable);
    }
}
