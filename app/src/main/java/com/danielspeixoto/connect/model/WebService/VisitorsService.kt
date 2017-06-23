package com.danielspeixoto.connect.model.WebService

import com.danielspeixoto.connect.model.UserModel
import com.danielspeixoto.connect.model.pojo.User
import com.danielspeixoto.connect.model.pojo.Visitor
import retrofit2.Call
import retrofit2.http.*

/**
 * Created by danielspeixoto on 4/25/17.
 */
interface VisitorsService {

    @GET("visitors?isConnected=false")
    fun getNotConnected(@Header("Authorization") authorization: String,
                        @Query("group") group: String): Call<List<Visitor>>

    @GET("visitors?isConnected=true")
    fun getConnected(@Header("Authorization") authorization: String = UserModel.currentUser!!.token!!,
                     @Query("group") group: String = UserModel.currentUser!!.group!!): Call<List<Visitor>>

    @POST("visitors")
    fun create(@Header("Authorization") authorization: String,
               @Query("group") group: String,
               @Body visitor: Visitor): Call<Visitor>

    @GET("visitors/{id}/observers")
    fun retrieveObservers(@Header("Authorization") authorization: String,
                          @Path("id") id: String): Call<List<User>>

    @FormUrlEncoded
    @PUT("visitors/{id}/isConnected/")
    fun deleteVisitor(@Header("Authorization") authorization: String,
                      @Path("id") id: String,
                      @Field("isConnected") isConnected: HashMap<String, Boolean>): Call<Visitor>

    @PUT("visitors/{id}/activities")
    fun addActivity(@Header("Authorization") authorization: String,
                    @Path("id") id: String,
                    @Body activity: HashMap<String, String>): Call<Visitor>

}