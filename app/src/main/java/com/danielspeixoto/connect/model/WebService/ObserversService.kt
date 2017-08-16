package com.danielspeixoto.connect.model.WebService

import com.danielspeixoto.connect.model.UserModel
import com.danielspeixoto.connect.model.pojo.Visitor
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

/**
 * Created by daniel on 22/06/17.
 */
interface ObserversService {

    @POST("observers")
    fun addObserver(@Body activity: HashMap<String, String>,
                    @Header("Authorization") authorization: String = UserModel.currentUser!!.token!!)
            : Call<Visitor>
}