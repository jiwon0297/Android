package com.example.myapplication.lost;

import com.google.gson.annotations.SerializedName;

public class LostCommentDeleteData {
    @SerializedName("number")
    int number;

    public LostCommentDeleteData(int number) {
        this.number = number;
    }
}
