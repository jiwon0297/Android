package com.example.myapplication.mate;

import com.google.gson.annotations.SerializedName;

public class MateCommentDeleteData {
    @SerializedName("number")
    int number;

    public MateCommentDeleteData(int number) {
        this.number = number;
    }
}
