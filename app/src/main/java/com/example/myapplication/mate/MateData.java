package com.example.myapplication.mate;

import com.google.gson.annotations.SerializedName;


public class MateData {
    @SerializedName("number")
    int number;

    @SerializedName("campus")
    String campus;

    @SerializedName("title")
    String title;

    @SerializedName("nickname")
    String nickname;

    @SerializedName("date")
    String date;

    @SerializedName("cate")
    private String cate;

    @SerializedName("content")
    String content;

    public MateData(String cate) {
        this.cate = cate;
    }

    public MateData() {

    }
}
