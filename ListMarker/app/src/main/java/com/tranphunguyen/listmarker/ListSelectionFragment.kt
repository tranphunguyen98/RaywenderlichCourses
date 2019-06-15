package com.tranphunguyen.listmarker

import android.app.Fragment
import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [ListSelectionFragment.OnListItemFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [ListSelectionFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class ListSelectionFragment : Fragment(),
        ListMakerAdapter.ListMakerClickListener {

    lateinit var listDataManager: ListDataManager
    lateinit var listsRecyclerView: RecyclerView
    private var listener: OnListItemFragmentInteractionListener? = null

    interface OnListItemFragmentInteractionListener {
        fun onFragmentInteraction(list: TaskList)
    }

    companion object {
        @JvmStatic
        fun newInstance(): ListSelectionFragment {
            return ListSelectionFragment()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnListItemFragmentInteractionListener) {
            listener = context
            listDataManager = ListDataManager(context)
        } else {
            throw RuntimeException("$context must implement OnListItemFragmentInteractionListener")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_selection, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val list = listDataManager.readList()
        view?.let {
            listsRecyclerView = it.findViewById(R.id.listMatker)
            listsRecyclerView.layoutManager = LinearLayoutManager(activity)
            listsRecyclerView.adapter = ListMakerAdapter(list, this)
        }
    }

    override fun listItemClicked(list: TaskList) {
        listener?.onFragmentInteraction(list)
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    fun addList(list: TaskList) {
        listDataManager.saveList(list)

        val recyclerViewAdapter = listsRecyclerView.adapter as ListMakerAdapter
        recyclerViewAdapter.addList(list)
    }

    fun saveList(list: TaskList) {
        listDataManager.saveList(list)
        updateLists()
    }

    private fun updateLists() {
        val lists = listDataManager.readList()
        listsRecyclerView.adapter = ListMakerAdapter(lists, this)
    }
}
