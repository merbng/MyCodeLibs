package com.app.merbng.mycodelibs.utils;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.design.widget.TextInputLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.app.merbng.mycodelibs.R;

/**
 * Created by zx on 2016/8/24.
 */
public class DialogUtils {
    public interface GetUrlName {
        void getname(String str);
    }

    public static Dialog createEditDialog(final Context mContext,
                                          String alert_str, String edit_hint, final String edit_text,
                                          final GetUrlName getUrlName) {

        LayoutInflater inflater = LayoutInflater.from(mContext);
        final View v = inflater.inflate(R.layout.creat_dialog, null);// 得到加载view
        final Dialog loadingDialog = new Dialog(mContext, R.style.loading_dialog);// 创建自定义样式dialog
        loadingDialog.setCancelable(true);// 可以用“返回键”取消
        loadingDialog.setCanceledOnTouchOutside(true);// 点击屏幕消失
        loadingDialog.setContentView(v, new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));// 设置布局
        if (null != alert_str) {
            ((TextView) v.findViewById(R.id.dialog_alert_title))
                    .setText(alert_str);

        }
        Button creat_box_cancel = (Button) v
                .findViewById(R.id.creat_box_cancel);
        TextInputLayout til_input = (TextInputLayout) v.findViewById(R.id.til_input);
        Button creat_box_commit = (Button) v
                .findViewById(R.id.creat_box_commit);
        final EditText creat_box_edit = (EditText) v
                .findViewById(R.id.creat_box_edit);
        if (null != edit_hint) {
            til_input.setHint(edit_hint);
            creat_box_edit.setText(edit_text);
            if (edit_text != null) {
                creat_box_edit.setSelection(edit_text.length());
            }

        }
        loadingDialog.setOnShowListener(new DialogInterface.OnShowListener() {

            @Override
            public void onShow(DialogInterface dialog) {
                InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(creat_box_edit,
                        InputMethodManager.SHOW_IMPLICIT);
            }
        });
        creat_box_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                creat_box_edit.setText(edit_text);
                if (edit_text != null) {
                    creat_box_edit.setSelection(edit_text.length());
                }
                loadingDialog.dismiss();
                AppSystemUtils.hideKeyBoard(v.getContext(), v);
            }
        });
        creat_box_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = creat_box_edit.getText().toString().trim();
                if (null == str || str.equals("")) {
                    Toast.makeText(mContext, "未输入！", Toast.LENGTH_SHORT).show();
                } else {
                    getUrlName.getname(str);
                    creat_box_edit.setText(str);
                    if (edit_text != null) {
                        creat_box_edit.setSelection(str.length());
                    }
                    loadingDialog.dismiss();
                    AppSystemUtils.hideKeyBoard(v.getContext(), v);
                }

            }
        });
        return loadingDialog;
    }

    public interface IOkCallBack {
        void okClick();

        void noClick();
    }

    /**
     * MD风格的Dialog
     * 提示框
     *
     * @param title
     * @param content
     * @param callBack
     */
    public static android.support.v7.app.AlertDialog.Builder MDStyleDialog(Context mContext, String title, String content, final IOkCallBack callBack) {
        // 为响应点击事件来创建个弹窗
        android.support.v7.app.AlertDialog.Builder dialog = new android.support.v7.app.AlertDialog.Builder(mContext);
        dialog.setTitle(title);
        // 设置弹窗内容
        dialog.setMessage(content);
        // 设置弹窗的确定按键
        dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                callBack.okClick();
            }
        }).create();
        // 设置弹窗的取消按键
        dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                callBack.noClick();
            }
        }).create();
        // 展示弹窗
        return dialog;
    }

}
