package com.danielspeixoto.connect.presenter

import com.danielspeixoto.connect.contract.SignUp
import com.danielspeixoto.connect.model.UserModel
import com.danielspeixoto.connect.model.pojo.User
import com.danielspeixoto.connect.util.Validate
import com.danielspeixoto.connect.view.activity.HomeActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by danielspeixoto on 2/14/17.
 */

class SignUpPresenter(private val view: SignUp.View) : SignUp.Presenter {

    override fun signUp(user: User) {
        view.showLoadingDialog()
        val result = Validate.user(user)
        if (result == Validate.OK) {
            UserModel.createADM(user)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe ({ user1 ->
                        view.closeLoadingDialog()
                        view.goToActivityClearPrevious(HomeActivity::class.java)
                    }, { _ ->
                        view.closeLoadingDialog()
                        view.showErrorDialog()
                    })
        } else {
            view.setMessageViewText(result)
        }
    }
}
