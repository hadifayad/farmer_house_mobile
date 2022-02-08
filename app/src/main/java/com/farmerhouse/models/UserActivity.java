package com.farmerhouse.models;

import com.google.gson.annotations.SerializedName;

public class UserActivity {
    @SerializedName("id")
    String id;
    @SerializedName("plant")
    String plant;
    @SerializedName("height")
    String height;
    @SerializedName("mantaa")
    String mantaa;
    @SerializedName("mazrouat_type")
    String mazrouat_type;
    @SerializedName("soil")
    String soil;
    @SerializedName("water_type")
    String water_type;
    @SerializedName("plants_type")
    String plants_type;
    @SerializedName("planting_type")
    String planting_type;
    @SerializedName("mawsem")
    String mawsem;
    @SerializedName("date")
    String date;

    public String getId() {
        return id;
    }

    public String getHeight() {
        return height;
    }

    public String getMawsem() {
        return mawsem;
    }

    public void setMawsem(String mawsem) {
        this.mawsem = mawsem;
    }

    public String getMantaa() {
        return mantaa;
    }

    public String getMazrouat_type() {
        return mazrouat_type;
    }

    public String getPlant() {
        return plant;
    }

    public String getPlanting_type() {
        return planting_type;
    }

    public String getPlants_type() {
        return plants_type;
    }

    public String getSoil() {
        return soil;
    }

    public String getWater_type() {
        return water_type;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public void setMantaa(String mantaa) {
        this.mantaa = mantaa;
    }

    public void setMazrouat_type(String mazrouat_type) {
        this.mazrouat_type = mazrouat_type;
    }

    public void setPlant(String plant) {
        this.plant = plant;
    }

    public void setPlanting_type(String planting_type) {
        this.planting_type = planting_type;
    }

    public void setPlants_type(String plants_type) {
        this.plants_type = plants_type;
    }

    public void setSoil(String soil) {
        this.soil = soil;
    }

    public void setWater_type(String water_type) {
        this.water_type = water_type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
