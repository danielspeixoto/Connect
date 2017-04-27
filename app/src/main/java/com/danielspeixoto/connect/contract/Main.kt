package com.danielspeixoto.connect.contract

/**
 * Created by danielspeixoto on 4/27/17.
 */
interface Main {

    interface View : ActivityBase.View

    interface Presenter : ActivityBase.Presenter {
        fun checkIfUserIsSaved()
    }

}