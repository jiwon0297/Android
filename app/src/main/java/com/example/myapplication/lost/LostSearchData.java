package com.example.myapplication.lost;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class LostSearchData {
    @SerializedName("campus")
    String campus;

    @SerializedName("title")
    String title;

    @SerializedName("nickname")
    String nickname;

    @SerializedName("date")
    Date date;

    @SerializedName("number")
    int number;

    @SerializedName("type")
    String type;

    @SerializedName("content")
    String content;

    @SerializedName("url")
    String url;

    public LostSearchData(String type, String title) {
        this.type = type;
        this.title = title;
    }

    public LostSearchData(){

    }
}
