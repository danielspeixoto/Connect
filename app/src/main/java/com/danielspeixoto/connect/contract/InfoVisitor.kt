package com.danielspeixoto.connect.contract

import com.danielspeixoto.connect.model.pojo.Visitor
import com.danielspeixoto.connect.view.recycler.adapter.ActivityAdapter
import com.danielspeixoto.connect.view.recycler.adapter.ObserverAdapter

/**
 * Created by danielspeixoto on 4/27/17.
 */
interface InfoVisitor {

    interface View : ActivityBase.View {
        fun onVisitorConnected(isConnected : Boolean)
        fun onActivityAdded(activity: String)
        fun onObserved()
    }

    interface Presenter : ActivityBase.Presenter {
        var activitiesAdapter: ActivityAdapter?
        var observersAdapter: ObserverAdapter?
        var visitor  : Visitor?
        fun toggleConnected()
        fun addActivity(activity : String)
        fun observe()
        fun retrieveObservers()
    }

}