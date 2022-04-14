package com.uzarsif.models;

import com.google.gson.annotations.SerializedName;

public class ActivityType {

    @SerializedName("id")
    String id ;

    @SerializedName("name")
    String name ;

    @SerializedName("description")
    String description ;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
