package com.app.merbng.mycodelibs.A_progress;

import android.os.Bundle;
import android.view.View;

import com.app.merbng.mycodelibs.R;
import com.app.merbng.mycodelibs.base.BaseActivity;

/**带阶段节点的进度条
 * https://github.com/SouthernBox/SectionProgressBar
 * Created by Merbng on 2017/3/28.
 */

public class SectionProgressBarActivity extends BaseActivity implements View.OnClickListener {

    private SectionProgressBar sectionProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section_progress);
        initView();
    }

    private void initView() {
        sectionProgressBar = (SectionProgressBar) findViewById(R.id.section_progress_bar);
        findViewById(R.id.btn_stage0).setOnClickListener(this);
        findViewById(R.id.btn_stage1).setOnClickListener(this);
        findViewById(R.id.btn_stage2).setOnClickListener(this);
        findViewById(R.id.btn_stage3).setOnClickListener(this);
        findViewById(R.id.btn_stage4).setOnClickListener(this);
        findViewById(R.id.btn_stage5).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_stage0:
                sectionProgressBar.setProgress(SectionProgressBar.STAGE0);
                break;
            case R.id.btn_stage1:
                sectionProgressBar.setProgress(SectionProgressBar.STAGE1);
                break;
            case R.id.btn_stage2:
                sectionProgressBar.setProgress(SectionProgressBar.STAGE2);
                break;
            case R.id.btn_stage3:
                sectionProgressBar.setProgress(SectionProgressBar.STAGE3);
                break;
            case R.id.btn_stage4:
                sectionProgressBar.setProgress(SectionProgressBar.STAGE4);
                break;
            case R.id.btn_stage5:
                sectionProgressBar.setProgress(SectionProgressBar.STAGE5);
                break;
        }
    }
}