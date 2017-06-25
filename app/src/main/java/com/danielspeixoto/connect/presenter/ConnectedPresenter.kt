package com.danielspeixoto.connect.presenter

import android.support.v4.widget.SwipeRefreshLayout
import com.danielspeixoto.connect.contract.Connected
import com.danielspeixoto.connect.model.VisitorModel
import com.danielspeixoto.connect.view.recycler.adapter.MutableAdapter
import com.danielspeixoto.connect.view.recycler.adapter.VisitorAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ConnectedPresenter(private val view: Connected.View) : Connected.Presenter {

    override var adapter: VisitorAdapter? = null
    override var refreshLayout: SwipeRefreshLayout? = null

    override fun syncItems() {
        if(adapter != null) {
            adapter!!.clearData()
            VisitorModel.getConnected()
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