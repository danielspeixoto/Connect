package com.danielspeixoto.connect.contract

import com.danielspeixoto.connect.view.recycler.adapter.VisitorAdapter

/**
 * Created by danielspeixoto on 4/27/17.
 */
interface Home {

    interface View : ActivityBase.View

    interface Presenter : ActivityBase.Presenter {
        var adapter: VisitorAdapter?
        fun syncItems()
    }

}