package com.danielspeixoto.connect.module

/**
 * Created by danielspeixoto on 2/15/17.
 */

class Login {

    interface View : ActivityBase.View

    interface Presenter : ActivityBase.Presenter {
        fun logIn(username: String, password: String)
    }

}
