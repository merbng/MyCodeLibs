package com.app.merbng.mycodelibs.activitys;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Toast;

import com.app.merbng.mycodelibs.R;
import com.zhy.m.permission.MPermissions;
import com.zhy.m.permission.PermissionDenied;
import com.zhy.m.permission.PermissionGrant;

import java.io.File;

public class MainActivity extends Activity {
    private static final int REQUECT_CODE_Camera = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btnClick(View v) {
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,Manifest.permission.CAMERA)) {

                Toast.makeText(MainActivity.this,"1111111111",0).show();
            } else { 
                // No explanation needed, we can request the permission.
Toast.makeText(MainActivity.this,"00000000",0).show();
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.READ_CONTACTS},
                        2);


            }
        }else {
            Toast.makeText(MainActivity.this,"333333333",0).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        MPermissions.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @PermissionGrant(REQUECT_CODE_Camera)
    public void requestSdcardSuccess() {
        Toast.makeText(this, "申请GRANT ACCESS SDCARD!", Toast.LENGTH_SHORT).show();

    }

    @PermissionDenied(REQUECT_CODE_Camera)
    public void requestSdcardFailed() {
        Toast.makeText(this, "拒绝DENY ACCESS SDCARD!", Toast.LENGTH_SHORT).show();
    }

    public void openCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);// 打开相机
        String file = Environment.getExternalStorageDirectory() + File.separator + "JanuBookingOnline" + File.separator + "user_head_photo.jpg";
        intent.putExtra("output", Uri.fromFile(new File(file)));
        startActivityForResult(intent, 1);
    }
}


