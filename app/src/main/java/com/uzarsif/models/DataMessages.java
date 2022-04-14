package com.uzarsif.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DataMessages {

    boolean isLastChild;

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

    @SerializedName("image")
    String image;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @SerializedName("description")
    String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @SerializedName("children")
    String children;

    public String getChildren() {
        return children;
    }

    public void setChildren(String children) {
        this.children = children;
    }

    public boolean isLastChild() {
        return isLastChild;
    }

    public void setLastChild(boolean lastChild) {
        isLastChild = lastChild;
    }
}
