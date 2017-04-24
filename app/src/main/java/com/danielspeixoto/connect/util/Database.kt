package com.danielspeixoto.connect.util

import com.danielspeixoto.connect.R
import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import org.jetbrains.anko.connectivityManager
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit


/**
 * Created by daniel on 16/04/17.
 */

object Database {

    val END_POINT = "http://192.168.0.22:8080/"
    val retrofit: Retrofit
    val gson = Gson()

    init {
        val client = OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .addInterceptor { chain -> onOnIntercept(chain) }
                .build()

        retrofit = Retrofit.Builder().baseUrl(END_POINT)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build()
    }

    @Throws(IOException::class)
    private fun onOnIntercept(chain: Interceptor.Chain): Response {
        if (!isConnected) {
            throw IOException(App.getStringResource((R.string.no_internet)))
        }
        val builder = chain.request().newBuilder()
        return chain.proceed(builder.build())
    }

    val isConnected : Boolean
        get() {
            val info = App.context!!.connectivityManager.activeNetworkInfo
            return info != null && info.isConnected
        }
}
