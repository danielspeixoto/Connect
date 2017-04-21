package com.danielspeixoto.connect.util

import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by daniel on 16/04/17.
 */

object Database {
    val END_POINT = "http://192.168.0.22:8080/"
    val retrofit: Retrofit = Retrofit.Builder().baseUrl(END_POINT)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    val gson: Gson? = Gson()
}
