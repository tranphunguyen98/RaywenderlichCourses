package com.tranphunguyen.listmarker

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import kotlinx.android.synthetic.main.list_maker_view_holder.view.*

class ListDetailAdapter(var list: ArrayList<String>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onBindViewHolder(p0: RecyclerView.ViewHolder, p1: Int) {
        if (p0 is ListViewHolder) {
            p0.itemNumber.text = (p1 + 1).toString()
            p0.itemString.text = list[p1]
        }
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        return when (getItemViewType(p1)) {
            1 -> {
                val itemView = LayoutInflater.from(p0.context).inflate(R.layout.list_maker_view_holder, p0, false)
                ListViewHolder(itemView)
            }
            else -> {
                val itemView = LayoutInflater.from(p0.context).inflate(R.layout.no_data_cell, p0, false)
                NoDataCell(itemView)
            }
        }
    }

    override fun getItemCount(): Int = if(list.size > 0) list.size else 1

    override fun getItemViewType(position: Int): Int {
        return if (list.size > 0) 1 else 0
    }

    fun addList(task: String) {
        list.add(task)
        notifyDataSetChanged()
    }

    inner class NoDataCell(itemView: View) : RecyclerView.ViewHolder(itemView)
}