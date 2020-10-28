package com.example.myapplication.mate;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class MateSearchData {
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

    @SerializedName("cate")
    String cate;

    @SerializedName("content")
    String content;

    public MateSearchData(String cate) {
        this.cate = cate;
    }

    public MateSearchData(){

    }
}
