package com.danielspeixoto.connect.util

import com.danielspeixoto.connect.model.pojo.Permission
import java.util.*

/**
 * Created by daniel on 16/04/17.
 */

object Permissions {

    val permissions = ArrayList<String>()
    val permissionsHash = HashMap<String, Boolean>()

    val MANAGE_USERS = "manageUsers"

    init {
        permissions.add(MANAGE_USERS)
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
