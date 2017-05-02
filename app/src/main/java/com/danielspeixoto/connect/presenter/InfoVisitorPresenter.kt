package com.danielspeixoto.connect.presenter

import com.danielspeixoto.connect.R
import com.danielspeixoto.connect.contract.InfoVisitor
import com.danielspeixoto.connect.model.VisitorModel
import com.danielspeixoto.connect.model.pojo.Visitor
import com.danielspeixoto.connect.util.App
import com.danielspeixoto.connect.view.recycler.adapter.ActivityAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by danielspeixoto on 4/27/17.
 */
class InfoVisitorPresenter(private val mView: InfoVisitor.View) : InfoVisitor.Presenter {

    override var visitor: Visitor? = null
    override var activitiesAdapter: ActivityAdapter? = null

    override fun toggleVisitorConnected() {
        VisitorModel.toggleConnected(visitor!!._id!!,
                                     visitor!!.isConnected).subscribeOn(Schedulers.io()).observeOn(
                AndroidSchedulers.mainThread()).subscribe({ visitor1 ->
                                                              visitor!!.isConnected = !visitor!!.isConnected
                                                              mView.onVisitorConnected(visitor!!.isConnected)
                                                          },
                                                          { throwable ->
                                                              throwable.printStackTrace()
                                                              App.showMessage(App.getStringResource(
                                                                      R.string.error_occurred))
                                                          })
    }

    override fun addActivity(activity: String) {
        VisitorModel.addActivity(visitor!!._id!!,
                                 activity).subscribeOn(Schedulers.io()).observeOn(
                AndroidSchedulers.mainThread()).subscribe({ visitor1 ->
                                                              visitor!!.addActivity(activity)
                                                              activitiesAdapter!!.addItem(activity)
                                                          },
                                                          { throwable ->
                                                              throwable.printStackTrace()
                                                              App.showMessage(App.getStringResource(
                                                                      R.string.error_occurred))
                                                          })
    }
}