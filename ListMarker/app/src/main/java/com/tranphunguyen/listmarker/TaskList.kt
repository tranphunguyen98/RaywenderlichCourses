package com.tranphunguyen.listmarker

import android.os.Parcel
import android.os.Parcelable

class TaskList constructor(val name: String, var tasks: ArrayList<String> =
        ArrayList()) : Parcelable {
    //Second constructor
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.createStringArrayList()
            )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeStringList(tasks)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<TaskList> {
        override fun createFromParcel(parcel: Parcel): TaskList {
            return TaskList(parcel)
        }

        override fun newArray(size: Int): Array<TaskList?> {
            return arrayOfNulls(size)
        }
    }
}