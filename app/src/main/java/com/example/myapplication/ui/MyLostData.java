package com.example.myapplication.ui;

import com.google.gson.annotations.SerializedName;

public class MyLostData {
    @SerializedName("campus")
    String campus;

    @SerializedName("title")
    String title;

    @SerializedName("nickname")
    String nickname;

    @SerializedName("type")
    String type;

    @SerializedName("date")
    String date;

    @SerializedName("content")
    String content;

    @SerializedName("number")
    int number;

    public MyLostData(String nickname) {
        this.nickname = nickname;
    }

    public MyLostData(){}
}
