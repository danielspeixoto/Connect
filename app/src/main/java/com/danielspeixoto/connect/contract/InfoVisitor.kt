package com.danielspeixoto.connect.contract

import com.danielspeixoto.connect.model.pojo.Visitor

/**
 * Created by danielspeixoto on 4/27/17.
 */
interface InfoVisitor {

    interface View : ActivityBase.View {
        fun onVisitorConnected(isConnected : Boolean)
    }

    interface Presenter : ActivityBase.Presenter {
        var visitor  : Visitor?
        fun toggleVisitorConnected()
    }

}