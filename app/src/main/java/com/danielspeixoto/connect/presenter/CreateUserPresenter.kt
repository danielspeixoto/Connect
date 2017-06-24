package com.danielspeixoto.connect.presenter

import com.danielspeixoto.connect.R
import com.danielspeixoto.connect.contract.CreateUser
import com.danielspeixoto.connect.model.UserModel
import com.danielspeixoto.connect.model.pojo.User
import com.danielspeixoto.connect.util.App
import com.danielspeixoto.connect.util.Validate
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by danielspeixoto on 5/3/17.
 */
class CreateUserPresenter(private val view: CreateUser.View) : CreateUser.Presenter {

    override fun create(user: User) {
        App.showMessage(App.getStringResource(R.string.loading))
        val result = Validate.user(user)
        if (result == Validate.OK) {
            UserModel.createWorker(user)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe ({ user1 ->
                                    view.activity.finish()
                                }, { throwable ->
                                    view.showErrorDialog()
                                })
        } else {
            view.setMessageViewText(result)
        }
    }
}
