package com.danielspeixoto.connect.model.pojo

/**
 * Created by danielspeixoto on 4/21/17.
 */
data class User constructor(var username: String = "", var password: String = "") {
    var name: String = ""
    var group: String = ""
    var token: String = ""
    var permissions: HashMap<String, Boolean> = null!!

}