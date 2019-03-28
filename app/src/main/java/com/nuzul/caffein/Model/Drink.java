package com.nuzul.caffein.Model;

import com.google.gson.annotations.SerializedName;

public class Drink {
    @SerializedName("name_drink")
    private String name_drink;
    @SerializedName("price_drink")
    private String price_drink;
    @SerializedName("photo_id")
    private String photo_id;
    private String action;

    public Drink() {}

    public Drink(String name_drink, String price_drink, String photo_id, String action) {
        this.name_drink = name_drink;
        this.price_drink = price_drink;
        this.photo_id = photo_id;
        this.action = action;
    }

    public String getName_drink() {
        return name_drink;
    }

    public void setName_drink(String name_drink) {
        this.name_drink = name_drink;
    }

    public String getPrice_drink() {
        return price_drink;
    }

    public void setPrice_drink(String price_drink) {
        this.price_drink = price_drink;
    }

    public String getPhoto_id() {
        return photo_id;
    }

    public void setPhoto_id(String photo_id) {
        this.photo_id = photo_id;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}