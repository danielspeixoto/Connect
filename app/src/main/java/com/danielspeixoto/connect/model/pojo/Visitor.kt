package com.danielspeixoto.connect.model.pojo

/**
 * Created by danielspeixoto on 4/25/17.
 */
data class Visitor(var name : String) {

    var observations : String? = null
    var phone: Number? = null
    var age: Int? = null
    var activities = ArrayList<String>()
    var observers = ArrayList<String>()

}