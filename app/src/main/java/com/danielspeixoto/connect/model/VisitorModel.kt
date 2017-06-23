package com.danielspeixoto.connect.model

import com.danielspeixoto.connect.model.pojo.User
import com.danielspeixoto.connect.model.pojo.Visitor
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
            Database.visitorsService.create(UserModel.currentUser!!.token!!, UserModel.currentUser!!.group!!, visitor)
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

    fun getNonConnected() : Single<List<Visitor>> {
        return Single.create<List<Visitor>> { subscriber ->
            Database.visitorsService.getNotConnected(UserModel.currentUser!!.token!!, UserModel.currentUser!!.group!!)
                    .enqueue(object : Callback<List<Visitor>> {
                        override fun onResponse(call: Call<List<Visitor>>, response: Response<List<Visitor>>) {
                            if (response.isSuccessful) {
                                subscriber.onSuccess(response.body())
                            } else {
                                subscriber.onError(Throwable(response.code().string))
                            }
                        }

                        override fun onFailure(call: Call<List<Visitor>>, throwable: Throwable) {
                            throwable.printStackTrace()
                            subscriber.onError(throwable)
                        }
                    })
        }
    }

    fun getConnected() : Single<List<Visitor>> {
        return Single.create<List<Visitor>> { subscriber ->
            Database.visitorsService.getConnected()
                    .enqueue(object : Callback<List<Visitor>> {
                        override fun onResponse(call: Call<List<Visitor>>, response: Response<List<Visitor>>) {
                            if (response.isSuccessful) {
                                subscriber.onSuccess(response.body())
                            } else {
                                subscriber.onError(Throwable(response.code().string))
                            }
                        }

                        override fun onFailure(call: Call<List<Visitor>>, throwable: Throwable) {
                            throwable.printStackTrace()
                            subscriber.onError(throwable)
                        }
                    })
        }
    }

    fun toggleConnected(id : String, isConnected : Boolean) : Single<Visitor> {
        return Single.create<Visitor> { subscriber ->
            Database.visitorsService.toggleConnected(UserModel.currentUser!!.token!!, id, hashMapOf("isConnected" to isConnected))
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

    fun addActivity(id : String, activity: String) : Single<Visitor> {
        return Single.create<Visitor> { subscriber ->
            Database.visitorsService.addActivity(UserModel.currentUser!!.token!!, id, hashMapOf("activity" to activity))
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

    fun retrieveObservers(id : String) : Single<List<User>> {
        return Single.create<List<User>> { subscriber ->
            Database.visitorsService.retrieveObservers(UserModel.currentUser!!.token!!, id)
                    .enqueue(object : Callback<List<User>> {
                        override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                            if (response.isSuccessful) {
                                subscriber.onSuccess(response.body())
                            } else {
                                subscriber.onError(Throwable(response.code().string))
                            }
                        }

                        override fun onFailure(call: Call<List<User>>, throwable: Throwable) {
                            throwable.printStackTrace()
                            subscriber.onError(throwable)
                        }
                    })
        }
    }

    fun  addObserver(_id: String): Single<Visitor> {
        return Single.create<Visitor> { subscriber ->
            Database.observersService.addObserver(hashMapOf("username" to UserModel.currentUser!!.username!!, "visitor" to _id))
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

    fun  deleteVisitor(_id: String): Single<Any> {
        return Single.create<Any> { subscriber ->
            Database.visitorsService.deleteVisitor(_id)
                    .enqueue(object : Callback<Any> {
                        override fun onResponse(call: Call<Any>, response: Response<Any>) {
                            if (response.isSuccessful) {
                                subscriber.onSuccess(response.body())
                            } else {
                                subscriber.onError(Throwable(response.code().string))
                            }
                        }

                        override fun onFailure(call: Call<Any>, throwable: Throwable) {
                            throwable.printStackTrace()
                            subscriber.onError(throwable)
                        }
                    })
        }
    }

}