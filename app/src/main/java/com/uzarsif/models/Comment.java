package com.uzarsif.models;

import com.google.gson.annotations.SerializedName;

public class Comment {

    @SerializedName("id")
    String id;
    @SerializedName("text")
    String text;
    @SerializedName("chatId")
    String chatId;
    @SerializedName("userId")
    String userId;
    @SerializedName("creation_date")
    String creation_date;
    @SerializedName("type")
    String type;
    @SerializedName("image")
    String image;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getUserId() {
        return userId;
    }

    public String getText() {
        return text;
    }

    public String getChatId() {
        return chatId;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public String getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(String creation_date) {
        this.creation_date = creation_date;
    }

    public String getImage() {
        return image;
    }

    public String getType() {
        return type;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setType(String type) {
        this.type = type;
    }
}

