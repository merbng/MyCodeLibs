package com.app.merbng.mycodelibs.utils;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.TypedValue;

import com.app.merbng.mycodelibs.R;


/**谷歌原生刷新
 * Created by merbng on 2016/9/7.
 */

public class AppRefreshUtils {
    public static void initRefresh(Context mContext, SwipeRefreshLayout refreshLayout) {
//        设置颜色
        refreshLayout.setColorSchemeResources(R.color.red_error);
//       设置距离屏幕的偏移量
        refreshLayout.setProgressViewOffset(false, 0, (int) TypedValue
                .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100, mContext.getResources()
                        .getDisplayMetrics()));
    }
}
