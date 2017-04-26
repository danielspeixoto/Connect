package com.danielspeixoto.connect.model.WebService

import com.danielspeixoto.connect.model.pojo.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT

/**
 * Created by danielspeixoto on 4/21/17.
 */

interface UsersService {

    @GET("users/")
    fun getAll(): Call<List<User>>

    @POST("users")
    fun createADM(@Body user: User): Call<User>

    @PUT("users/login")
    fun logIn(@Body user: User): Call<User>

}