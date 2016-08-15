package com.app.merbng.mycodelibs.A_materiallogin;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.transition.Explode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.app.merbng.mycodelibs.R;
import com.app.merbng.mycodelibs.base.BaseActivity;

/**
 * MD风格的登陆注册页
 * https://github.com/fanrunqi/MaterialLogin
 */
public class MaterialLoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btGo;
    private CardView cv;
    private FloatingActionButton fab;
    private EditText etUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materiallogin);
        fvbId();

    }

    private void fvbId() {
        etUsername = ((EditText) findViewById((R.id.et_username)));
        btGo = ((Button) findViewById(R.id.bt_go));
        cv = ((CardView) findViewById(R.id.cv));
        fab = ((FloatingActionButton) findViewById(R.id.fab));
        fab.setOnClickListener(this);
        btGo.setOnClickListener(this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab:
                getWindow().setExitTransition(null);
                getWindow().setEnterTransition(null);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options =
                            ActivityOptions.makeSceneTransitionAnimation(this, fab, fab.getTransitionName());
                    startActivity(new Intent(this, RegisterActivity.class), options.toBundle());
                } else {
                    startActivity(new Intent(this, RegisterActivity.class));
                }
                break;
            case R.id.bt_go:
                Explode explode = new Explode();
                explode.setDuration(500);

                getWindow().setExitTransition(explode);
                getWindow().setEnterTransition(explode);
                ActivityOptionsCompat oc2 = ActivityOptionsCompat.makeSceneTransitionAnimation(this);
                Intent i2 = new Intent(this, LoginSuccessActivity.class);
                startActivity(i2, oc2.toBundle());
                break;
        }
    }
}
