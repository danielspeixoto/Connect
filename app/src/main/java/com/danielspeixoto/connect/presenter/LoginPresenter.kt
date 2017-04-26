package com.danielspeixoto.connect.presenter

import com.danielspeixoto.connect.R
import com.danielspeixoto.connect.model.UserModel
import com.danielspeixoto.connect.model.pojo.User
import com.danielspeixoto.connect.module.Login
import com.danielspeixoto.connect.util.App
import com.danielspeixoto.connect.util.Validate
import com.danielspeixoto.connect.view.activity.HomeActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by danielspeixoto on 2/15/17.
 */

class LoginPresenter(private val mView: Login.View) : Login.Presenter {

    override fun logIn(username: String, password: String) {
        App.showMessage(App.getStringResource(R.string.loading))
        val user = User(username, password)
        val result = Validate.User(user)
        if (result == Validate.OK) {
            UserModel.logIn(username, password)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe ({ _ ->
                        mView.goToActivity(HomeActivity::class.java)
                        mView.activity.finish()
                    }, { throwable ->
                        when(throwable.message) {
                            "404" -> App.showMessage(App.getStringResource(R.string.incorrect_username_password))
                            else -> App.showMessage(App.getStringResource(R.string.error_occurred))
                        }
                    })
        } else {
            App.showMessage(result)
        }
    }
}
