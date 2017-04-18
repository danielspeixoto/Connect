package com.danielspeixoto.connect.model;


import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.danielspeixoto.connect.R;
import com.danielspeixoto.connect.model.WebService.UserService;
import com.danielspeixoto.connect.model.pojo.User;
import com.danielspeixoto.connect.util.App;
import com.danielspeixoto.connect.util.Database;
import com.danielspeixoto.connect.util.DatabaseContract;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import lombok.Getter;

/**
 * Created by danielspeixoto on 2/14/17.
 */

public class UserModel implements DatabaseContract {
	
	@Getter
	private static User currentUser;
	@Getter
	private static String sToken;
	private static UserService sUserService;
	
	static {
		sUserService = Database.getRetrofit().create(UserService.class);
	}
	
	@NonNull
	public static Single<User> logIn(String username, String password) {
		return Single.create((subscriber) -> {
			sUserService.logIn(username, password)
					.subscribeOn(Schedulers.io())
					.observeOn(Schedulers.io())
					.subscribe(jsonObject -> {
						if (jsonObject.get(SUCCESS).getAsBoolean()) {
							currentUser = Database.getGson().fromJson(jsonObject.get(USER), User.class);
							sToken = jsonObject.get(TOKEN).getAsString();
							saveAccountOnDevice(username, password);
							subscriber.onSuccess(currentUser);
						} else {
							String error = jsonObject.get(MESSAGE).getAsString();
							App.showMessage(error);
							subscriber.onError(new Throwable(error));
						}
					}, throwable -> {
						App.showMessage(App.getStringResource(R.string.error_occurred));
						subscriber.onError(throwable);
						throwable.printStackTrace();
					});
		});
	}
	
	@NonNull
	public static Single<User> createADM(User user) {
		String passwordWithoutHash = user.getPassword();
		return Single.create((subscriber) -> sUserService.createADM(user)
				                                     .subscribeOn(Schedulers.io())
				                                     .observeOn(Schedulers.io())
				                                     .subscribe(jsonObject -> {
					                                     if (jsonObject.get(SUCCESS).getAsBoolean()) {
						                                     User newUser = Database.getGson().fromJson(jsonObject.get(USER), User.class);
						                                     saveAccountOnDevice(newUser.getUsername(), passwordWithoutHash);
						                                     logIn(newUser, jsonObject.get(TOKEN).getAsString());
						                                     subscriber.onSuccess(newUser);
					                                     } else {
						                                     String error = jsonObject.get(MESSAGE).getAsString();
						                                     subscriber.onError(new Throwable(error));
						                                     App.showMessage(error);
					                                     }
				                                     }, throwable -> {
					                                     App.showMessage(App.getStringResource(R.string.error_occurred));
					                                     subscriber.onError(throwable);
					                                     throwable.printStackTrace();
				                                     }));
	}
	
	public static boolean hasAccountSavedOnDevice() {
		if (!isLogged()) {
			SharedPreferences preferences = App.getContext()
					                                .getSharedPreferences(LOGIN, Context.MODE_PRIVATE);
			if (preferences.contains(USERNAME)) {
				logIn(preferences.getString(USERNAME, ""), preferences.getString(PASSWORD, ""));
			}
		}
		return isLogged();
	}
	
	public static void logOut() {
		currentUser = null;
		App.getContext().getSharedPreferences(LOGIN, Context.MODE_PRIVATE).edit().clear().commit();
	}
	
	private static void logIn(User user, String token) {
		currentUser = user;
		sToken = token;
	}
	
	private static void saveAccountOnDevice(String username, String password) {
		new Thread(() -> {
			SharedPreferences.Editor editor = App.getContext()
					                                  .getSharedPreferences(LOGIN, Context.MODE_PRIVATE).edit();
			editor.putString(USERNAME, username);
			editor.putString(PASSWORD, password);
			editor.apply();
		}).start();
	}
	
	private static boolean isLogged() {
		return currentUser != null;
	}
}
