package com.example.myapplication.lost;

import com.example.myapplication.mate.MateResponse;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class LostResponse {
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
    private List<LostResponse> result;

    public List<LostResponse> getResult() {
        return result;
    }

    private String title;
    private String id;
    private String campus;

    public String getTitle() { return title;}
    public String getId() { return id;}
    public String getCampus() { return campus;}
}
