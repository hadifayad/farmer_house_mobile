package com.uzarsif.models;

import com.google.gson.annotations.SerializedName;

public class Message {
    @SerializedName("id")
    String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @SerializedName("date")
    String date;

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    @SerializedName("chatId")
    String chatId;

    public void setChatId(String userId) {
        this.chatId = chatId;
    }

    public String getChatId() {
        return chatId;
    }

    @SerializedName("dataId")
    String dataId;

    public String getDataId() {
        return dataId;
    }

    public void setDataId(String dataId) {
        this.dataId = dataId;
    }
}
