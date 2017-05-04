package com.danielspeixoto.connect.contract

import com.danielspeixoto.connect.view.recycler.adapter.UserAdapter
import com.danielspeixoto.connect.view.recycler.adapter.VisitorAdapter

/**
 * Created by danielspeixoto on 5/3/17.
 */
interface Users {

    interface View : ActivityBase.View

    interface Presenter : ActivityBase.Presenter {
        var adapter: UserAdapter?
        fun syncItems()
    }

}