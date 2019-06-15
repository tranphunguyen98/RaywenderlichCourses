package com.tranphunguyen.listmarker

import android.app.Fragment
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class ListDetailFragment : Fragment() {
    private var listener: OnFragmentInteractionListener? = null
    private lateinit var list: TaskList
    lateinit var listItemRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        list = arguments.getParcelable(MainActivity.INTENT_LIST_KEY)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_list_detail, container, false)
        view?.let {
            listItemRecyclerView = it.findViewById(R.id.list_items_recyclerview)
            listItemRecyclerView.adapter = ListDetailAdapter(list.tasks)
            listItemRecyclerView.layoutManager = LinearLayoutManager(activity)
        }
        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnFragmentInteractionListener {
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {

        @JvmStatic
        fun newInstance(list: TaskList): ListDetailFragment {
            val fragment = ListDetailFragment()
            val args = Bundle()
            args.putParcelable(MainActivity.INTENT_LIST_KEY, list)
            fragment.arguments = args
            return fragment
        }

    }

    fun addTask(item : String) {
        list.tasks.add(item)

        val listRecyclerAdapter = listItemRecyclerView.adapter as ListDetailAdapter
        listRecyclerAdapter.list = list.tasks
        listRecyclerAdapter.notifyDataSetChanged()

    }
}
