package com.danielspeixoto.connect.util;

import com.danielspeixoto.connect.R;
import com.danielspeixoto.connect.model.pojo.User;

/**
 * Created by danielspeixoto on 2/14/17.
 */

public class Validate {

    public static final String OK = "OK";
    private static String message;

    public static String User(User user) {
        message = OK;
        if (user.getUsername().length() == 0) {
            message = App.getStringResource(R.string.username_must_fill);
        }
        return message;
    }
}
