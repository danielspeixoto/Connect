package com.danielspeixoto.connect.util

import com.danielspeixoto.connect.R
import com.danielspeixoto.connect.model.pojo.User
import com.danielspeixoto.connect.model.pojo.Visitor

/**
 * Created by danielspeixoto on 2/14/17.
 */

object Validate {

    val OK = "OK"
    private var message: String = OK

    fun User(user: User): String {
        message = OK
        if (!user.username!!.matches("[a-z | 0-9]*".toRegex())) {
            message = App.getStringResource(R.string.username_lowercase)
        }
        return message
    }

    fun  visitor(visitor: Visitor): String {
        message = OK
        if (visitor.name.isEmpty()) {
            message = App.getStringResource(R.string.name_must_fill)
        }
        return message
    }


}
