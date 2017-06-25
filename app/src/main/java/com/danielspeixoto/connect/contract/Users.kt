package com.danielspeixoto.connect.contract

import android.support.v4.widget.SwipeRefreshLayout
import com.danielspeixoto.connect.view.recycler.adapter.UserAdapter

/**
 * Created by danielspeixoto on 5/3/17.
 */
interface Users {

    interface View : ActivityBase.View

    interface Presenter : ActivityBase.Presenter {
        var refreshLayout: SwipeRefreshLayout?
        var adapter: UserAdapter?
        fun syncItems()
    }

}