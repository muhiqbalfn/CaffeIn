package com.nuzul.caffein.Model;

import com.google.gson.annotations.SerializedName;

public class Food {
    @SerializedName("id_food")
    private String id_food;
    @SerializedName("name_food")
    private String name_food;
    @SerializedName("price_food")
    private String price_food;
    @SerializedName("photo_id")
    private String photo_id;
    private String action;

    public Food() {
    }

    public Food(String id_food, String name_food, String price_food, String photo_id, String action) {
        this.id_food = id_food;
        this.name_food = name_food;
        this.price_food = price_food;
        this.photo_id = photo_id;
        this.action = action;
    }

    public String getId_food() {
        return id_food;
    }

    public void setId_food(String id_food) {
        this.id_food = id_food;
    }

    public String getName_food() {
        return name_food;
    }

    public void setName_food(String name_food) {
        this.name_food = name_food;
    }

    public String getPrice_food() {
        return price_food;
    }

    public void setPrice_food(String price_food) {
        this.price_food = price_food;
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