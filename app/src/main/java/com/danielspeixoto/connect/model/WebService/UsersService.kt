package com.danielspeixoto.connect.model.WebService

import com.danielspeixoto.connect.model.pojo.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * Created by danielspeixoto on 4/21/17.
 */

interface UsersService {

    @GET("users/get-all")
    fun getUsers(): Call<List<User>>

    @POST("users/register")
    fun createADM(@Body user: User): Call<User>

    @POST("users/authenticate")
    fun logIn(@Body user: User): Call<User>

}