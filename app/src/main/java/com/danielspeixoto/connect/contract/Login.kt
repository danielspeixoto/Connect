package com.danielspeixoto.connect.contract

/**
 * Created by danielspeixoto on 2/15/17.
 */

interface Login {

    interface View : ActivityBase.View {
        fun setMessageViewText(message : String)
    }

    interface Presenter : ActivityBase.Presenter {
        fun logIn(username: String, password: String)
    }

}
