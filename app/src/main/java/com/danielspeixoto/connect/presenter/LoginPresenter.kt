package com.danielspeixoto.connect.presenter

import com.danielspeixoto.connect.R
import com.danielspeixoto.connect.contract.Login
import com.danielspeixoto.connect.model.UserModel
import com.danielspeixoto.connect.model.pojo.User
import com.danielspeixoto.connect.util.App
import com.danielspeixoto.connect.util.Validate
import com.danielspeixoto.connect.view.activity.HomeActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by danielspeixoto on 2/15/17.
 */

class LoginPresenter(private val view: Login.View) : Login.Presenter {

    override fun logIn(username: String,
                       password: String) {

        view.showLoadingDialog()
        val user = User(username,
                        password)
        val result = Validate.user(user)
        if (result == Validate.OK) {
            UserModel.logIn(username,
                            password)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ _ ->
                                   view.goToActivity(HomeActivity::class.java)
                                   view.activity.finish()
                               },
                               { throwable ->
                                   view.closeLoadingDialog()
                                   when (throwable.message) {
                                       "404" -> view.setMessageViewText(App.getStringResource(R.string.incorrect_username_password))
                                       else  -> view.showErrorDialog()
                                   }
                               })
        } else {
            view.setMessageViewText(result)
        }
    }
}
