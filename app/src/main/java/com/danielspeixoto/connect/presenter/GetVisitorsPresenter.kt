package com.danielspeixoto.connect.presenter

import com.danielspeixoto.connect.R
import com.danielspeixoto.connect.contract.GetVisitors
import com.danielspeixoto.connect.model.VisitorModel
import com.danielspeixoto.connect.util.App
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by danielspeixoto on 4/27/17.
 */
class GetVisitorsPresenter(private val mView: GetVisitors.View) : GetVisitors.Presenter {

    override fun syncItems() {
        VisitorModel.getVisitors()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { list, throwable ->
                    if(throwable != null) {
                        when(throwable.message) {
                            //"404" -> App.showMessage(App.getStringResource(R.string.incorrect_username_password))
                            else -> App.showMessage(App.getStringResource(R.string.error_occurred))
                        }
                    } else {
                        list.forEach { mView.addItem(it) }
                    }
        }
    }
}