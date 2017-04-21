package com.danielspeixoto.connect.util

import com.danielspeixoto.connect.model.pojo.Permission
import java.util.*

/**
 * Created by daniel on 16/04/17.
 */

object Permissions {

    val permissions = ArrayList<String>()
    val permissionsHash = HashMap<String, Boolean>()

    val MANAGE_TICKET = "MANAGE_TICKET"
    val MANAGE_USERS = "MANAGE_USERS"
    val VIEW_HISTORY = "VIEW_HISTORY"
    val MANAGE_OFFERS = "MANAGE_OFFERS"
    val MANAGE_PAYMENT = "MANAGE_PAYMENT"

    init {
        permissions.add(MANAGE_TICKET)
        permissions.add(MANAGE_USERS)
        permissions.add(VIEW_HISTORY)
        permissions.add(MANAGE_OFFERS)
        permissions.add(MANAGE_PAYMENT)
        for (string in permissions) {
            permissionsHash.put(string, false)
        }
    }

    fun generatePermissionsMap(permissions: ArrayList<Permission>): HashMap<String, Boolean> {
        val hashMap = HashMap<String, Boolean>()
        for ((name, isAllowed) in permissions) {
            hashMap.put(name, isAllowed)
        }
        return hashMap
    }

    val permissionsList: ArrayList<Permission>
        get() {
            val permissionsList = ArrayList<Permission>()
            for (string in permissions) {
                permissionsList.add(Permission(string, false))
            }
            return permissionsList
        }
}
