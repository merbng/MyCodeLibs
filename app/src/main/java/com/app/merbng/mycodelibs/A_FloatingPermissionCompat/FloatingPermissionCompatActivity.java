package com.app.merbng.mycodelibs.A_FloatingPermissionCompat;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.app.merbng.mycodelibs.R;
import com.linchaolong.android.floatingpermissioncompat.FloatingPermissionCompat;


/**
 * Android 下悬浮窗权限兼容库
 * https://github.com/linchaolong/FloatingPermissionCompat
 */
public class FloatingPermissionCompatActivity extends AppCompatActivity {
    private Context mContext;
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext =FloatingPermissionCompatActivity.this;
        setContentView(R.layout.activity_floating_permission_compat);
        findViewById(R.id.btn_show_or_apply).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                // 检查是否已经授权
                if (FloatingPermissionCompat.get().check(mContext)) {
                    FloatWindowManager.get().show(mContext);
                } else {
                    // 授权提示
                    new AlertDialog.Builder(mContext).setTitle("悬浮窗权限未开启")
                            .setMessage("你的手机没有授权" + mContext.getString(R.string.app_name) + "获得悬浮窗权限，视频悬浮窗功能将无法正常使用")
                            .setPositiveButton("开启", new DialogInterface.OnClickListener() {
                                @Override public void onClick(DialogInterface dialog, int which) {
                                    // 显示授权界面
                                    FloatingPermissionCompat.get().apply(mContext);
                                }
                            })
                            .setNegativeButton("取消", null).show();
                }
            }
        });

        findViewById(R.id.btn_dismiss).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                FloatWindowManager.get().dismiss();
            }
        });
    }
}
