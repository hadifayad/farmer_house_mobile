package com.uzarsif.models;

import com.google.gson.annotations.SerializedName;

public class AdminViewMessage {

    @SerializedName("id")
    String id;
    @SerializedName("user")
    String user;
    @SerializedName("mandoub")
    String mandoub;

    @SerializedName("title")
    String title;

    @SerializedName("creation_date")
    String creation_date;

    @SerializedName("fullname")
    String fullname;

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getId() {
        return id;
    }

    public String getUser() {
        return user;
    }

    public String getMandoub() {
        return mandoub;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setMandoob(String mandoob) {
        this.mandoub = mandoub;
    }

    public String getTitle() {
        return title;
    }

    public String getCreation_date() {
        return creation_date;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCreation_date(String creation_date) {
        this.creation_date = creation_date;
    }
}
