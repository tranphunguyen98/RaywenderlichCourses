package com.tranphunguyen.listmarker

import android.content.Context
import android.preference.PreferenceManager

class ListDataManager (val context: Context) {

//    private object Holder {
//        val INSTANCE  =  ListDataManager()
//    }
//
//    companion object {
//        @JvmStatic
//        fun getInstance(context: Context) : ListDataManager {
//            return Holder.INSTANCE
//        }
//    }
    fun saveList(list: TaskList) {
        val editor = PreferenceManager.getDefaultSharedPreferences(context).edit()
        editor.putStringSet(list.name, list.tasks.toHashSet())
        editor.apply()
    }

    fun readTask(name : String) : ArrayList<String> {
        var listTask: ArrayList<String>

        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        listTask = ArrayList(sharedPreferences.getStringSet(name,HashSet<String>()).toHashSet())
        return listTask
    }
    fun readList() : ArrayList<TaskList> {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        val sharedpreferenceContents = sharedPreferences.all
        val tasklists = ArrayList<TaskList>()

        for(taskList in sharedpreferenceContents) {
            val itemsHashSet = taskList.value as HashSet<String>
            val list = TaskList(taskList.key,ArrayList(itemsHashSet))

            tasklists.add(list)
        }
        return tasklists
    }
}