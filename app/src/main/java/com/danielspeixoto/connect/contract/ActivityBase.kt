package com.danielspeixoto.connect.contract

import com.danielspeixoto.connect.view.activity.BaseActivity

/**
 * Created by danielspeixoto on 1/6/17.
 */

interface ActivityBase {

    interface View : Base.View {

        val activity: BaseActivity

        fun goToActivity(clazz: Class<*>)
    }

    interface Presenter : Base.Presenter

}