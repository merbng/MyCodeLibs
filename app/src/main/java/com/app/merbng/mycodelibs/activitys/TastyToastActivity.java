package com.app.merbng.mycodelibs.activitys;

import android.os.Bundle;
import android.view.View;

import com.app.merbng.mycodelibs.R;
import com.app.merbng.mycodelibs.base.BaseActivity;
import com.sdsmdg.tastytoast.TastyToast;

/**有趣的Toast
 * https://github.com/yadav-rahul/TastyToast
 */
public class TastyToastActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasty_toast);
        
    }
    public void btn_Click(View view){
        switch (view.getId()){
            case R.id.btn1:
                TastyToast.makeText(getApplicationContext(), "Hello World ----SUCCESS!", TastyToast.LENGTH_LONG, TastyToast.SUCCESS);
                break;
            case R.id.btn2:
                TastyToast.makeText(getApplicationContext(), "Hello World !WARNING", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                break;
            case R.id.btn3:
                TastyToast.makeText(getApplicationContext(), "Hello World !ERROR", TastyToast.LENGTH_LONG, TastyToast.ERROR);
                break;
            case R.id.btn4:
                TastyToast.makeText(getApplicationContext(), "Hello World !INFO", TastyToast.LENGTH_LONG, TastyToast.INFO);
                break;
            case R.id.btn5:
                TastyToast.makeText(getApplicationContext(), "Hello World !DEFAULT", TastyToast.LENGTH_LONG, TastyToast.DEFAULT);
                break;
        }
    }
}
