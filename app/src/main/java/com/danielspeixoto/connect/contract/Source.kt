package com.danielspeixoto.connect.contract

/**
 * Created by danielspeixoto on 4/27/17.
 */
interface Source {

    interface View<T> : Base.View {

        fun addItem(t: T)

    }

    interface Presenter : Base.Presenter {

        fun syncItems()

    }

}