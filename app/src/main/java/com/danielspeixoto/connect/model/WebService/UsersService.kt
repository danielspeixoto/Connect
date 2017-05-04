package com.danielspeixoto.connect.model.WebService

import com.danielspeixoto.connect.model.pojo.User
import retrofit2.Call
import retrofit2.http.*

/**
 * Created by danielspeixoto on 4/21/17.
 */

interface UsersService {

    @POST("users")
    fun createADM(@Body user: User): Call<User>

    @POST("users/{group}")
    fun createWorker(@Header("Authorization") authorization: String, @Path("group") group: String, @Body user: User): Call<User>

    @PUT("users/login")
    fun logIn(@Body user: User): Call<User>

    @GET("users/{group}")
    fun getCoWorkers(@Header("Authorization") authorization: String, @Path("group") group: String): Call<List<User>>

}