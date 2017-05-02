package com.danielspeixoto.connect.model.pojo

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by danielspeixoto on 4/25/17.
 */
data class Visitor(var _id: String? = null,
                   var name: String,
                   var observations: String? = null,
                   var phone: Int? = null,
                   var age: Int? = null,
                   var activities: ArrayList<String> = arrayListOf<String>(),
                   var observers: ArrayList<String> = arrayListOf<String>(),
                   var isConnected: Boolean = false) : Parcelable {

    companion object {
        @JvmField val CREATOR: Parcelable.Creator<Visitor> = object : Parcelable.Creator<Visitor> {
            override fun createFromParcel(source: Parcel): Visitor = Visitor(source)
            override fun newArray(size: Int): Array<Visitor?> = arrayOfNulls(size)
        }
    }

    constructor(names : String) : this (name = names)

    constructor(source: Parcel) : this(
            source.readString(),
            source.readString(),
            source.readString(),
            source.readValue(Int::class.java.classLoader) as Int?,
            source.readValue(Int::class.java.classLoader) as Int?,
            source.createStringArrayList(),
            source.createStringArrayList(),
            1.equals(source.readInt())
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeString(_id)
        dest?.writeString(name)
        dest?.writeString(observations)
        dest?.writeValue(phone)
        dest?.writeValue(age)
        dest?.writeStringList(activities)
        dest?.writeStringList(observers)
        dest?.writeInt((if (isConnected) 1 else 0))
    }
}