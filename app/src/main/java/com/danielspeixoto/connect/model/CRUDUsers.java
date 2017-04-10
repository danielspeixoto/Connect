package com.danielspeixoto.connect.model;

import android.support.annotation.NonNull;

import com.danielspeixoto.connect.model.pojo.User;

import rx.Observable;
import rx.Single;

/**
 * Created by danielspeixoto on 4/10/17.
 */

public class CRUDUsers extends CRUD {

    @NonNull
    public static Single<User> logIn(String email, String password) {
        return Single.create(subscriber -> {});
    }

    @NonNull
    public static Single<User> createAdm(User user) {
        return Single.create(singleSubscriber -> {});
    }

    @NonNull
    public static Single<User> createUser(User user) {
        String adm = Connection.getCurrentUser().getAdm();
        user.setAdm(adm);
        String email = user.getUsername();
        return Single.create(singleSubscriber -> {});
    }

    @NonNull
    public static Observable<User> getAll() {
        return Observable.create(subscriber -> {});
    }

    @NonNull
    public static Single<User> update(User user) {
        return Single.create(singleSubscriber -> {});
    }
}

