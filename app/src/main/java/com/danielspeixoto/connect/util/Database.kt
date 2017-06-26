package com.danielspeixoto.connect.util

import com.danielspeixoto.connect.model.WebService.ObserversService
import com.danielspeixoto.connect.model.WebService.UsersService
import com.danielspeixoto.connect.model.WebService.VisitorsService
import com.google.gson.Gson
import okhttp3.OkHttpClient
import org.jetbrains.anko.connectivityManager
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


/**
 * Created by daniel on 16/04/17.
 */

object Database {

    val END_POINT = "https://connect-rest.herokuapp.com/"
//    val END_POINT = "http://192.168.0.22:8080/"

    val gson = Gson()

    val retrofit: Retrofit by lazy {

        val client = OkHttpClient.Builder()
                .connectTimeout(10,
                                TimeUnit.SECONDS)
                .readTimeout(30,
                             TimeUnit.SECONDS)
                .build()

        Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(END_POINT)
                .client(client)
                .build()
    }

    val usersService by lazy {
        Database.retrofit.create(UsersService::class.java)
    }

    val visitorsService by lazy {
        Database.retrofit.create(VisitorsService::class.java)
    }

    val  observersService by lazy {
        Database.retrofit.create(ObserversService::class.java)
    }

    val isConnected: Boolean
        get() {
            val info = App.context.connectivityManager.activeNetworkInfo
            return info != null && info.isConnected
        }

}
