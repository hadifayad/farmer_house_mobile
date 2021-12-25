package com.farmerhouse.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DataMessages {

    @SerializedName("id")
    String id;
    @SerializedName("text")
    String text;
    @SerializedName("data")
    List<Data> data;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }
}
