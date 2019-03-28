package com.nuzul.caffein.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ResultPesanan {
    @SerializedName("status")
    private String status;
    @SerializedName("result")
    private List<Pesanan> result = new ArrayList<Pesanan>();
    @SerializedName("message")
    private String message;

    public ResultPesanan() {}

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Pesanan> getResult() {
        return result;
    }

    public void setResult(List<Pesanan> result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}