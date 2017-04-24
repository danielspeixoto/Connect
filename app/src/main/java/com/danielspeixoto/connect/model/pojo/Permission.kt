package com.danielspeixoto.connect.model.pojo

import com.danielspeixoto.connect.R
import com.danielspeixoto.connect.util.App
import com.danielspeixoto.connect.util.Permissions.MANAGE_OFFERS
import com.danielspeixoto.connect.util.Permissions.MANAGE_PAYMENT
import com.danielspeixoto.connect.util.Permissions.MANAGE_TICKET
import com.danielspeixoto.connect.util.Permissions.MANAGE_USERS
import com.danielspeixoto.connect.util.Permissions.VIEW_HISTORY

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
            MANAGE_TICKET -> this.localeName = App.getStringResource(R.string.manage_tickets)
            MANAGE_USERS -> this.localeName = App.getStringResource(R.string.manage_users)
            VIEW_HISTORY -> this.localeName = App.getStringResource(R.string.history)
            MANAGE_OFFERS -> this.localeName = App.getStringResource(R.string.manage_offers)
            MANAGE_PAYMENT -> this.localeName = App.getStringResource(R.string.manage_payment)
        }
    }
}