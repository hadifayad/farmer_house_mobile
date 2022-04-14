package com.uzarsif.models;

import com.google.gson.annotations.SerializedName;

public class Plant {

    @SerializedName("id")
    String id;
    String data_id;
    @SerializedName("name")
    String name;
//    String height;
//    String mantaa;
//    String water_ways;
//    String plants_types_id;
//    String mawsem;
//    String planting_type;
//    String mazrouat_type;
//    String soil_type;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getData_id() {
        return data_id;
    }

    public void setData_id(String data_id) {
        this.data_id = data_id;
    }

//    public String getHeight() {
//        return height;
//    }
//
//    public void setHeight(String height) {
//        this.height = height;
//    }
//
//    public String getMantaa() {
//        return mantaa;
//    }
//
//    public void setMantaa(String mantaa) {
//        this.mantaa = mantaa;
//    }
//
//    public String getWater_ways() {
//        return water_ways;
//    }
//
//    public void setWater_ways(String water_ways) {
//        this.water_ways = water_ways;
//    }
//
//    public String getPlants_types_id() {
//        return plants_types_id;
//    }
//
//    public void setPlants_types_id(String plants_types_id) {
//        this.plants_types_id = plants_types_id;
//    }
//
//    public String getMawsem() {
//        return mawsem;
//    }
//
//    public void setMawsem(String mawsem) {
//        this.mawsem = mawsem;
//    }
//
//    public String getPlanting_type() {
//        return planting_type;
//    }
//
//    public void setPlanting_type(String planting_type) {
//        this.planting_type = planting_type;
//    }
//
//    public String getMazrouat_type() {
//        return mazrouat_type;
//    }
//
//    public void setMazrouat_type(String mazrouat_type) {
//        this.mazrouat_type = mazrouat_type;
//    }
//
//    public String getSoil_type() {
//        return soil_type;
//    }
//
//    public void setSoil_type(String soil_type) {
//        this.soil_type = soil_type;
//    }
}
