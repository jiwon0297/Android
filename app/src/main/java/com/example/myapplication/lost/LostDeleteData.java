package com.example.myapplication.lost;

import com.google.gson.annotations.SerializedName;

public class LostDeleteData {
    @SerializedName("number")
    int number;

    public LostDeleteData(int number) {
        this.number = number;
    }

}
