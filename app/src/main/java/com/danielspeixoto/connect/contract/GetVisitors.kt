package com.danielspeixoto.connect.contract

import com.danielspeixoto.connect.model.pojo.Visitor

/**
 * Created by danielspeixoto on 4/27/17.
 */
interface GetVisitors {

    interface View : Source.View<Visitor> {
    }

    interface Presenter : Source.Presenter {
    }
}