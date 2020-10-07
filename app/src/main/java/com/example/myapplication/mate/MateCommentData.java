package com.example.myapplication.mate;

import com.google.gson.annotations.SerializedName;

public class MateCommentData {
    @SerializedName("postnumber")
    int postnumber;

    @SerializedName("nickname")
    String nickname;

    @SerializedName("date")
    String date;

    @SerializedName("number")
    int number;


    @SerializedName("content")
    String content;

    public MateCommentData(int postnumber) {
        this.postnumber = postnumber;
    }

    public MateCommentData(){

    }
}
