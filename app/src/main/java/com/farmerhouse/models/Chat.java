package com.farmerhouse.models;

import com.google.gson.annotations.SerializedName;

public class Chat {
    @SerializedName("id")
    String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @SerializedName("created_at")
    String created_at;

    public void setCreated_at(String date) {
        this.created_at = created_at;
    }

    public String getCreated_at() {
        return created_at;
    }

    @SerializedName("userId")
    String userId;

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    @SerializedName("title")
    String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
