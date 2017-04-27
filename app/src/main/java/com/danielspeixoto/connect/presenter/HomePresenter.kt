package com.danielspeixoto.connect.presenter

import com.danielspeixoto.connect.R
import com.danielspeixoto.connect.contract.Home
import com.danielspeixoto.connect.model.UserModel
import com.danielspeixoto.connect.view.activity.MainActivity

/**
 * Created by danielspeixoto on 4/27/17.
 */
class HomePresenter(private val mView: Home.View) : Home.Presenter {

    override fun onMenuItemSelected(id: Int) {
        when (id) {
            R.id.logout -> {
                UserModel.logOut()
                mView.goToActivity(MainActivity::class.java)
                mView.activity.finish()
            }
        }
    }
}