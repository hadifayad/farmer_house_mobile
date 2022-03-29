package com.farmerhouse.models;

import com.google.gson.annotations.SerializedName;

public class MandoubAked {

    @SerializedName("id")
    String id;

    @SerializedName("farmerId")
    String farmerId;

    @SerializedName("mandoubId")
    String mandoubId ;


    @SerializedName("place")
    String place;


    @SerializedName("quantity")
    String quantity;

    @SerializedName("type")
    String type;

    @SerializedName("price")
    String price;

    @SerializedName("tesleem_place")
    String tesleem_place;

    @SerializedName("date")
    String date;

    @SerializedName("notes")
    String notes;

    public String getNotes() {
        return notes;
    }

    public String getDate() {
        return date;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getFarmerId() {
        return farmerId;
    }

    public String getMandoubId() {
        return mandoubId;
    }

    public String getPlace() {
        return place;
    }

    public String getPrice() {
        return price;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getTesleem_place() {
        return tesleem_place;
    }
}
