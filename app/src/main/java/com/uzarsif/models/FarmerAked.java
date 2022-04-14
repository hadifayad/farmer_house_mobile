package com.uzarsif.models;

import com.google.gson.annotations.SerializedName;

public class FarmerAked {

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

        @SerializedName("email")
        String email;

    @SerializedName("phone")
    String phone;

    @SerializedName("address")
    String address;

    @SerializedName("village")
    String village;

    public String getPhone() {
        return phone;
    }

    public String getVillage() {
        return village;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

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
