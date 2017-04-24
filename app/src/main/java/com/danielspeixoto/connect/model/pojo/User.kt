package com.danielspeixoto.connect.model.pojo

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by danielspeixoto on 4/21/17.
 */
data class User constructor(var username: String = "", var password: String = "") : Parcelable {
    var name: String = ""

    var group: String = ""

    var token: String = ""

    var permissions: HashMap<String, Boolean> = HashMap()

    companion object {
        @JvmField val CREATOR: Parcelable.Creator<User> = object : Parcelable.Creator<User> {
            override fun createFromParcel(source: Parcel): User = User(source)
            override fun newArray(size: Int): Array<User?> = arrayOfNulls(size)
        }
    }

    constructor(source: Parcel) : this(source.readString(), source.readString())

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeString(username)
        dest?.writeString(password)
    }
}