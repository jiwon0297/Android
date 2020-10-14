package com.example.myapplication.lost;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class LostData {
    @SerializedName("campus")
    String campus;

    @SerializedName("title")
    String title;

    @SerializedName("nickname")
    String nickname;

    @SerializedName("type")
    String type;

    @SerializedName("content")
    String content;

    @SerializedName("number")
    int number;

    @SerializedName("date")
    Date date;

    public LostData(String type) {
        this.type = type;
    }

    public LostData(){}

}
