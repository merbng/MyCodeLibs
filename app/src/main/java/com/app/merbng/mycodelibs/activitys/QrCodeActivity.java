package com.app.merbng.mycodelibs.activitys;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.merbng.mycodelibs.R;
import com.app.merbng.mycodelibs.base.BaseActivity;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

/**
 * 快速集成二维码扫描功能
 * https://github.com/yipianfengye/android-zxingLibrary
 */
public class QrCodeActivity extends BaseActivity {
    private Button btn_scan, btn_scanalbumscan, btn_create;
    private TextView tv_result, tv_albumresult;
    private ImageView imgv_showqrcode;
    private EditText edit_input;
    private static final int REQUEST_CODE = 111;
    private static final int REQUEST_IMAGE = 112;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_code);
        fvbId();
        openQrCode();
    }

    private void fvbId() {
        btn_scan = (Button) findViewById(R.id.btn_scan);
        btn_scanalbumscan = (Button) findViewById(R.id.btn_scanalbum);
        btn_create = (Button) findViewById(R.id.btn_create);
        tv_result = (TextView) findViewById(R.id.tv_result);
        tv_albumresult = (TextView) findViewById(R.id.tv_albumresult);
        imgv_showqrcode = (ImageView) findViewById(R.id.imgv_showqrcode);
        edit_input = (EditText) findViewById(R.id.edit_input);
    }

    private void openQrCode() {
        /**
         * 打开默认二维码扫描界面
         */
        btn_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, CaptureActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
        /**
         * 打开相册扫描图片
         * */
        btn_scanalbumscan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_IMAGE);
            }
        });
        /**
         * 生成二维码图片
         */
        btn_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textContent = edit_input.getText().toString();
                if (TextUtils.isEmpty(textContent)) {
                    show("您的输入为空!");
                    return;
                }
                edit_input.setText("");
                Bitmap mBitmap = CodeUtils.createImage(textContent, 400, 400, BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
                imgv_showqrcode.setImageBitmap(mBitmap);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /**
         * 处理二维码扫描结果
         */
        if (requestCode == REQUEST_CODE) {
            //处理扫描结果（在界面上显示）
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    tv_result.setText("解析结果:" + result);
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    show("解析二维码失败");
                }
            }
        } else if (requestCode == REQUEST_IMAGE) {
            if (data != null) {
                Uri uri = data.getData();
                ContentResolver cr = getContentResolver();
                try {
                    Bitmap mBitmap = MediaStore.Images.Media.getBitmap(cr, uri);//显得到bitmap图片
                    //fixme 这个地方容易OOM
                    try {
                        CodeUtils.analyzeBitmap(mBitmap, new CodeUtils.AnalyzeCallback() {
                            @Override
                            public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
                                tv_albumresult.setText("扫描相册解析结果:" + result);
                            }
    
                            @Override
                            public void onAnalyzeFailed() {
                                show("解析二维码失败");
                            }
                        });
                    } catch (OutOfMemoryError e) {
                        tv_albumresult.setText("图片太大！");
                        e.printStackTrace();
                    }

                    if (mBitmap != null) {
                        mBitmap.recycle();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
