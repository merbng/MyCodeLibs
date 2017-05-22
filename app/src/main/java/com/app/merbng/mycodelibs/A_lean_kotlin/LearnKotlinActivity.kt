package com.app.merbng.mycodelibs.A_lean_kotlin

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.app.merbng.mycodelibs.R
import com.app.merbng.mycodelibs.base.BaseActivity
import kotlinx.android.synthetic.main.activity_learn_kotlin.*
import kotlinx.android.synthetic.main.activity_studyrecycle.*
import java.util.*

class LearnKotlinActivity : BaseActivity() {
    var adapter: RecyclerViewAdapter? = null
    var list = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_learn_kotlin)
        initView();
        tv_1.setText("Hellow ，Kotlin")
        tv_2.setText("welcome！")
        tv_1.setOnClickListener {}

    }

    private fun initView() {
        for (i in 0..20) {
            list.add("我是" + i)
        }
        recycleView.layoutManager = LinearLayoutManager(this)
        adapter = RecyclerViewAdapter(this, list)
        recycleView.adapter = adapter

    }
}
