package com.danielspeixoto.connect.contract

/**
 * Created by danielspeixoto on 4/27/17.
 */
interface Home {

    interface View : ActivityBase.View

    interface Presenter : ActivityBase.Presenter {
        fun onMenuItemSelected(id : Int)
    }

}