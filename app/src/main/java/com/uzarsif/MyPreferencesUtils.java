package com.uzarsif;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class MyPreferencesUtils {


    public static String getUserToken(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        String userToken = prefs.getString("token", "");
        return userToken;
    }
}
