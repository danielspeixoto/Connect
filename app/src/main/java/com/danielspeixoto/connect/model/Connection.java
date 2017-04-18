package com.danielspeixoto.connect.model;


import android.content.Context;
import android.content.SharedPreferences;

import com.danielspeixoto.connect.model.pojo.User;
import com.danielspeixoto.connect.util.App;
import com.danielspeixoto.connect.util.DatabaseContract;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by danielspeixoto on 2/14/17.
 */

public class Connection implements DatabaseContract {

    @Getter
    private static User currentUser;
	@Getter
	@Setter
	private static String sToken;
	
	public static void logIn(String username, String password) {
	    /*Database.getRetrofit().create(UsersService.class).logIn(username, password).subscribe((user, throwable) -> {
			if(throwable == null) {
				currentUser = user;
				saveAccountOnDevice(username, password);
			} else {
				throwable.printStackTrace();
				App.showMessage(App.getStringResource(R.string.error_occurred));
			}
        });*/
	}
	
	public static void logIn(User user, String token) {
		currentUser = user;
		sToken = token;
	}
	
	public static void saveAccountOnDevice(String username, String password) {
		new Thread(() -> {
            SharedPreferences.Editor editor = App.getContext()
		            .getSharedPreferences(LOGIN, Context.MODE_PRIVATE).edit();
			editor.putString(USERNAME, username);
	        editor.putString(PASSWORD, password);
            editor.apply();
        }).start();
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
	
	
	private static boolean isLogged() {
		return currentUser != null;
    }
}
