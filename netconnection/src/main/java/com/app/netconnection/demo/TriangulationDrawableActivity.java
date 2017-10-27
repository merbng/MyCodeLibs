package com.app.netconnection.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.app.a1merbng.tempdemo.R;

import cn.nekocode.triangulation.TriangulationDrawable;

/**动态背景
 * https://github.com/nekocode/TriangulationDrawable
 * Created by merbng on 2017/10/27.
 */

public class TriangulationDrawableActivity  extends AppCompatActivity {
    private TriangulationDrawable triangulationDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_triangulation);
        triangulationDrawable = new TriangulationDrawable();
        findViewById(android.R.id.content).setBackground(triangulationDrawable);
    }
    @Override
    protected void onStart() {
        super.onStart();
        triangulationDrawable.start();
    }

    @Override
    protected void onStop() {
        triangulationDrawable.stop();
        super.onStop();
    }
}
