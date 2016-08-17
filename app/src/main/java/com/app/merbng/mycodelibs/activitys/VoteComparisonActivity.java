package com.app.merbng.mycodelibs.activitys;

import android.os.Bundle;

import com.allen.comparsechart.CompareIndicator;
import com.app.merbng.mycodelibs.R;
import com.app.merbng.mycodelibs.base.BaseActivity;

/**
 * 一个简单的投票排名对比图
 * https://github.com/AllenCoder/AndroidCustomView
 */
public class VoteComparisonActivity extends BaseActivity {
    private CompareIndicator CompareIndicator1;
    private CompareIndicator CompareIndicator3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote_comparison);

        this.CompareIndicator1 = (CompareIndicator) findViewById(R.id.CompareIndicator2);
        this.CompareIndicator3 = (CompareIndicator) findViewById(R.id.CompareIndicator3);
        CompareIndicator1.updateView(10,90);
        CompareIndicator3.updateView(70,30);
    }
}
