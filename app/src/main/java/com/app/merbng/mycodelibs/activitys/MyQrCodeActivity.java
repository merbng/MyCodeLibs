package com.app.merbng.mycodelibs.activitys;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.merbng.mycodelibs.R;
import com.app.merbng.mycodelibs.base.BaseActivity;
import com.app.merbng.mycodelibs.utils.QRUtils;
import com.bumptech.glide.Glide;

/**
 * 我的二维码
 */
public class MyQrCodeActivity extends BaseActivity implements View.OnTouchListener {
    public ImageView userDetail_img;
    public String userId;
    private String nickName;//标题上方的 昵称
    public final static String USERID_KEY = "userId";//从上一个界面跳转的key
    public final static String NICKNAME_KEY = "nickName";//从上一个界面跳转的key
    public final static String COVER_KEY = "coverUrl";//从上一个界面跳转的key
    public final static String INTRO_KEY = "INTRO";//从上一个界面跳转的key  简介
    public final static String TYPE = "QRTYPE";//从上一个界面跳转的key  folder不显示头像。
    public final static String TYPE_HEARD = "HEARD";//从上一个界面跳转的key  folder不显示头像。
    public final static String TYPE_CARD = "CARD";//从上一个界面跳转的key  folder不显示头像。
    public final static String TYPE_FOLDER = "FOLDER";//从上一个界面跳转的key  folder不显示头像。
    private String url;
    private ImageView heardImg;
    private TextView tv_nickName;
    private TextView tv_intro;
    private TextView tv_bottom;//底部文字
    private String coverUrl;//话题封面
    private String intro;//简介
    private RelativeLayout rl_qr;
    private LinearLayout ll_heard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_qr_code);
        fvbId();
        init();
    }

    @Override
    public void hand_message(Message msg) {
        switch (msg.what) {
            case 1002:
                userDetail_img.setImageBitmap(QRUtils.createHeardImgCode(mContext, url, BitmapFactory.decodeResource(getResources(), R.drawable.github)));
                break;
        }
    }

    public void fvbId() {
        rl_qr = (RelativeLayout) findViewById(R.id.rl_qr);
        userDetail_img = (ImageView) findViewById(R.id.user_detail_img);
        heardImg = (ImageView) findViewById(R.id.code_rigv_heardImg);
        tv_nickName = (TextView) findViewById(R.id.code_tv_nickName);
        tv_intro = (TextView) findViewById(R.id.code_tv_intro);
        tv_bottom = (TextView) findViewById(R.id.tv_bottom);
        ll_heard = (LinearLayout) findViewById(R.id.ll_heard);
    }

    public void init() {
        userId = getIntent().getStringExtra(USERID_KEY);
        nickName = getIntent().getStringExtra(NICKNAME_KEY);
        coverUrl = getIntent().getStringExtra(COVER_KEY);
        url = userId;
        new Thread(new Runnable() {
            @Override
            public void run() {
                Message message = myHandler.obtainMessage();
                message.what = 1002;
                myHandler.sendMessage(message);
            }
        }).start();

        tv_nickName.setText(nickName);
        if (getIntent().hasExtra(INTRO_KEY)) {
            tv_intro.setVisibility(View.VISIBLE);
            intro = getIntent().getStringExtra(INTRO_KEY);
            tv_intro.setText(intro);
        } else {
            tv_intro.setVisibility(View.GONE);
        }
        if (getIntent().hasExtra(TYPE)) {
            String CodeType = getIntent().getStringExtra(TYPE);
            switch (CodeType) {
                case TYPE_HEARD:
                    tv_bottom.setText(getString(R.string.scan_qr_see_mycollect_card));
                    break;
                case TYPE_CARD:
                    tv_bottom.setText(getString(R.string.scan_qr_see_mycollect_card));
                    break;
                case TYPE_FOLDER:
                    tv_bottom.setText(getString(R.string.scan_qr_see_mycollect_card));
                    ll_heard.setVisibility(View.GONE);
                    break;
            }
        }
        Glide.with(mContext).load(coverUrl).placeholder(R.drawable.github).error(R.drawable.github).into(heardImg);
        rl_qr.setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            finish();
        }
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.BUTTON_BACK) {
            finish();
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void onBackPressed() {
        finish();
    }

}
