package com.danielspeixoto.connect.contract

import android.support.v7.widget.RecyclerView
import com.danielspeixoto.connect.model.pojo.Visitor
import com.danielspeixoto.connect.view.recycler.adapter.ActivityAdapter

/**
 * Created by danielspeixoto on 4/27/17.
 */
interface InfoVisitor {

    interface View : ActivityBase.View {
        fun onVisitorConnected(isConnected : Boolean)
        fun onActivityAdded(activity: String) {}
    }

    interface Presenter : ActivityBase.Presenter {
        var visitor  : Visitor?
        fun toggleVisitorConnected()
        fun addActivity(activity : String)
        var activitiesAdapter: ActivityAdapter?
    }

}