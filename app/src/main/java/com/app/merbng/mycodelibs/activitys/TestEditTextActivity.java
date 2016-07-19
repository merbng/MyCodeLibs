package com.app.merbng.mycodelibs.activitys;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ActionMode;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;

import com.app.merbng.mycodelibs.R;
import com.app.merbng.mycodelibs.base.BaseActivity;
import com.app.merbng.mycodelibs.utils.EditUtils;
import com.app.merbng.mycodelibs.utils.LogUtil;

/**
 * Android EditText 的使用及值得注意的地方
 * http://mp.weixin.qq.com/s?__biz=MzA3MDMyMjkzNg==&mid=2652261810&idx=1&sn=930ba529f025d8460a6366e7b0595c56&scene=0#wechat_redirect
 */
public class TestEditTextActivity extends BaseActivity {

    private EditText edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_edit_text);
        edit = ((EditText) findViewById(R.id.edit));
        //  监听EditText的输入状态
        listenerState(edit);
        //  监听输入法中的回车按钮
        listenerEnter(edit);
        //  改变输入法中回车按钮的显示内容
        listenerEnterShowText(edit);
//        限制输入内容
        limtInputText(edit);
//        屏蔽EditText的复制、粘贴功能
        shieldingCopyCut(edit);
    }

    /**
     * 在低版本的Android SDK中，如果对EditText的输入长度有限制时，
     * 长按EditText并将选中的内容拖动到EditText输入框中，
     * 如果这时候的长度超过了EditText的输入长度限制，程序会直接崩溃掉，
     * 在高版本的Android SDK中这个问题已经改了，
     * 如果出现上面的情况会直接清空输入框中的内容，为了避免这种讨厌的问题，
     * 我们可以屏蔽EditText的复制和粘贴功能，只需要屏蔽EditText的长按响应即可：
     *
     * @param edit
     */
    private void shieldingCopyCut(EditText edit) {
        edit.setCustomSelectionActionModeCallback(new ActionMode.Callback() {
            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {

                return false;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                return false;
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {

            }
        });
        edit.setLongClickable(false);
    }


    /**
     * 改变输入法中回车按钮的显示内容
     * 如果回车按钮是执行搜索功能，则回车按钮上显示”搜索”，如果是执行发送功能，则显示”发送”,如果是下一步，则显示”下一步”。
     *
     * @param edit
     */
    private void listenerEnterShowText(EditText edit) {
            /*IME_ACTION_SEARCH 搜索
            * IME_ACTION_SEND 发送
            * IME_ACTION_NEXT 下一步
            * IME_ACTION_DONE 完成*/
        edit.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
    }

    /**
     * 监听输入法中的回车按钮
     * 比如搜狗输入法的右下角有一个回车按钮，我们希望用户点击它时也执行确认功能，可以通过监听EditText的按键点击事件来实现：
     *
     * @param edit
     */
    private void listenerEnter(EditText edit) {
        edit.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_UP) {
                    LogUtil.log.e("手指弹起时执行确认功能");
                    return true;
                }
                return false;
            }
        });

    }

    /**
     * 监听EditText的输入状态
     * 类似新浪微博，在输入内容时会提示还可以输入多少字；
     * 有的搜索引擎，输入内容时实时显示搜索结果；
     * 有的输入框有输入长度限制，输入内容超过长度限制时弹出提示信息。
     */
    private void listenerState(EditText edit) {
        edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                LogUtil.log.e("监听EditText输入内容的变化，在这里可以监听输入内容的长度。");
            }

            @Override
            public void afterTextChanged(Editable s) {
                LogUtil.log.e("这里可以实现所输即所得，用户输入的同时可以立即在这里根据输入内容执行操作，显示搜索结果！");

            }
        });
    }

    /**
     * 在初始化EditText时，调用InputTxtFilter的inputFilter方法，传入输入长度限制、输入内容的类型限制等即可
     *
     * @param edit
     */
    private void limtInputText(EditText edit) {
        EditUtils.InputTxtFilter.inputFilter(this, edit, EditUtils.InputTxtFilter.INPUT_TYPE_EN, 5);
    }

}
