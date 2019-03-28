package com.nuzul.caffein.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ResultFood {
    @SerializedName("status")
    private String status;
    @SerializedName("result")
    private List<Food> result = new ArrayList<Food>();
    @SerializedName("message")
    private String message;

    public ResultFood() {}

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public List<Food> getResult() {
        return result;
    }
    public void setResult(List<Food> result) {
        this.result = result;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}