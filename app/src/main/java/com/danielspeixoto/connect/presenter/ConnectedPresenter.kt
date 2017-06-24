package com.danielspeixoto.connect.presenter

import com.danielspeixoto.connect.contract.Connected
import com.danielspeixoto.connect.model.VisitorModel
import com.danielspeixoto.connect.view.recycler.adapter.BaseAdapter
import com.danielspeixoto.connect.view.recycler.adapter.VisitorAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ConnectedPresenter(private val view: Connected.View) : Connected.Presenter {

    override var adapter: VisitorAdapter? = null

    override fun syncItems() {
        if(adapter != null) {
            adapter!!.clearData()
            VisitorModel.getConnected()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe { list, throwable ->
                        adapter!!.status = BaseAdapter.LOADED
                        if (throwable != null) {
                            when (throwable.message) {
                                else -> view.showErrorDialog()
                            }
                        } else {
                            list.forEach { adapter!!.addItem(it) }
                        }
                    }
        }
    }
}