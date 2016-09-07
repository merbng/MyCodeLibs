package com.app.merbng.mycodelibs.A_windowManager;

import android.app.Service;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.Toast;

import com.app.merbng.mycodelibs.utils.MIUI;


public class ClipboardService extends Service {

    private ClipboardManager clipboardManager;

    private String clipString;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(final Intent intent, int flags, int startId) {


        if (clipboardManager == null) {
            clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        }

        if (clipString == null) {
            clipString = "";
        }


        clipboardManager.addPrimaryClipChangedListener(new ClipboardManager.OnPrimaryClipChangedListener() {
            @Override
            public void onPrimaryClipChanged() {


                CharSequence text = clipboardManager.getPrimaryClip().getItemAt(0).getText();

                if (text != null) {

                    String s = text.toString();

                    if (!TextUtils.equals(s, clipString)) {

                        final UcNotification notification =
                                new UcNotification.Builder()
                                        .setContext(ClipboardService.this)
                                        .setContent(s)
                                        .setOnClickNotificationListener(new UcNotification.OnClickNotificationListener() {
                                            @Override
                                            public void onClickThenDismiss(String clipString) {
                                                Toast.makeText(ClipboardService.this, "收藏成功", Toast.LENGTH_SHORT).show();
//                                                Toast.makeText(ClipboardService.this, "clicked：" + clipString, Toast.LENGTH_SHORT).show();
//                                                Intent intent = new Intent(ClipboardService.this, MainActivity.class);
//                                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//
//                                                ClipboardService.this.startActivity(intent);
                                            }
                                        }).build();
                        if (MIUI.isFloatWindowOpAllowed(ClipboardService.this)) {
                            notification.show();
                        } else {
                            Toast.makeText(ClipboardService.this, "需要打开权限", Toast.LENGTH_SHORT).show();
                        }
                    }
                    clipString = s;

                }


            }
        });

        return Service.START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
