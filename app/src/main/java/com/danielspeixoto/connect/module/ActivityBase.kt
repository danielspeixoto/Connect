package com.danielspeixoto.connect.module

import com.danielspeixoto.connect.view.activity.BaseActivity

/**
 * Created by danielspeixoto on 1/6/17.
 */

class ActivityBase {

    interface View : Base.View {

        val activity: BaseActivity

        fun goToActivity(clazz: Class<*>)
    }

    interface Presenter : Base.Presenter

}