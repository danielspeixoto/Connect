package com.danielspeixoto.connect.contract

import com.danielspeixoto.connect.model.pojo.User

/**
 * Created by danielspeixoto on 5/3/17.
 */
interface CreateUser {

    interface View : ActivityBase.View

    interface Presenter : ActivityBase.Presenter {
        fun create(user: User)
    }

}