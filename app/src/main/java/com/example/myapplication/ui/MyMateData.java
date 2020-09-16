package com.example.myapplication.ui;

import com.google.gson.annotations.SerializedName;


public class MyMateData {
    @SerializedName("campus")
    String campus;

    @SerializedName("title")
    String title;

    @SerializedName("nickname")
    String nickname;

    @SerializedName("date")
    String date;

    @SerializedName("number")
    int number;

    @SerializedName("cate")
    String cate;

    @SerializedName("content")
    String content;

    public MyMateData(String nickname){
       this.nickname = nickname;
    }
    public MyMateData(){

    }

}

