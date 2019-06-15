package com.tranphunguyen.listmarker

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView

class ListViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
    val itemNumber = itemView.findViewById<TextView>(R.id.itemNumber)
    val itemString: TextView = itemView.findViewById(R.id.itemString)
}