package com.farmerhouse.models;

import com.google.gson.annotations.SerializedName;

public class New {
    @SerializedName("id")
    String id;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @SerializedName("userId")
    String userId;

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    @SerializedName("files")
    String files;

    public String getFiles() {
        return files;
    }

    public void setFiles(String files) {
        this.files = files;
    }

    @SerializedName("text")
    String text;

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }


    @SerializedName("date")
    String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @SerializedName("profile_picture")
    String profile_picture;


    public String getProfile_picture() {
        return profile_picture;
    }

    public void setProfile_picture(String profile_picture) {
        this.profile_picture = profile_picture;
    }

    @SerializedName("fullname")
    String fullname;

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
}
