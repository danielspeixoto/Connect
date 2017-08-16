package com.danielspeixoto.connect.presenter

import com.danielspeixoto.connect.contract.Main
import com.danielspeixoto.connect.model.UserModel
import com.danielspeixoto.connect.view.activity.HomeActivity

/**
 * Created by danielspeixoto on 4/27/17.
 */
class MainPresenter(private val view: Main.View) : Main.Presenter {

    override fun checkIfUserIsSaved() {
        if (UserModel.hasAccountSavedOnDevice()) {
            view.goToActivity(HomeActivity::class.java)
            view.activity.finish()
        }
    }
}