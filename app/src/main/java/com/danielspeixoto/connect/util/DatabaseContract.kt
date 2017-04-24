package com.danielspeixoto.connect.util

/**
 * Created by danielspeixoto on 2/14/17.
 */

interface DatabaseContract {
    companion object {

        val USERNAME = "username"
        val GROUP = "group"
        val NAME = "name"
        val PASSWORD = "password"
        val PERMISSIONS = "permissions"

        val LOGIN = "login"
        val DATA = "data"
        val TOKEN = "token"

        val USER = "user"
        val SUCCESS = "success"
        val ERROR = "error"
        val MESSAGE = "msg"
    }
}
