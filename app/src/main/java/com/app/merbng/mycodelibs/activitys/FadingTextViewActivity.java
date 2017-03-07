package com.app.merbng.mycodelibs.activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.app.merbng.mycodelibs.R;
import com.app.merbng.mycodelibs.base.BaseActivity;

/**每隔几秒自动更改其内容的textview
 * https://github.com/rosenpin/FadingTextView
 */
public class FadingTextViewActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fading_text_view);
    }
}
