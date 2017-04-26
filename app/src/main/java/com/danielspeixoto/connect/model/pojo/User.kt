package com.danielspeixoto.connect.model.pojo

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


/**
 * Created by danielspeixoto on 4/21/17.
 */
data class User constructor(@SerializedName("_id") @Expose var username: String, var password: String) {

    lateinit var name: String

    var group: String? = null

    var token: String? = null

    var permissions: HashMap<String, Boolean> = HashMap()

    val values
     get() = "$group $name $username $password"

}