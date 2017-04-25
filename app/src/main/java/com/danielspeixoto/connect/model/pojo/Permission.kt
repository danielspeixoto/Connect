package com.danielspeixoto.connect.model.pojo

import com.danielspeixoto.connect.R
import com.danielspeixoto.connect.util.App
import com.danielspeixoto.connect.util.Permissions.MANAGE_USERS

/**
 * Created by danielspeixoto on 4/21/17.
 */
data class Permission(val name: String, var isAllowed: Boolean = false) {

    private var localeName: String = ""
        get

    init {
        setLocaleName(name)
    }

    private fun setLocaleName(name: String) {
        when (name) {
            MANAGE_USERS -> this.localeName = App.getStringResource(R.string.manage_users)
        }
    }
}