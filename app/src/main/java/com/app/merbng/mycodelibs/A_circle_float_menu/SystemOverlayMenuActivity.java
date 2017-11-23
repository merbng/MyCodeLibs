package com.app.merbng.mycodelibs.A_circle_float_menu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.app.merbng.mycodelibs.R;

public class SystemOverlayMenuActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_with_overlay);

        Button button = (Button) findViewById(R.id.startOverlayServiceButton);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
    }


}
