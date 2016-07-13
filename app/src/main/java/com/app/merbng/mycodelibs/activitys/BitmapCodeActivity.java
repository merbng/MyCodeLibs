package com.app.merbng.mycodelibs.activitys;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.app.merbng.mycodelibs.R;
import com.app.merbng.mycodelibs.utils.BitmapUtils;
import com.app.merbng.mycodelibs.utils.QRCodeUtil;


/**
 * 艺术二维码
 */
public class BitmapCodeActivity extends AppCompatActivity {
    String cev = "发现世界，留住精彩~";

    ImageView imageView;
    View bt;
    Bitmap[] bitmaps;
    Bitmap bm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bitmapqrcode);
        imageView = (ImageView) findViewById(R.id.img_code);
        bt = findViewById(R.id.bt);
        bitmaps = new Bitmap[7];
        bitmaps[0] = BitmapUtils.readBitMap(this, R.drawable.deg);
        bitmaps[1] = BitmapUtils.readBitMap(this, R.drawable.ebo);
        bitmaps[2] = BitmapUtils.readBitMap(this, R.drawable.ecn);
        bitmaps[3] = BitmapUtils.readBitMap(this, R.drawable.eco);
        bitmaps[4] = BitmapUtils.readBitMap(this, R.drawable.eep);
        bitmaps[5] = BitmapUtils.readBitMap(this, R.drawable.eer);
        bitmaps[6] = BitmapUtils.readBitMap(this, R.drawable.eft);
        bm = BitmapUtils.readBitMap(this, R.drawable.kys);
        imageView.setImageBitmap(QRCodeUtil.CreateQRCodeBitmap(cev, 1000, bitmaps, bm));
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap bitmap = QRCodeUtil.CreateQRCodeBitmap(cev, 1000, bitmaps, bm);
                bitmap = QRCodeUtil.withIcon(bitmap, BitmapUtils.readBitMap(BitmapCodeActivity.this, R.mipmap.ic_launcher), 0.2f);
                imageView.setImageBitmap(bitmap);
            }
        });
    }
}
