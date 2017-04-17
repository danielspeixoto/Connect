package com.danielspeixoto.connect.model.WebService;

import com.danielspeixoto.connect.model.pojo.User;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by daniel on 16/04/17.
 */

public interface UsersService {
	
	@GET("users")
	Observable<List<User>> getUsers();
	
	@POST("users")
	Observable<User> createUser(@Body User user);
	
}
