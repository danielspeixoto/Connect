package com.danielspeixoto.connect.presenter

import com.danielspeixoto.connect.R
import com.danielspeixoto.connect.model.VisitorModel
import com.danielspeixoto.connect.model.pojo.Visitor
import com.danielspeixoto.connect.module.CreateVisitor
import com.danielspeixoto.connect.util.App
import com.danielspeixoto.connect.util.Validate
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by danielspeixoto on 4/25/17.
 */
class CreateVisitorPresenter(private val mView: CreateVisitor.View) : CreateVisitor.Presenter {

    override fun create(visitor: Visitor) {
        App.showMessage(App.getStringResource(R.string.loading))
        val result = Validate.visitor(visitor)
        if (result == Validate.OK) {
            VisitorModel.create(visitor)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe { visitor1 ->
                        //TODO Use a info dialog instead
                        App.showMessage(App.getStringResource(R.string.visitor_added))
                        mView.refresh()
                    }
        } else {
            App.showMessage(result)
        }
    }
}
