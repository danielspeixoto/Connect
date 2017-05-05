package com.danielspeixoto.connect.model.pojo

import com.google.gson.annotations.SerializedName


/**
 * Created by danielspeixoto on 4/21/17.
 */
data class User(@SerializedName("_id") var username: String? = null,
                var password: String? = null,
                var name: String? = null,
                val group: String? = null,
                var token: String? = null,
                var permissions: HashMap<String, Boolean> = HashMap())