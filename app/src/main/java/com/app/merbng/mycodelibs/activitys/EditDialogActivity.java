package com.app.merbng.mycodelibs.activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.app.merbng.mycodelibs.R;
import com.app.merbng.mycodelibs.base.BaseActivity;
import com.app.merbng.mycodelibs.utils.DialogUtils;

/**
 * 带编辑框的Dialog
 */
public class EditDialogActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_dialog);
        DialogUtils.createEditDialog(mContext, "喂喂喂", "请输入", "内容", new DialogUtils.GetUrlName() {
            @Override
            public void getname(String str) {
                show("输入：" + str);
            }
        }).show();
    }
}
