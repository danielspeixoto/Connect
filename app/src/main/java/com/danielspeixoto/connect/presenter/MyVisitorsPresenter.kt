package com.danielspeixoto.connect.presenter

import com.danielspeixoto.connect.R
import com.danielspeixoto.connect.contract.MyVisitors
import com.danielspeixoto.connect.model.UserModel
import com.danielspeixoto.connect.util.App
import com.danielspeixoto.connect.view.recycler.adapter.BaseAdapter
import com.danielspeixoto.connect.view.recycler.adapter.VisitorAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MyVisitorsPresenter(private val mView: MyVisitors.View) : MyVisitors.Presenter {

    override var adapter: VisitorAdapter? = null

    override fun getVisitors() {
        if(adapter != null) {
            adapter!!.clearData()
            UserModel.getVisitors()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe { list, throwable ->
                        adapter!!.status = "loaded"
                        if (throwable != null) {
                            when (throwable.message) {
                                else -> App.showMessage(App.getStringResource(R.string.error_occurred))
                            }
                        } else {
                            list.forEach { adapter!!.addItem(it) }
                            adapter!!.status = BaseAdapter.LOADED
                        }
                    }
        }
    }
}