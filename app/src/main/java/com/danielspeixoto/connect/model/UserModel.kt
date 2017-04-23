package com.danielspeixoto.connect.model

import android.content.Context
import com.danielspeixoto.connect.R
import com.danielspeixoto.connect.model.WebService.UsersService
import com.danielspeixoto.connect.model.pojo.User
import com.danielspeixoto.connect.util.App
import com.danielspeixoto.connect.util.Database
import com.danielspeixoto.connect.util.DatabaseContract
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import lombok.Getter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

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
                                saveAccountOnDevice()
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

        fun logIn(user: User) = logIn(user.username, user.password)

        fun createADM(user: User): Single<User> {
            return Single.create<User> { subscriber ->
                sUsersService.createADM(user)
                        .enqueue(object : Callback<User> {
                            override fun onResponse(call: Call<User>, response: Response<User>) {
                                currentUser = response.body()
                                saveAccountOnDevice()
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
                val file = File("user")
                if (file.isFile) {
                    currentUser = ObjectInputStream(App.context!!.openFileInput("user")).readObject() as User
                    logIn(currentUser!!).subscribeOn(Schedulers.io()).observeOn(Schedulers.io()).subscribe()
                    return true
                }
            }
            return false
        }

        fun logOut() {
            currentUser = null
            App.context!!.getSharedPreferences(DatabaseContract.LOGIN,
                                               Context.MODE_PRIVATE).edit().clear().commit()
        }

        private fun saveAccountOnDevice() {
            Thread {
                val objectOS = ObjectOutputStream(App.context!!.openFileOutput("user",
                                                                               Context.MODE_PRIVATE))
                objectOS.writeObject(currentUser)
                objectOS.close()
            }.start()
        }

        private val isLogged: Boolean
            get() = currentUser != null
    }
}
