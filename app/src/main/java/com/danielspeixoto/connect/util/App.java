package com.danielspeixoto.connect.util;

import android.content.Context;
import android.support.multidex.MultiDexApplication;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by danielspeixoto on 2/15/17.
 */

public class App extends MultiDexApplication {

    private static Context mContext;

    public static Context getContext() {
        return mContext;
    }

    public static String getStringResource(int resId) {
        return mContext.getString(resId);
    }
    
    public static float getDimenResource(int resId) {
        return mContext.getResources().getDimension(resId);
    }
 
    public static void showMessage(String message) {
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
    }
	
	public static void log(String s) {
		Log.d("Testing", s);
	}
	
    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }
}
