package com.danielspeixoto.connect.model

import android.content.Context
import com.danielspeixoto.connect.R
import com.danielspeixoto.connect.model.WebService.UsersService
import com.danielspeixoto.connect.model.pojo.User
import com.danielspeixoto.connect.util.App
import com.danielspeixoto.connect.util.Database
import com.danielspeixoto.connect.util.DatabaseContract
import com.danielspeixoto.connect.util.DatabaseContract.Companion.USER
import com.danielspeixoto.connect.util.EMPTY_STRING
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by danielspeixoto on 4/21/17.
 */
class UserModel : DatabaseContract {
    companion object {

        var currentUser: User? = null
            get private set
        private var sUsersService: UsersService = Database.retrofit.create(UsersService::class.java)

        fun logIn(username: String, password: String): Single<User> {
            return Single.create<User> { subscriber ->
                sUsersService.logIn(User(username, password))
                        .enqueue(object : Callback<User> {
                            override fun onResponse(call: Call<User>, response: Response<User>) {
                                if(response.isSuccessful) {
                                    currentUser = response.body()
                                    saveAccountOnDevice()
                                    subscriber.onSuccess(currentUser)
                                }
                            }

                            override fun onFailure(call: Call<User>, throwable: Throwable) {
                                App.showMessage(App.getStringResource(R.string.error_occurred))
                                subscriber.onError(throwable)
                                throwable.printStackTrace()
                            }
                        })
            }
        }

        fun logIn(user: User) = logIn(user.username, user.password)

        fun createADM(user: User): Single<User> {
            return Single.create<User> { subscriber ->
                sUsersService.createADM(user)
                        .enqueue(object : Callback<User> {
                            override fun onResponse(call: Call<User>, response: Response<User>) {
                                if(response.isSuccessful) {
                                    currentUser = response.body()
                                    saveAccountOnDevice()
                                    subscriber.onSuccess(currentUser)
                                }
                            }

                            override fun onFailure(call: Call<User>, throwable: Throwable) {
                                App.showMessage(App.getStringResource(R.string.error_occurred))
                                subscriber.onError(throwable)
                                throwable.printStackTrace()
                            }
                        })
            }
        }

        fun hasAccountSavedOnDevice(): Boolean {
            if (!isLogged) {
                val editor = App.context!!.getSharedPreferences(USER, Context.MODE_PRIVATE)
                if (editor.contains(USER)) {
                    currentUser = Database.gson!!.fromJson(editor.getString(USER, EMPTY_STRING),
                                                           User::class.java)
                    logIn(currentUser!!).subscribeOn(Schedulers.io()).observeOn(Schedulers.io()).subscribe()
                    return true
                }
            }
            return false
        }

        private fun saveAccountOnDevice() {
            Thread {
                val editor = App.context!!.getSharedPreferences(USER, Context.MODE_PRIVATE).edit()
                editor.putString(USER, Database.gson!!.toJson(currentUser))
                editor.apply()
            }.start()
        }

        fun logOut() {
            currentUser = null
            App.context!!.getSharedPreferences(USER, Context.MODE_PRIVATE).edit().clear().apply()
        }

        private val isLogged: Boolean
            get() = currentUser != null
    }
}
