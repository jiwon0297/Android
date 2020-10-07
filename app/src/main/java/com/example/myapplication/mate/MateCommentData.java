package com.example.myapplication.mate;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class MateCommentData {
    @SerializedName("postnumber")
    int postnumber;

    @SerializedName("nickname")
    String nickname;

    @SerializedName("date")
    Date date;

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
