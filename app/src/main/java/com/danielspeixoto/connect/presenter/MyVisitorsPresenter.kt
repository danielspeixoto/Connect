package com.danielspeixoto.connect.presenter

import android.support.v4.widget.SwipeRefreshLayout
import com.danielspeixoto.connect.contract.MyVisitors
import com.danielspeixoto.connect.model.UserModel
import com.danielspeixoto.connect.view.recycler.adapter.MutableAdapter
import com.danielspeixoto.connect.view.recycler.adapter.VisitorAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MyVisitorsPresenter(private val view: MyVisitors.View) : MyVisitors.Presenter {

    override var adapter: VisitorAdapter? = null
    override var refreshLayout: SwipeRefreshLayout? = null

    override fun getVisitors() {
        if(adapter != null) {
            adapter!!.clearData()
            UserModel.getVisitors()
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