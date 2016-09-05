package com.app.merbng.mycodelibs.A_windowManager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.app.merbng.mycodelibs.R;

/**
 * 仿Uc  监听粘贴板
 */
public class UcNotifyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ucnotify);
    }

    public void show(View view) {
        Toast.makeText(this, "启动剪切板监听服务", Toast.LENGTH_SHORT).show();
        startService(new Intent(this, ClipboardService.class));
    }
}
