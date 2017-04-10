package com.danielspeixoto.connect.model;


import android.content.Context;
import android.content.SharedPreferences;

import com.danielspeixoto.connect.model.pojo.User;
import com.danielspeixoto.connect.util.App;
import com.danielspeixoto.connect.util.DatabaseContract;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import lombok.Getter;
import rx.Single;
import rx.SingleSubscriber;

/**
 * Created by danielspeixoto on 2/14/17.
 */

public class Connection implements DatabaseContract {

    @Getter
    private static User currentUser;

    public static Single<User> findUser(String email, String password) {
        return CRUDUsers.logIn(email, password);
    }

    public static void logIn(User user) {
        new Thread(() -> {
            // Salva os dados do usuario novamente no celular
            SharedPreferences.Editor editor = App.getContext()
		            .getSharedPreferences(LOGIN, Context.MODE_PRIVATE).edit();
            editor.putString(EMAIL, user.getUsername());
            editor.putString(PASSWORD, user.getPassword());
            editor.putString(ADM, user.getAdm());
            editor.putString(NAME, user.getName());
            editor.apply();
            try {
                File file = new File(App.getContext()
		                .getDir(DATA, App.MODE_PRIVATE), PERMISSIONS);
                ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file));
                outputStream.writeObject(user.getPermissions());
                outputStream.flush();
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).run();
    }

    public static boolean hasAccountSavedOnDevice() {
        if (!isLogged()) {
            SharedPreferences preferences = App.getContext()
		            .getSharedPreferences(LOGIN, Context.MODE_PRIVATE);
            if (preferences.contains(EMAIL)) {
                try {
                    File file = new File(App.getContext()
		                    .getDir(DATA, App.MODE_PRIVATE), PERMISSIONS);
                    ObjectInputStream outputStream = new ObjectInputStream(new FileInputStream(file));
                    currentUser = new User(preferences.getString(NAME, ""),
		                    preferences.getString(EMAIL, ""),
		                    preferences.getString(PASSWORD, ""),
		                    preferences.getString(ADM, ""),
		                    (HashMap<String, Boolean>) outputStream.readObject());
                    updateUser();
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        return isLogged();
    }

    public static boolean isLogged() {
        return currentUser != null;
    }

    public static void logOff() {
        currentUser = null;
        App.getContext().getSharedPreferences(LOGIN, Context.MODE_PRIVATE).edit().clear().commit();
    }

    private static void updateUser() {
        // Sincroniza dados locais com os remotos
	    CRUDUsers.update(currentUser).subscribe(new SingleSubscriber<User>() {
		    @Override
		    public void onSuccess(User value) {
				Connection.logIn(value);
		    }
		
		    @Override
		    public void onError(Throwable error) {
			    logOff();
			    App.showMessage(error.getMessage());
		    }
	    });
    }
}