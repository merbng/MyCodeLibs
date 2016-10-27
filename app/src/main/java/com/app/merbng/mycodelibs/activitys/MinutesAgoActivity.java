package com.app.merbng.mycodelibs.activitys;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.app.merbng.mycodelibs.R;
import com.app.merbng.mycodelibs.utils.DateUtils;
import com.app.merbng.mycodelibs.widget.MinutesAgoTixtView;

import java.util.concurrent.TimeUnit;

/**
 * 几分钟前
 * 将时间戳转换为友好显示的时间,用于聊天
 */
public class MinutesAgoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minutes_ago);

        MinutesAgoTixtView agoTixtView = (MinutesAgoTixtView) findViewById(R.id.minuteago_date);
        MinutesAgoTixtView agoTixtView2 = (MinutesAgoTixtView) findViewById(R.id.minuteago_date2);
        MinutesAgoTixtView agoTixtView3 = (MinutesAgoTixtView) findViewById(R.id.minuteago_date3);
        MinutesAgoTixtView agoTixtView4 = (MinutesAgoTixtView) findViewById(R.id.minuteago_date4);
        TextView textView = (TextView) findViewById(R.id.tv_other);

        String currentUTCTime = DateUtils.getCurrentUTCTime();

        agoTixtView.setWhen(DateUtils.string2long(currentUTCTime), TimeUnit.SECONDS);
        agoTixtView2.setWhen(DateUtils.string2long("2014-6-20 10:22:200"), TimeUnit.SECONDS);
        agoTixtView3.setWhen(DateUtils.string2long("2014-8-20 10:22:200"), TimeUnit.SECONDS);
        agoTixtView4.setWhen(DateUtils.string2long("2016-9-26 18:32:200"), TimeUnit.SECONDS);

        textView.setText(DateUtils.convertTimeToFriendly(DateUtils.string2long(currentUTCTime)));
    }
}
