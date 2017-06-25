package com.danielspeixoto.connect.contract

import com.danielspeixoto.connect.view.activity.BaseActivity

/**
 * Created by danielspeixoto on 1/6/17.
 */

interface ActivityBase {

    interface View : Base.View {

        val activity: BaseActivity

        fun goToActivity(clazz: Class<*>)

        fun showSavedDialog(message : String)

        fun showLoadingDialog()

        fun closeLoadingDialog()

        fun showErrorDialog()

        fun goToActivityClearPrevious(clazz: Class<*>)
    }

    interface Presenter : Base.Presenter

}