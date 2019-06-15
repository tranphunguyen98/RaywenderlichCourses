package com.tranphunguyen.listmarker

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_list_detail.*

class ListDetailActivity : Activity() {

    private lateinit var listItemDetail: RecyclerView
    private lateinit var listDataManager: ListDataManager
    private lateinit var adapter: ListDetailAdapter

    private var fragmentDetail: ListDetailFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_detail)
        listItemDetail = this.findViewById(R.id.list_item_detail)

        listDataManager = ListDataManager(this)

        setupRecyclerview()

//        fabAdd.setOnClickListener {
//            showTaskDialog()
//        }
    }

    override fun onBackPressed() {
        val bundle = Bundle()
//        bundle.putParcelable(MainActivity.INTENT_LIST_KEY,list)

        val intent = Intent()
        intent.putExtras(bundle)
        setResult(RESULT_OK,intent)
        super.onBackPressed()
    }

    private fun setupRecyclerview() {
        listItemDetail.layoutManager = LinearLayoutManager(this)
        listItemDetail.adapter = adapter
    }

//    private fun showTaskDialog() {
//        val builder = AlertDialog.Builder(this)
//        val editText = EditText(this)
//        val strPositive = getString(R.string.create_list)
//
//        with(builder) {
//            setTitle(title)
//            setView(editText)
//            setPositiveButton(strPositive) { dialog, _ ->
//                val strTask = editText.text.toString()
//
//                saveNewTask(strTask)
//
//                dialog.dismiss()
//            }
//            create()
//            show()
//        }
//    }

//    private fun saveNewTask(task: String) {
//        list.tasks.add(task)
//        adapter.notifyItemInserted(list.tasks.size)
//    }
}
