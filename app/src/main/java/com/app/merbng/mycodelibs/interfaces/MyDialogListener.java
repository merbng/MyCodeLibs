package com.app.merbng.mycodelibs.interfaces;

import android.content.DialogInterface;

/**
 * Created by zx on 2016/8/30.
 */
public abstract class MyDialogListener {
    public abstract void onFirst(DialogInterface dialog);

    public abstract void onSecond(DialogInterface dialog);

    public void onThird(DialogInterface dialog) {
    }

    public void onCancle() {
    }
}
