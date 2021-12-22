package com.farmerhouse.models;

import com.google.gson.annotations.SerializedName;

public class Village {

    @SerializedName("id")
    String id;
    @SerializedName("name")
    String name;

    @SerializedName("kadaa_id")
    String kadaa_id;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }


    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKadaa_id() {
        return kadaa_id;
    }

    public void setKadaa_id(String kadaa_id) {
        this.kadaa_id = kadaa_id;
    }
}
