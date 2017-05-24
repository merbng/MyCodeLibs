package com.app.merbng.mycodelibs.activitys

import android.os.Bundle
import android.os.Handler

import com.app.merbng.mycodelibs.R
import com.app.merbng.mycodelibs.base.BaseActivity
import com.xiaochendev.lib.HintAnimEditText

import java.util.ArrayList

class EditTextHintAnimActivity : BaseActivity() {
    internal var mEditText: HintAnimEditText? = null
    internal var data: MutableList<CharSequence>

    internal var handler = Handler()

    internal var mPos = 0
    private var mRunnable: Runnable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edittext_hint_anim)
        //构造数据
        data = ArrayList<CharSequence>()
        data.add("微信")
        data.add("QQ")
        data.add("大众点评")
        data.add("淘宝")
        data.add("暮光之城")


        mEditText = findViewById(R.id.edittxt) as HintAnimEditText

        //设置初始显示
        mEditText.setHintString("搜索")

        mRunnable = object : Runnable {
            override fun run() {
                mEditText.changeHintWithAnim(data[mPos])
                mPos++
                if (mPos >= data.size) {
                    mPos = 0
                }
                handler.postDelayed(this, 3000)
            }
        }

        handler.postDelayed(mRunnable, 3000)


    }
    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacks(mRunnable)
    }
}