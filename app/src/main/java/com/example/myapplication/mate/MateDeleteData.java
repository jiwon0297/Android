package com.example.myapplication.mate;

import com.google.gson.annotations.SerializedName;

public class MateDeleteData {
    @SerializedName("number")
    int number;

    public MateDeleteData(int number) {
        this.number = number;
    }
}
