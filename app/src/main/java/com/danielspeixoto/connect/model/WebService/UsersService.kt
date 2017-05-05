package com.danielspeixoto.connect.model.WebService

import com.danielspeixoto.connect.model.UserModel
import com.danielspeixoto.connect.model.pojo.User
import com.danielspeixoto.connect.model.pojo.Visitor
import retrofit2.Call
import retrofit2.http.*

/**
 * Created by danielspeixoto on 4/21/17.
 */

interface UsersService {

    @POST("users")
    fun createADM(@Body user: User): Call<User>

    @POST("users/{group}")
    fun createWorker(@Header("Authorization") authorization: String,
                     @Path("group") group: String,
                     @Body user: User): Call<User>

    @PUT("users/login")
    fun logIn(@Body user: User): Call<User>

    @GET("users/{group}")
    fun getCoWorkers(@Header("Authorization") authorization: String,
                     @Path("group") group: String): Call<List<User>>

    @GET("users/{id}/visitors")
    fun getVisitors(@Header("Authorization") authorization: String = UserModel.currentUser!!.token!!,
                    @Path("id") username: String = UserModel.currentUser!!.username!!) : Call<List<Visitor>>
}