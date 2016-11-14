package com.app.merbng.mycodelibs.activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.app.merbng.mycodelibs.R;
import com.app.merbng.mycodelibs.base.BaseActivity;
import com.wingsofts.threedlayout.ThreeDLayout;

/**一秒让你的view拥有3D效果！
 * https://github.com/githubwing/ThreeDLayout
 */
public class Layout3DActivity extends BaseActivity {
    ThreeDLayout tdAvatar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout3_d);
        tdAvatar = (ThreeDLayout) findViewById(R.id.td_avatar);
        tdAvatar.setTouchMode(ThreeDLayout.MODE_BOTH_X_Y);
        tdAvatar.setMaxRotateDegree(30);

    }
}
