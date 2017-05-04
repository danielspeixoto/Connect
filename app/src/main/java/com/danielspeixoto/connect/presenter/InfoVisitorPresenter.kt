package com.danielspeixoto.connect.presenter

import com.danielspeixoto.connect.R
import com.danielspeixoto.connect.contract.InfoVisitor
import com.danielspeixoto.connect.model.UserModel
import com.danielspeixoto.connect.model.VisitorModel
import com.danielspeixoto.connect.model.pojo.Visitor
import com.danielspeixoto.connect.util.App
import com.danielspeixoto.connect.view.recycler.adapter.ActivityAdapter
import com.danielspeixoto.connect.view.recycler.adapter.UserAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by danielspeixoto on 4/27/17.
 */
class InfoVisitorPresenter(private val mView: InfoVisitor.View) : InfoVisitor.Presenter {

    override var visitor: Visitor? = null
    override var activitiesAdapter: ActivityAdapter? = null
    override var observersAdapter: UserAdapter? = null

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

    override fun observe() {
        val username = UserModel.currentUser!!.username!!
        VisitorModel.addActivity(visitor!!._id!!,
                                 username).subscribeOn(Schedulers.io()).observeOn(
                AndroidSchedulers.mainThread()).subscribe({ visitor1 ->
                                                              visitor!!.observers.add(username)
                                                              observersAdapter!!.addItem(UserModel.currentUser!!)
                                                              mView.onObserved()
                                                          },
                                                          { throwable ->
                                                              throwable.printStackTrace()
                                                              App.showMessage(App.getStringResource(
                                                                      R.string.error_occurred))
                                                          })
    }

    override fun retrieveObservers() {
        VisitorModel.retrieveObservers(visitor!!._id!!).subscribeOn(Schedulers.io()).observeOn(
                AndroidSchedulers.mainThread()).subscribe({ users ->
                                                              users.forEach { observersAdapter!!.addItem(it) }
                                                          },
                                                          { throwable ->
                                                              throwable.printStackTrace()
                                                              App.showMessage(App.getStringResource(
                                                                      R.string.error_occurred))
                                                          })
    }
}