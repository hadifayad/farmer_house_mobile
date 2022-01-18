package com.farmerhouse.models;

import com.google.gson.annotations.SerializedName;

public class ProfileData {

    @SerializedName("fullname")
    String fullname;

    @SerializedName("village")
    String village;
    @SerializedName("email")
    String email;
    @SerializedName("address")
    String address;
    @SerializedName("land_village")
    String land_village;
    @SerializedName("land_height")
    String land_height;
    @SerializedName("land_state")
    String land_state;

      @SerializedName("land_id")
    String land_id;
      @SerializedName("land_area")
    String land_area	;
      @SerializedName("land_water")
    String land_water;
 @SerializedName("land_has_well")
    String land_has_well;
 @SerializedName("land_related_public_water")
    String land_related_public_water;
 @SerializedName("land_has_pond")
    String land_has_pond;


}
