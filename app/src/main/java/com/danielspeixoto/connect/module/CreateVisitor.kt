package com.danielspeixoto.connect.module

import com.danielspeixoto.connect.model.pojo.Visitor

/**
 * Created by danielspeixoto on 4/25/17.
 */
class CreateVisitor {

    interface View : ActivityBase.View {
        fun refresh()
    }

    interface Presenter : ActivityBase.Presenter {
        fun create(visitor: Visitor)
    }
}