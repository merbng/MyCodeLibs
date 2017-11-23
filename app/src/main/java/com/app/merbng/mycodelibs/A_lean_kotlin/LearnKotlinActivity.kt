package com.app.merbng.mycodelibs.A_lean_kotlin

import android.os.Bundle
import com.app.merbng.mycodelibs.R
import com.app.merbng.mycodelibs.base.BaseActivity
import java.util.*

class LearnKotlinActivity : BaseActivity() {
    var adapter: RecyclerViewAdapter? = null
    var list = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_learn_kotlin)
    }
}
