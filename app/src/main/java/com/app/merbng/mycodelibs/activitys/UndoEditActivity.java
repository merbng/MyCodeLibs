package com.app.merbng.mycodelibs.activitys;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;

import com.app.merbng.mycodelibs.R;
import com.app.merbng.mycodelibs.base.BaseActivity;

import ren.qinc.edit.PerformEdit;

/**
 * EditText的撤销和恢复（反撤销）
 * https://github.com/qinci/AndroidEdit
 */
public class UndoEditActivity extends BaseActivity implements View.OnClickListener {
    private EditText mEditText;
    private PerformEdit mPerformEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_undo_edit);
        mEditText = ((EditText) findViewById(R.id.editText));
        findViewById(R.id.action_undo).setOnClickListener(this);
        findViewById(R.id.action_redo).setOnClickListener(this);
        findViewById(R.id.action_clear).setOnClickListener(this);
        mPerformEdit = new PerformEdit(mEditText) {
            @Override
            protected void onTextChanged(Editable s) {
                //文本发生改变,可以是用户输入或者是EditText.setText触发.(setDefaultText的时候不会回调)
                super.onTextChanged(s);
            }
        };

        mPerformEdit.setDefaultText("这是初始值,不能撤销的值");
    }

    @Override
    public void onClick(View v) {
        int itemId = v.getId();
        if (itemId == R.id.action_undo) {
            mPerformEdit.undo();
        } else if (itemId == R.id.action_redo) {
            mPerformEdit.redo();
        } else if (itemId == R.id.action_clear) {
            mPerformEdit.clearHistory();
        }
    }
}