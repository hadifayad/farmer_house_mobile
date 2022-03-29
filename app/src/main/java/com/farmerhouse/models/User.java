package com.farmerhouse.models;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Button;

import com.google.gson.annotations.SerializedName;

public class User {

    static final String KEY_USERNAME = "username";
    static final String KEY_PASSWORD = "password";
    static final String KEY_FULLNAME = "fullname";

    @SerializedName("id")
    String id;
    @SerializedName("fullname")
    String fullname;
    @SerializedName("username")
    String username;
    @SerializedName("password")
    String password;

    @SerializedName("village")
    String village;


    @SerializedName("address")
    String address;


    @SerializedName("phone")
    String phone;



    @SerializedName("token")
    String token;

    @SerializedName("user_role")
    String user_role;

    @SerializedName("mandoobId")
    String mandoobId;



    @SerializedName("profile_picture")
    String profile_picture;

    public String getMandoobId() {
        return mandoobId;
    }

    public String getUser_role() {
        return user_role;
    }

    public void setUser_role(String user_role) {
        this.user_role = user_role;
    }

    public void setMandoobId(String mandoobId) {
        this.mandoobId = mandoobId;
    }



    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getProfile_picture() {
        return profile_picture;
    }

    public void setProfile_picture(String profile_picture) {
        this.profile_picture = profile_picture;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public static boolean isLogged(Context context) {

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        String username = prefs.getString(KEY_USERNAME, "");

        if (username != "" && username != null) {
            return true;
        } else return false;
    }

    public static boolean isLoggedAdmin(Context context) {

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        String username = prefs.getString(KEY_USERNAME, "");
        String role = prefs.getString("role", "");
        Log.d("streamer details", username + " " + role);
        if (username != "" && username != null && role.compareTo("1") == 1) {
            return true;
        } else return false;
    }

    public static String getID(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        String ID = prefs.getString("userId", "");

        return ID;
    }



    public static String getFullname(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        String fullname = prefs.getString("fullname", "");

        return fullname;
    }

    public static void removeAdsPref(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor ed = prefs.edit();
        ed.putString("removeAds", "1");
        ed.commit();
    }



    public static Boolean isAdsRemoved(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        String isRemovedAds = prefs.getString("removeAds", "0");
        if (isRemovedAds.equals("1")) {
            return true;
        } else {
            return false;
        }
    }



    public static void clearPref(Context context) {
        Button login;
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor ed = prefs.edit();
        ed.putString(KEY_USERNAME, "");
        ed.putString(KEY_PASSWORD, "");

        ed.putString(KEY_FULLNAME, "");
        ed.putString("id", "");

        ed.commit();
        //MainActivity.login.setVisibility(View.VISIBLE);
    }

}
