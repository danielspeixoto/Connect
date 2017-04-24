package com.danielspeixoto.connect.module

import com.danielspeixoto.connect.model.pojo.User


/**
 * Created by danielspeixoto on 2/14/17.
 */

class SignUp {

    interface View : ActivityBase.View

    interface Presenter : ActivityBase.Presenter {
        fun signUp(user: User)
    }
}
