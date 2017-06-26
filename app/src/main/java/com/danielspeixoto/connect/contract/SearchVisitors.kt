package com.danielspeixoto.connect.contract

import com.danielspeixoto.connect.view.recycler.adapter.VisitorAdapter

/**
 * Created by daniel on 25/06/17.
 */
interface SearchVisitors {

    interface View : ActivityBase.View

    interface Presenter : ActivityBase.Presenter {
        var adapter: VisitorAdapter?
        fun search(query : String)
    }

}