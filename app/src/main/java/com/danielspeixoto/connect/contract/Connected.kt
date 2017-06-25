package com.danielspeixoto.connect.contract

import android.support.v4.widget.SwipeRefreshLayout
import com.danielspeixoto.connect.view.recycler.adapter.VisitorAdapter

interface Connected {

    interface View : ActivityBase.View

    interface Presenter : ActivityBase.Presenter {
        var refreshLayout: SwipeRefreshLayout?
        var adapter: VisitorAdapter?
        fun syncItems()
    }
}
