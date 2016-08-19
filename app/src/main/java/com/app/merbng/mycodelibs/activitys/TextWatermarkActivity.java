package com.app.merbng.mycodelibs.activitys;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.app.merbng.mycodelibs.R;
import com.app.merbng.mycodelibs.base.BaseActivity;
import com.app.merbng.mycodelibs.utils.ColorUtils;
import com.app.merbng.mycodelibs.utils.ImageUtils;

/**
 * 图片加文字水印
 */
public class TextWatermarkActivity extends BaseActivity {

    private ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_watermark);
        img = ((ImageView) findViewById(R.id.imgv_addWather));
        final Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bg);
        findViewById(R.id.btn_addWather).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        Bitmap bitmap1 = ImageUtils.drawTextToCenter(mContext, bitmap, "这是标题呜呜呜", 56, ColorUtils.getRandomColor());
                        Bitmap bitmap2 = ImageUtils.GetRoundedCornerBitmap(bitmap1);
                            img.setImageBitmap(bitmap2);
                    }
                });
            }
        });
        findViewById(R.id.btn_randomColor).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img.setBackgroundColor(ColorUtils.getRandomColor());
            }
        });
    }

}
