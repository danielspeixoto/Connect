package com.danielspeixoto.connect.util;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import lombok.Getter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by daniel on 16/04/17.
 */

public class Database {
	
	public static final String END_POINT = "http://192.168.0.22:4000/api/";
	@Getter
	private static Retrofit mRetrofit;
	
	static {
		mRetrofit = new Retrofit.Builder().baseUrl(END_POINT)
				.addConverterFactory(GsonConverterFactory.create())
				.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
				.build();
	}
	
}
