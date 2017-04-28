package com.danielspeixoto.connect.model.pojo


/**
 * Created by danielspeixoto on 4/21/17.
 */
data class User(var username: String? = null,
                var password: String? = null,
                var name: String? = null,
                val group: String? = null,
                var token: String? = null,
                var permissions: HashMap<String, Boolean> = HashMap())