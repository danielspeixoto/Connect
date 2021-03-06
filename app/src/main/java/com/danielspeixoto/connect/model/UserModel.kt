package com.danielspeixoto.connect.model

import android.content.Context
import com.danielspeixoto.connect.model.pojo.User
import com.danielspeixoto.connect.model.pojo.Visitor
import com.danielspeixoto.connect.util.*
import com.danielspeixoto.connect.util.DatabaseContract.Companion.USER
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * Created by danielspeixoto on 4/21/17.
 */
object UserModel : DatabaseContract {

    var currentUser: User? = null
        get private set

    fun logIn(username: String, password: String): Single<User> {
        return Single.create<User> { subscriber ->
            Database.usersService.logIn(User(username, password))
                    .enqueue(object : Callback<User> {
                        override fun onResponse(call: Call<User>, response: Response<User>) {
                            if (response.isSuccessful) {
                                currentUser = response.body()
                                saveAccountOnDevice()
                                subscriber.onSuccess(currentUser)
                            } else {
                                subscriber.onError(Throwable(response.code().string))
                            }
                        }

                        override fun onFailure(call: Call<User>, throwable: Throwable) {
                            subscriber.onError(throwable)
                            throwable.printStackTrace()
                        }
                    })
        }
    }

    fun logIn(user: User) = logIn(user.username!!, user.password!!)

    fun createADM(user: User): Single<User> {
        return Single.create<User> { subscriber ->
            Database.usersService.createADM(user)
                    .enqueue(object : Callback<User> {
                        override fun onResponse(call: Call<User>, response: Response<User>) {
                            if (response.isSuccessful) {
                                currentUser = response.body()
                                saveAccountOnDevice()
                                subscriber.onSuccess(currentUser)
                            } else {
                                subscriber.onError(Throwable(response.code().string))
                            }
                        }

                        override fun onFailure(call: Call<User>, throwable: Throwable) {
                            subscriber.onError(throwable)
                            throwable.printStackTrace()
                        }
                    })
        }
    }

    fun createWorker(user: User): Single<User> {
        return Single.create<User> { subscriber ->
            Database.usersService.createWorker(currentUser!!.token!!, currentUser!!.group!!, user)
                    .enqueue(object : Callback<User> {
                        override fun onResponse(call: Call<User>, response: Response<User>) {
                            if (response.isSuccessful) {
                                subscriber.onSuccess(currentUser)
                            } else {
                                subscriber.onError(Throwable(response.code().string))
                            }
                        }

                        override fun onFailure(call: Call<User>, throwable: Throwable) {
                            subscriber.onError(throwable)
                            throwable.printStackTrace()
                        }
                    })
        }
    }

    fun getUsers() : Single<List<User>> {
        return Single.create<List<User>> { subscriber ->
            Database.usersService.getCoWorkers(UserModel.currentUser!!.token!!, UserModel.currentUser!!.group!!)
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

    fun getVisitors() : Single<List<Visitor>> {
        return Single.create<List<Visitor>> { subscriber ->
            Database.usersService.getVisitors()
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

    fun hasAccountSavedOnDevice(): Boolean {
        if (!isLogged) {
            val editor = App.context.getSharedPreferences(USER, Context.MODE_PRIVATE)
            val userGson = editor.getString(USER, EMPTY_STRING)
            if (userGson !== EMPTY_STRING) {
                currentUser = Database.gson.fromJson(userGson,
                                                     User::class.java)
                logIn(currentUser!!).subscribeOn(Schedulers.io()).observeOn(Schedulers.io()).subscribe(
                        { user -> },
                        { throwable ->
                            throwable.printStackTrace()
                        })
            }
        }
        return isLogged
    }

    private fun saveAccountOnDevice() {
        Thread {
            val editor = App.context.getSharedPreferences(USER, Context.MODE_PRIVATE).edit()
            editor.putString(USER, Database.gson.toJson(currentUser))
            editor.apply()
        }.start()
    }

    fun logOut() {
        currentUser = null
        App.context.getSharedPreferences(USER, Context.MODE_PRIVATE).edit().clear().apply()
    }

    val isLogged: Boolean
        get() = currentUser != null
}
