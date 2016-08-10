package com.app.merbng.mycodelibs.activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.androidadvance.topsnackbar.TSnackbar;
import com.app.merbng.mycodelibs.R;
import com.app.merbng.mycodelibs.base.BaseActivity;

/**
 * 顶部 Snackbar
 */
public class SnackbarTopActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snackbar_top);
        TSnackbar.make(findViewById(android.R.id.content),"Hello from TSnackBar.", TSnackbar.LENGTH_INDEFINITE).show();
    }

}
