package com.danielspeixoto.connect.presenter

import com.danielspeixoto.connect.R
import com.danielspeixoto.connect.contract.Home
import com.danielspeixoto.connect.model.UserModel
import com.danielspeixoto.connect.model.VisitorModel
import com.danielspeixoto.connect.util.App
import com.danielspeixoto.connect.view.activity.MainActivity
import com.danielspeixoto.connect.view.recycler.adapter.VisitorAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by danielspeixoto on 4/27/17.
 */
class HomePresenter(private val mView: Home.View) : Home.Presenter {

    override var mAdapter: VisitorAdapter? = null

//    override fun onMenuItemSelected(id: Int) {
//        when (id) {
//            R.id.logout -> {
//                UserModel.logOut()
//                mView.goToActivity(MainActivity::class.java)
//                mView.activity.finish()
//            }
//        }
//    }

    override fun syncItems() {
        if(mAdapter != null) {
            mAdapter!!.clearData()
            VisitorModel.getVisitors()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe { list, throwable ->
                        if (throwable != null) {
                            when (throwable.message) {
                            //"404" -> App.showMessage(App.getStringResource(R.string.incorrect_username_password))
                                else -> App.showMessage(App.getStringResource(R.string.error_occurred))
                            }
                        } else {
                            list.forEach { mAdapter!!.addItem(it) }
                        }
                    }
        }
    }
}