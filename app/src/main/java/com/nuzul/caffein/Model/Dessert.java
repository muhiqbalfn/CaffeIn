package com.nuzul.caffein.Model;

import com.google.gson.annotations.SerializedName;

public class Dessert {
    @SerializedName("name_dessert")
    private String name_dessert;
    @SerializedName("price_dessert")
    private String price_dessert;
    @SerializedName("photo_id")
    private String photo_id;
    private String action;

    public Dessert(){}

    public Dessert(String name_dessert, String price_dessert, String photo_id, String action) {
        this.name_dessert = name_dessert;
        this.price_dessert = price_dessert;
        this.photo_id = photo_id;
        this.action = action;
    }

    public String getName_dessert() {
        return name_dessert;
    }

    public void setName_dessert(String name_dessert) {
        this.name_dessert = name_dessert;
    }

    public String getPrice_dessert() {
        return price_dessert;
    }

    public void setPrice_dessert(String price_dessert) {
        this.price_dessert = price_dessert;
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
