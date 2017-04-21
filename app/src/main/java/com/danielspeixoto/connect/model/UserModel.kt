package com.danielspeixoto.connect.model

import android.content.Context
import com.danielspeixoto.connect.R
import com.danielspeixoto.connect.model.WebService.UsersService
import com.danielspeixoto.connect.model.pojo.User
import com.danielspeixoto.connect.util.App
import com.danielspeixoto.connect.util.Database
import com.danielspeixoto.connect.util.DatabaseContract
import io.reactivex.Single
import lombok.Getter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by danielspeixoto on 4/21/17.
 */
class UserModel : DatabaseContract {
    companion object {

        @Getter
        var currentUser: User? = null
            private set
        private var sUsersService: UsersService = Database.retrofit.create(UsersService::class.java)

        fun logIn(username: String, password: String): Single<User> {
            return Single.create<User> { subscriber ->
                sUsersService.logIn(User(username, password))
                        .enqueue(object : Callback<User> {
                            override fun onResponse(call: Call<User>, response: Response<User>) {
                                currentUser = response.body()
                                saveAccountOnDevice(username, password)
                                subscriber.onSuccess(currentUser)
                            }

                            override fun onFailure(call: Call<User>, throwable: Throwable) {
                                App.showMessage(App.getStringResource(R.string.error_occurred))
                                subscriber.onError(throwable)
                                throwable.printStackTrace()
                            }
                        })
            }
        }

        fun createADM(user: User): Single<User> {
            val passwordWithoutHash = user.password
            return Single.create<User> { subscriber ->
                sUsersService.createADM(user)
                        .enqueue(object : Callback<User> {
                            override fun onResponse(call: Call<User>, response: Response<User>) {
                                currentUser = response.body()
                                saveAccountOnDevice(currentUser!!.username, passwordWithoutHash)
                                subscriber.onSuccess(currentUser)
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
                val preferences = App.context!!.getSharedPreferences(DatabaseContract.LOGIN,
                        Context.MODE_PRIVATE)
                if (preferences.contains(DatabaseContract.USERNAME)) {
                    logIn(preferences.getString(DatabaseContract.USERNAME, ""), preferences.getString(DatabaseContract.PASSWORD, ""))
                }
            }
            return isLogged
        }

        fun logOut() {
            currentUser = null
            App.context!!.getSharedPreferences(DatabaseContract.LOGIN, Context.MODE_PRIVATE).edit().clear().commit()
        }

        private fun saveAccountOnDevice(username: String, password: String) {
            Thread {
                val editor = App.context!!.getSharedPreferences(DatabaseContract.LOGIN, Context
                        .MODE_PRIVATE).edit()
                editor.putString(DatabaseContract.USERNAME, username)
                editor.putString(DatabaseContract.PASSWORD, password)
                editor.apply()
            }.start()
        }

        private val isLogged: Boolean
            get() = currentUser != null
    }
}
