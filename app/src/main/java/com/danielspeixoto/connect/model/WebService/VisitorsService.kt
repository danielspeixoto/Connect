package com.danielspeixoto.connect.model.WebService

import com.danielspeixoto.connect.model.pojo.Visitor
import retrofit2.Call
import retrofit2.http.*

/**
 * Created by danielspeixoto on 4/25/17.
 */
interface VisitorsService {

    @GET("visitors/{group}")
    fun getAll(@Header("Authorization") authorization: String,
               @Path("group") group: String): Call<List<Visitor>>

    @POST("visitors/{group}")
    fun create(@Header("Authorization") authorization: String,
               @Path("group") group: String,
               @Body visitor: Visitor): Call<Visitor>

    @PUT("visitors/{_id}/isConnected")
    fun toggleConnected(@Header("Authorization") authorization: String,
                        @Path("_id") id: String,
                        @Body isConnected: Boolean): Call<Visitor>
}