package com.tranphunguyen.listmarker

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.widget.EditText
import android.widget.FrameLayout
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : Activity(), ListSelectionFragment.OnListItemFragmentInteractionListener {
    private var listSelectionFragment: ListSelectionFragment = ListSelectionFragment.newInstance()
    private var fragmentContainer: FrameLayout? = null
    private var largeScreen = false
    private var listDetailFragment: ListDetailFragment? = null

    override fun onFragmentInteraction(list: TaskList) {
        showListDetail(list)
    }

    companion object {
        const val INTENT_LIST_KEY = "list"
        const val LIST_DETAIL_REQUEST_CODE = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fragmentContainer = findViewById(R.id.fragment_container)

        largeScreen = fragmentContainer != null



        fab.setOnClickListener {
            showCreateListDialog()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == LIST_DETAIL_REQUEST_CODE) {
            data?.let {
                listSelectionFragment.saveList(data.getParcelableExtra(INTENT_LIST_KEY))
            }
        }
    }

    private fun showCreateListDialog() {
        val dialogTitle = getString(R.string.name_of_list)
        val positiveButtonTitle = getString(R.string.create_list)
        val builder = AlertDialog.Builder(this)
        val listTitleEditText = EditText(this)
        listTitleEditText.inputType = InputType.TYPE_CLASS_TEXT

        with(builder) {
            setTitle(dialogTitle)
            setView(listTitleEditText)
            setPositiveButton(positiveButtonTitle) { dialog, _ ->
                val task = listTitleEditText.text.toString()
                listDetailFragment?.addTask(task)
                dialog.dismiss()
            }
            create()
            show()
        }
    }

    private fun showListDetail(list: TaskList) {

        if (!largeScreen) {
            val listDetailIntent = Intent(this, ListDetailActivity::class.java)
            listDetailIntent.putExtra(INTENT_LIST_KEY, list)

            startActivityForResult(listDetailIntent, LIST_DETAIL_REQUEST_CODE)
        } else {
            title = list.name
            listDetailFragment = ListDetailFragment.newInstance(list)

            fragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_container, listSelectionFragment)
                    .addToBackStack(null)
                    .commit()

            fab.setOnClickListener {
                showCreateListDialog()
            }
        }

    }
}
