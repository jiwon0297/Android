package com.example.myapplication.mate;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MateResponse {

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    @SerializedName("result")
    @Expose
    private List<Mate> result;

    public List<Mate> getResult() {
        return result;
    }

    public void setResult(List<Mate> result) {
        this.result = result;
    }

}
