package com.danielspeixoto.connect.presenter

import android.support.v4.widget.SwipeRefreshLayout
import com.danielspeixoto.connect.contract.Home
import com.danielspeixoto.connect.model.VisitorModel
import com.danielspeixoto.connect.view.recycler.adapter.BaseAdapter
import com.danielspeixoto.connect.view.recycler.adapter.VisitorAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by danielspeixoto on 4/27/17.
 */
class HomePresenter(private val view: Home.View) : Home.Presenter {

    override var refreshLayout: SwipeRefreshLayout? = null
    override var adapter: VisitorAdapter? = null

    override fun syncItems() {
        if(adapter != null) {
            adapter!!.clearData()
            VisitorModel.getNonConnected()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe { list, throwable ->
                        adapter!!.status = BaseAdapter.LOADED
                        if (throwable != null) {
                            refreshLayout!!.isRefreshing = false
                            when (throwable.message) {
                                else -> view.showErrorDialog()
                            }
                        } else {
                            refreshLayout!!.isRefreshing = false
                            list.forEach { adapter!!.addItem(it) }
                        }
                    }
        }
    }
}