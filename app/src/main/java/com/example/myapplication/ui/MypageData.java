package com.example.myapplication.ui;

import com.google.gson.annotations.SerializedName;

public class MypageData {
    @SerializedName("email")
    String email;


    public MypageData(String email) {
        this.email = email;
    }
}

