package com.danielspeixoto.connect.presenter

import com.danielspeixoto.connect.contract.Users
import com.danielspeixoto.connect.model.UserModel
import com.danielspeixoto.connect.view.recycler.adapter.BaseAdapter
import com.danielspeixoto.connect.view.recycler.adapter.UserAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by danielspeixoto on 5/3/17.
 */
class UsersPresenter(private val view: Users.View) : Users.Presenter {

    override var adapter: UserAdapter? = null

    override fun syncItems() {
        if(adapter != null) {
            adapter!!.clearData()
            UserModel.getUsers()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe { list, throwable ->
                        if (throwable != null) {
                            when (throwable.message) {
                                else -> view.showErrorDialog()
                            }
                        } else {
                            list.forEach { adapter!!.addItem(it) }
                            adapter!!.status = BaseAdapter.LOADED
                        }
                    }
        }
    }
}