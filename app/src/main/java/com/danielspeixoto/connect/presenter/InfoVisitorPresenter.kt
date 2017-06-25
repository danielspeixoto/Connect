package com.danielspeixoto.connect.presenter

import com.danielspeixoto.connect.contract.InfoVisitor
import com.danielspeixoto.connect.model.UserModel
import com.danielspeixoto.connect.model.VisitorModel
import com.danielspeixoto.connect.model.pojo.Visitor
import com.danielspeixoto.connect.view.recycler.adapter.ActivityAdapter
import com.danielspeixoto.connect.view.recycler.adapter.MutableAdapter
import com.danielspeixoto.connect.view.recycler.adapter.ObserverAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by danielspeixoto on 4/27/17.
 */
class InfoVisitorPresenter(private val view: InfoVisitor.View) : InfoVisitor.Presenter {

    override var visitor: Visitor? = null
    override var activitiesAdapter: ActivityAdapter? = null
    override var observersAdapter: ObserverAdapter? = null

    //TODO Allow refreshing for visitor

    override fun toggleConnected() {
        VisitorModel.toggleConnected(visitor!!._id!!, visitor!!.isConnected).subscribeOn(Schedulers.io()).observeOn(
                AndroidSchedulers.mainThread()).subscribe({ _ ->
                    visitor!!.isConnected = !visitor!!.isConnected
                    view.onVisitorConnected(visitor!!.isConnected)
                },
                { throwable ->
                    throwable.printStackTrace()
                    view.showErrorDialog()
                })
    }

    override fun addActivity(activity: String) {
        activitiesAdapter!!.status = MutableAdapter.LOADING
        VisitorModel.addActivity(visitor!!._id!!, activity)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ _ ->
                    visitor!!.addActivity(activity)
                    activitiesAdapter!!.addItem(activity)
                    activitiesAdapter!!.status = MutableAdapter.LOADED
                    view.onActivityAdded(activity)
                },
                { throwable ->
                    activitiesAdapter!!.status = MutableAdapter.ERROR
                    throwable.printStackTrace()
                })
    }

    override fun observe() {
        observersAdapter!!.status = MutableAdapter.LOADING
        val username = UserModel.currentUser!!.username!!
        VisitorModel.addObserver(visitor!!._id!!)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ _ ->
                    visitor!!.observers.add(username)
                    observersAdapter!!.addItem(UserModel.currentUser!!)
                    observersAdapter!!.status = MutableAdapter.LOADED
                    view.onObserved()
                },
                { throwable ->
                    throwable.printStackTrace()
                    observersAdapter!!.status = MutableAdapter.ERROR
                })
    }

    override fun retrieveObservers() {
        VisitorModel.retrieveObservers(visitor!!._id!!).subscribeOn(Schedulers.io()).observeOn(
                AndroidSchedulers.mainThread()).subscribe({ users ->
                    users.forEach { observersAdapter!!.addItem(it) }
                    observersAdapter!!.status = MutableAdapter.LOADED
                },
                { throwable ->
                    throwable.printStackTrace()
                    observersAdapter!!.status = MutableAdapter.ERROR
                })
    }

    override fun deleteVisitor() {
        VisitorModel.deleteVisitor(visitor!!._id!!)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ _ -> },
                { throwable ->
                    throwable.printStackTrace()
                    view.showErrorDialog()
                })
    }
}