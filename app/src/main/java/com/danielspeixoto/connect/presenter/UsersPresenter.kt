package com.danielspeixoto.connect.presenter

import android.support.v4.widget.SwipeRefreshLayout
import com.danielspeixoto.connect.contract.Users
import com.danielspeixoto.connect.model.UserModel
import com.danielspeixoto.connect.view.recycler.adapter.MutableAdapter
import com.danielspeixoto.connect.view.recycler.adapter.UserAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by danielspeixoto on 5/3/17.
 */
class UsersPresenter(private val view: Users.View) : Users.Presenter {

    override var adapter: UserAdapter? = null
    override var refreshLayout: SwipeRefreshLayout? = null

    override fun syncItems() {
        if(adapter != null) {
            adapter!!.clearData()
            UserModel.getUsers()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe { list, throwable ->
                        if (throwable != null) {
                            adapter!!.status = MutableAdapter.ERROR
                        } else {
                            list.forEach { adapter!!.addItem(it) }
                            adapter!!.status = MutableAdapter.LOADED
                        }
                        refreshLayout!!.isRefreshing = false
                    }
        }
    }
}