package com.example.myapplication.lost;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class LostCommentData {
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

    public LostCommentData(int postnumber) {
        this.postnumber = postnumber;
    }

    public LostCommentData(){

    }
}
