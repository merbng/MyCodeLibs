package com.app.merbng.mycodelibs.A_lean_kotlin

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.merbng.mycodelibs.R

/**
 * Created by merbng on 2017/5/23.
 */
class RecyclerViewAdapter(val context: Context, val list: List<String>) : RecyclerView.Adapter<RecyclerViewAdapter.RecyclerHolder>() {
    override fun getItemCount(): Int {
        return list.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerHolder {
        return RecyclerHolder(LayoutInflater.from(context).inflate(R.layout.itme_kotlin, parent, false))
    }


    override fun onBindViewHolder(holder: RecyclerHolder, position: Int) {

    }

    class RecyclerHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

    }
}