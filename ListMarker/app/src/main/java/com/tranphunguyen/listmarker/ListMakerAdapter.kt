package com.tranphunguyen.listmarker

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

class ListMakerAdapter(private val lists: ArrayList<TaskList>,
                       private val clickListener : ListMakerClickListener) :
        RecyclerView.Adapter<ListViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ListViewHolder {
        val itemView = LayoutInflater.from(p0.context).inflate(R.layout.list_maker_view_holder, p0, false)
        return ListViewHolder(itemView)
    }

    override fun getItemCount(): Int = lists.size

    override fun onBindViewHolder(p0: ListViewHolder, p1: Int) {
        p0.itemNumber.text = (p1 + 1).toString()
        p0.itemString.text = lists[p1].name
        p0.itemView.setOnClickListener {
            clickListener.listItemClicked(lists[p1])
        }
    }

    fun addList(list: TaskList) {
        lists.add(list)
        notifyDataSetChanged()
    }

    interface ListMakerClickListener {
        fun listItemClicked(list: TaskList)
    }
}