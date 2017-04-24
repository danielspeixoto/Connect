package com.danielspeixoto.connect.util

import com.danielspeixoto.connect.R
import com.danielspeixoto.connect.model.pojo.User

/**
 * Created by danielspeixoto on 2/14/17.
 */

object Validate {

    val OK = "OK"
    private var message: String = OK

    fun User(user: User): String {
        message = OK
        if (user.username.isEmpty()) {
            message = App.getStringResource(R.string.username_must_fill)
        } else if (!user.username.matches("[a-z | 0-9]*".toRegex())) {
            message = App.getStringResource(R.string.username_lowercase)
        }
        return message
    }
}
