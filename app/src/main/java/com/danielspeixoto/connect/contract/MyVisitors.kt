package com.danielspeixoto.connect.contract

import com.danielspeixoto.connect.view.recycler.adapter.VisitorAdapter

class MyVisitors {

    interface View : ActivityBase.View {
    }

    interface Presenter : ActivityBase.Presenter {
        var adapter: VisitorAdapter?
        fun getVisitors()
    }
}
