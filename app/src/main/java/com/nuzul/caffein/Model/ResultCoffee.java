package com.nuzul.caffein.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ResultCoffee {
    @SerializedName("status")
    private String status;
    @SerializedName("result")
    private List<Coffee> result = new ArrayList<Coffee>();
    @SerializedName("message")
    private String message;

    public ResultCoffee() {}

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public List<Coffee> getResult() {
        return result;
    }
    public void setResult(List<Coffee> result) {
        this.result = result;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}