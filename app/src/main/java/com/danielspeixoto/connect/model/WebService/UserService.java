package com.danielspeixoto.connect.model.WebService;

import com.danielspeixoto.connect.model.pojo.User;
import com.google.gson.JsonObject;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by daniel on 16/04/17.
 */

public interface UserService {
	
	@GET("users/get-all")
	Observable<List<User>> getUsers();
	
	@POST("users/register")
	Single<JsonObject> createADM(@Body User user);
	
	@FormUrlEncoded
	@POST("users/authenticate")
	Single<JsonObject> logIn(@Field("username") String username, @Field("password") String password);
	
}
