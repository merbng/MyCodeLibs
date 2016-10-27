package com.app.merbng.mycodelibs.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.text.format.DateUtils;
import android.util.AttributeSet;
import android.widget.TextView;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**将时间戳转换为友好显示的时间,用于聊天
 * Created by merbng on 2016/8/29.
 */
public class MinutesAgoTixtView extends TextView {
    public MinutesAgoTixtView(Context paramContext) {
        super(paramContext);
        init();
    }

    public MinutesAgoTixtView(Context paramContext, AttributeSet paramAttributeSet) {
        super(paramContext, paramAttributeSet);
        init();
    }

    public MinutesAgoTixtView(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
        super(paramContext, paramAttributeSet, paramInt);
        init();
    }

    @TargetApi(21)
    public MinutesAgoTixtView(Context paramContext, AttributeSet paramAttributeSet, int paramInt1, int paramInt2) {
        super(paramContext, paramAttributeSet, paramInt1, paramInt2);
        init();
    }

    void init() {
        setSingleLine(true);
    }

    public void setWhen(long unixlong, TimeUnit paramTimeUnit) {
        if (unixlong >= 10000000000L) {
            unixlong = unixlong / 1000;
        }
        super.setText(DateUtils.getRelativeTimeSpanString(TimeUnit.MILLISECONDS.convert(unixlong, paramTimeUnit), System.currentTimeMillis(),
                DateUtils.MINUTE_IN_MILLIS,
                DateUtils.FORMAT_ABBREV_RELATIVE));
    }

    public void setWhen(Date paramDate) {
        setWhen(paramDate.getTime(), TimeUnit.MILLISECONDS);
    }
}