package com.app.merbng.mycodelibs.activitys;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.app.merbng.mycodelibs.R;
import com.app.merbng.mycodelibs.base.BaseActivity;
import com.app.merbng.mycodelibs.widget.TitleBar;

/**一个简单易用的导航栏TitleBar，可以轻松实现IOS导航栏的各种效果整个代码全部集中在TitleBar.java中，
 * 所有控件都动态生成，动态布局。不需要引用任何资源文件，拷贝TitleBar.java到自己工程即可使用
 * Created by merbng on 2016/3/27.
 */

public class TitleBarActivity extends BaseActivity {

    private ImageView mCollectView;
    private boolean mIsSelected;
    private TitleBar titleBar;
    private TitleBar.Action action;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_titlebar);
        if (hasKitKat() && !hasLollipop()) {
//            透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        } else if (hasLollipop()) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
        titleBar = (TitleBar) findViewById(R.id.titleBar);
        titleBar.setImmersive(true);
        titleBar.setBackgroundColor(Color.parseColor("#ffffff"));//状态栏整体渲染颜色
        titleBar.setLeftImageResource(R.drawable.skin_iconfont_back);
        titleBar.setLeftText("返回");
        titleBar.setLeftTextColor(Color.BLACK);
        titleBar.setLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        titleBar.setTitle("这个标题栏真的很吊的！很吊的！很吊的");
        titleBar.setTitleColor(Color.RED);
        titleBar.setSubTitleColor(Color.YELLOW);
        titleBar.setDividerColor(Color.GREEN);
        titleBar.setActionTextColor(Color.BLUE);
        action = new TitleBar.Action() {
            @Override
            public String getText() {
                return null;
            }

            @Override
            public int getDrawable() {
                return 0;
            }

            @Override
            public void performAction(View view) {
                Toast.makeText(TitleBarActivity.this, "点击了收藏", Toast.LENGTH_SHORT).show();
                mCollectView.setImageResource(R.drawable.skin_iconfont_ok);
                titleBar.setTitle(mIsSelected ? "文章详情\n朋友圈" : "帖子详情");
                mIsSelected = !mIsSelected;

            }
        };
        mCollectView = (ImageView) titleBar.addAction(action);

//        titleBar.addAction(new TitleBar.TextAction("发布") {
//            @Override
//            public void performAction(View view) {
//                Toast.makeText(MainActivity.this, "点击了发布", Toast.LENGTH_SHORT).show();
//
//            }
//        });


    }

    public void btnClick(View v) {
        titleBar.removeAction(action);
    }

    public static boolean hasKitKat() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
    }

    public static boolean hasLollipop() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    }
}
