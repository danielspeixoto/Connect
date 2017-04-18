package com.danielspeixoto.connect.model.pojo;

import com.danielspeixoto.connect.util.Database;
import com.danielspeixoto.connect.util.DatabaseContract;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import lombok.Data;

/**
 * Created by danielspeixoto on 2/14/17.
 */

@Data
public class User implements DatabaseContract {
    private HashMap<String, Boolean> permissions;
	private String name, username, password, group;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
	
	public User(JSONObject object) {
		try {
			this.name = object.getString(NAME);
			this.username = object.getString(USERNAME);
			this.group = object.getString(GROUP);
			this.permissions = Database.getGson().fromJson(object.get(PERMISSIONS).toString(), HashMap.class);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
}
