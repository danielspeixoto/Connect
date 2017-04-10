package com.danielspeixoto.connect.module;

/**
 * Created by danielspeixoto on 2/15/17.
 */

public class Login {

    public interface View extends ActivityBase.View {

    }

    public interface Presenter extends ActivityBase.Presenter {
        void logIn(String email, String password);
    }

}
