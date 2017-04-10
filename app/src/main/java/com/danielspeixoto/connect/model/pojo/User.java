package com.danielspeixoto.connect.model.pojo;

import java.util.HashMap;

import lombok.Data;

/**
 * Created by danielspeixoto on 2/14/17.
 */

@Data
public class User {
    private HashMap<String, Boolean> permissions;
    private String name, username, password, adm;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String name, String username, String password, String adm, HashMap<String, Boolean> permissions) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.adm = adm;
        this.permissions = permissions;

    }

}
