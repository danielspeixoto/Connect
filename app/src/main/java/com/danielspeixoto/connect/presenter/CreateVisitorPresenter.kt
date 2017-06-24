package com.danielspeixoto.connect.presenter

import com.danielspeixoto.connect.R
import com.danielspeixoto.connect.contract.CreateVisitor
import com.danielspeixoto.connect.model.VisitorModel
import com.danielspeixoto.connect.model.pojo.Visitor
import com.danielspeixoto.connect.util.App
import com.danielspeixoto.connect.util.Validate
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by danielspeixoto on 4/25/17.
 */
class CreateVisitorPresenter(private val view: CreateVisitor.View) : CreateVisitor.Presenter {

    override fun create(visitor: Visitor) {
        val result = Validate.visitor(visitor)
        if (result == Validate.OK) {
            view.showLoadingDialog()
            VisitorModel.create(visitor)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe ({ visitor1 ->
                        view.closeLoadingDialog()
                        view.showSavedDialog(App.getStringResource(R.string.visitor_added))
                        view.refresh()
                    }, { throwable ->
                        view.closeLoadingDialog()
                        view.showErrorDialog()
                    })
        } else {
            view.setMessageViewText(result)
        }
    }
}
