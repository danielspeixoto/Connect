package com.danielspeixoto.connect.presenter

import com.danielspeixoto.connect.R
import com.danielspeixoto.connect.contract.InfoVisitor
import com.danielspeixoto.connect.model.VisitorModel
import com.danielspeixoto.connect.model.pojo.Visitor
import com.danielspeixoto.connect.util.App
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by danielspeixoto on 4/27/17.
 */
class InfoVisitorPresenter(private val mView: InfoVisitor.View) : InfoVisitor.Presenter {

    override var visitor: Visitor? = null

    override fun toggleVisitorConnected() {
        VisitorModel.toggleConnected(visitor!!._id!!).subscribeOn(Schedulers.io()).observeOn(
                AndroidSchedulers.mainThread()).subscribe({ visitor1 ->
                                                              mView.onVisitorConnected(visitor1.isConnected)
                                                          },
                                                          { throwable ->
                                                              App.showMessage(App.getStringResource(
                                                                      R.string.error_occurred))
                                                          })
    }
}