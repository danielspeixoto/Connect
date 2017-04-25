package com.danielspeixoto.connect.model

import com.danielspeixoto.connect.R
import com.danielspeixoto.connect.model.pojo.Visitor
import com.danielspeixoto.connect.util.App
import com.danielspeixoto.connect.util.Database
import io.reactivex.Single
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by danielspeixoto on 4/25/17.
 */
object VisitorModel {

    fun create(visitor: Visitor): Single<Visitor> {
        return Single.create<Visitor> { subscriber ->
            Database.visitorsService.create(UserModel.currentUser!!.group, visitor)
                    .enqueue(object : Callback<Visitor> {
                        override fun onResponse(call: Call<Visitor>, response: Response<Visitor>) {
                            if (response.isSuccessful) {
                                subscriber.onSuccess(response.body())
                            }
                        }

                        override fun onFailure(call: Call<Visitor>, throwable: Throwable) {
                            App.showMessage(App.getStringResource(R.string.error_occurred))
                            subscriber.onError(throwable)
                            throwable.printStackTrace()
                        }
                    })
        }
    }
}