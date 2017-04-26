package com.danielspeixoto.connect.model

import com.danielspeixoto.connect.model.UserModel.currentUser
import com.danielspeixoto.connect.model.pojo.Visitor
import com.danielspeixoto.connect.util.App
import com.danielspeixoto.connect.util.Database
import com.danielspeixoto.connect.util.string
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
            App.log(currentUser.toString())
            Database.visitorsService.create(UserModel.currentUser!!.group!!, visitor)
                    .enqueue(object : Callback<Visitor> {
                        override fun onResponse(call: Call<Visitor>, response: Response<Visitor>) {
                            if (response.isSuccessful) {
                                subscriber.onSuccess(response.body())
                            } else {
                                subscriber.onError(Throwable(response.code().string))
                            }
                        }

                        override fun onFailure(call: Call<Visitor>, throwable: Throwable) {
                            throwable.printStackTrace()
                            subscriber.onError(throwable)
                        }
                    })
        }
    }
}