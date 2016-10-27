package com.app.merbng.mycodelibs.activitys;

import android.os.Bundle;

import com.app.merbng.mycodelibs.R;
import com.app.merbng.mycodelibs.base.BaseActivity;
import com.hitomi.cmlibrary.CircleMenu;
import com.hitomi.cmlibrary.OnMenuSelectedListener;
import com.hitomi.cmlibrary.OnMenuStatusChangeListener;

/**
 * 圆形菜单动画
 */
public class CircleMenuActivity extends BaseActivity {
    private CircleMenu circleMenu;

    private int[] iconResArray = new int[5];

    {
        iconResArray[0] = R.drawable.icon_home;
        iconResArray[1] = R.drawable.icon_search;
        iconResArray[2] = R.drawable.icon_notify;
        iconResArray[3] = R.drawable.icon_setting;
        iconResArray[4] = R.drawable.icon_gps;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle_menu);

        circleMenu = (CircleMenu) findViewById(R.id.circle_menu);
        // 设置打开/关闭菜单图标
        circleMenu.setMainIconResource(R.drawable.icon_menu, R.drawable.icon_cancel);
        // 设置一组 Resource 格式的子菜单项图
        circleMenu.setSubIconResources(iconResArray);
        circleMenu.setOnMenuSelectedListener(new OnMenuSelectedListener() {
            @Override
            public void onMenuSelected(int index) {
            }
        });
        circleMenu.setOnMenuStatusChangeListener(new OnMenuStatusChangeListener() {
            @Override
            public void onMenuOpened() {
            }

            @Override
            public void onMenuClosed() {
            }
        });
    }
}