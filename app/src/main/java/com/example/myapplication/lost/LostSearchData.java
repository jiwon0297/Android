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

    public LostSearchData(String cate) {
        this.type = type;
    }

    public LostSearchData(){

    }
}
