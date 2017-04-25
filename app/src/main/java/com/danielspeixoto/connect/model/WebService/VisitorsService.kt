package com.danielspeixoto.connect.model.WebService

import com.danielspeixoto.connect.model.pojo.Visitor
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

/**
 * Created by danielspeixoto on 4/25/17.
 */
interface VisitorsService {

    @GET("visitors/{group}")
    fun getAll(@Path("group") group : String): Call<List<Visitor>>

    @POST("visitors/{group}")
    fun create(@Path("group") group : String, @Body visitor: Visitor): Call<Visitor>

}