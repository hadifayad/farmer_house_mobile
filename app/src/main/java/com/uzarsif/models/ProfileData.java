package com.uzarsif.models;

import com.google.gson.annotations.SerializedName;

public class ProfileData {

    @SerializedName("fullname")
    String fullname;

    @SerializedName("mandoob")
    String mandoob;

    @SerializedName("village")
    String village;
    @SerializedName("village_id")
    String village_id;
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
    String land_area;
    @SerializedName("land_water")
    String land_water;
    @SerializedName("land_has_well")
    String land_has_well;
    @SerializedName("land_related_public_water")
    String land_related_public_water;
    @SerializedName("land_has_pond")
    String land_has_pond;

    @SerializedName("phone")
    String phone;


    @SerializedName("second_phone")
    String second_phone;


    @SerializedName("human")
    String human;
    @SerializedName("animals")
    String animals;
    @SerializedName("automatic_energy")
    String automatic_energy;
    @SerializedName("wind_energy")
    String wind_energy;
    @SerializedName("solar_energy")
    String solar_energy;
    @SerializedName("electricity")
    String electricity;
    @SerializedName("jarrar")
    String jarrar;
    @SerializedName("rash")
    String rash;
    @SerializedName("maktoura")
    String maktoura;
    @SerializedName("sahreej")
    String sahreej;
    @SerializedName("mdakha")
    String mdakha;
    @SerializedName("shabaket_ray")
    String shabaket_ray;

    @SerializedName("alat")
    String alat;

    public void setVillage_id(String village_id) {
        this.village_id = village_id;
    }

    public String getVillage_id() {
        return village_id;
    }

    public String getAlat() {
        return alat;
    }

    public void setAlat(String alat) {
        this.alat = alat;
    }

    public String getAnimals() {
        return animals;
    }

    public void setAnimals(String animals) {
        this.animals = animals;
    }

    public String getAutomatic_energy() {
        return automatic_energy;
    }

    public void setAutomatic_energy(String automatic_energy) {
        this.automatic_energy = automatic_energy;
    }

    public String getMandoob() {
        return mandoob;
    }

    public void setMandoob(String mandoob) {
        this.mandoob = mandoob;
    }

    public String getElectricity() {
        return electricity;
    }

    public void setElectricity(String electricity) {
        this.electricity = electricity;
    }

    public String getHuman() {
        return human;
    }

    public void setHuman(String human) {
        this.human = human;
    }

    public String getJarrar() {
        return jarrar;
    }

    public void setJarrar(String jarrar) {
        this.jarrar = jarrar;
    }

    public String getMaktoura() {
        return maktoura;
    }

    public void setMaktoura(String maktoura) {
        this.maktoura = maktoura;
    }

    public String getMdakha() {
        return mdakha;
    }

    public void setMdakha(String mdakha) {
        this.mdakha = mdakha;
    }

    public String getRash() {
        return rash;
    }

    public void setRash(String rash) {
        this.rash = rash;
    }

    public String getSahreej() {
        return sahreej;
    }

    public void setSahreej(String sahreej) {
        this.sahreej = sahreej;
    }

    public String getShabaket_ray() {
        return shabaket_ray;
    }

    public void setShabaket_ray(String shabaket_ray) {
        this.shabaket_ray = shabaket_ray;
    }

    public String getSolar_energy() {
        return solar_energy;
    }

    public void setSolar_energy(String solar_energy) {
        this.solar_energy = solar_energy;
    }

    public String getWind_energy() {
        return wind_energy;
    }

    public void setWind_energy(String wind_energy) {
        this.wind_energy = wind_energy;
    }



    public String getPhone() {
        return phone;
    }

    public String getSecond_phone() {
        return second_phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setSecond_phone(String second_phone) {
        this.second_phone = second_phone;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getFullname() {
        return fullname;
    }

    public String getLand_area() {
        return land_area;
    }

    public String getLand_has_pond() {
        return land_has_pond;
    }

    public String getLand_has_well() {
        return land_has_well;
    }

    public String getLand_height() {
        return land_height;
    }


    public String getLand_id() {
        return land_id;
    }

    public String getLand_related_public_water() {
        return land_related_public_water;
    }


    public String getLand_state() {
        return land_state;
    }


    public String getLand_village() {
        return land_village;
    }

    public String getLand_water() {
        return land_water;
    }

    public String getVillage() {
        return village;
    }

    public void setLand_village(String land_village) {
        this.land_village = land_village;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public void setLand_area(String land_area) {
        this.land_area = land_area;
    }

    public void setLand_has_pond(String land_has_pond) {
        this.land_has_pond = land_has_pond;
    }

    public void setLand_has_well(String land_has_well) {
        this.land_has_well = land_has_well;
    }

    public void setLand_height(String land_height) {
        this.land_height = land_height;
    }

    public void setLand_id(String land_id) {
        this.land_id = land_id;
    }

    public void setLand_related_public_water(String land_related_public_water) {
        this.land_related_public_water = land_related_public_water;
    }

    public void setLand_state(String land_state) {
        this.land_state = land_state;
    }

    public void setLand_water(String land_water) {
        this.land_water = land_water;
    }

    public void setVillage(String village) {
        this.village = village;
    }


}
