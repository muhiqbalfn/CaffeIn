package com.nuzul.caffein.Model;

import com.google.gson.annotations.SerializedName;

public class Coffee {
    @SerializedName("name_coffee")
    private String name_coffee;
    @SerializedName("price_coffee")
    private String price_coffee;
    @SerializedName("photo_id")
    private String photo_id;
    private String action;

    public Coffee(){}

    public Coffee(String name_coffee, String price_coffee, String photo_id, String action) {
        this.name_coffee = name_coffee;
        this.price_coffee = price_coffee;
        this.photo_id = photo_id;
        this.action = action;
    }

    public String getName_coffee() {
        return name_coffee;
    }

    public void setName_coffee(String name_coffee) {
        this.name_coffee = name_coffee;
    }

    public String getPrice_coffee() {
        return price_coffee;
    }

    public void setPrice_coffee(String price_coffee) {
        this.price_coffee = price_coffee;
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
