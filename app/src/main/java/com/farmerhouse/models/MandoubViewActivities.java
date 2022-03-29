package com.farmerhouse.models;

import com.google.gson.annotations.SerializedName;

public class MandoubViewActivities {



        @SerializedName("id")
        String id ;

        @SerializedName("mandoobId")
        String mandoobId  ;

        @SerializedName("activity_type")
        String activity_type  ;

        @SerializedName("notes")
        String notes ;

        @SerializedName("date")
        String date ;

        @SerializedName("farmer")
        String farmer  ;

        public String getId() {
            return id;
        }

        public String getMandoobId() {
            return mandoobId;
        }

        public String getDate() {
            return date;
        }

        public String getActivity_type() {
            return activity_type;
        }

        public String getFarmer() {
            return farmer;
        }

        public String getNotes() {
            return notes;
        }


}
