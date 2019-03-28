package com.nuzul.caffein.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ResultDrink {
    @SerializedName("status")
    private String status;
    @SerializedName("result")
    private List<Drink> result = new ArrayList<Drink>();
    @SerializedName("message")
    private String message;

    public ResultDrink() {}

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public List<Drink> getResult() {
        return result;
    }
    public void setResult(List<Drink> result) {
        this.result = result;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}
