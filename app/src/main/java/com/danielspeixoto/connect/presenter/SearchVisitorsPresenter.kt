package com.danielspeixoto.connect.presenter

import com.danielspeixoto.connect.contract.SearchVisitors
import com.danielspeixoto.connect.model.VisitorModel
import com.danielspeixoto.connect.view.recycler.adapter.MutableAdapter
import com.danielspeixoto.connect.view.recycler.adapter.VisitorAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SearchVisitorsPresenter(private val view: SearchVisitors.View) : SearchVisitors.Presenter {

    override var adapter: VisitorAdapter? = null

    override fun search(query : String) {
        if(adapter != null) {
            adapter!!.clearData()
            adapter!!.status = MutableAdapter.LOADING
            VisitorModel.search(query)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe { list, throwable ->
                        if (throwable != null) {
                            adapter!!.status = MutableAdapter.ERROR
                        } else {
                            list.forEach { adapter!!.addItem(it) }
                            adapter!!.status = MutableAdapter.LOADED
                        }
                    }
        }
    }
}