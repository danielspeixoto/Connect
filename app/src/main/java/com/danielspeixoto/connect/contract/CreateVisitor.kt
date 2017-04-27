package com.danielspeixoto.connect.contract

import com.danielspeixoto.connect.model.pojo.Visitor

/**
 * Created by danielspeixoto on 4/25/17.
 */
interface CreateVisitor {

    interface View : ActivityBase.View {
        fun refresh()
    }

    interface Presenter : ActivityBase.Presenter {
        fun create(visitor: Visitor)
    }
}