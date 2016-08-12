package com.app.merbng.mycodelibs.activitys;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.View;

import com.androidadvance.topsnackbar.TSnackbar;
import com.app.merbng.mycodelibs.R;
import com.app.merbng.mycodelibs.base.BaseActivity;
import com.app.merbng.mycodelibs.utils.SnackBarUtils;

/**
 * 顶部 Snackbar
 */
public class SnackbarTopActivity extends BaseActivity {

    private TSnackbar snackBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snackbar_top);
        snackBar = TSnackbar.make(findViewById(android.R.id.content), "这是顶部的 TSnackBar.", TSnackbar.LENGTH_INDEFINITE);
        findViewById(R.id.btn_openSnack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!snackBar.isShown()) {
                    snackBar.show();
                }
            }
        });
        findViewById(R.id.btn_closeSnack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (snackBar.isShown()) {
                    snackBar.dismiss();
                }
            }
        });
        findViewById(R.id.btn_Snack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar snackbar1 = Snackbar.make(v, "原生的 SnackBar", Snackbar.LENGTH_SHORT).setAction("消失", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        show("点了");
                    }
                });
                snackbar1.show();
            }
        });
        findViewById(R.id.btn_Snack_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SnackBarUtils.IndefiniteSnackbar(v, "Snack 1", 400, SnackBarUtils.Info).show();
            }
        });

    }

}
