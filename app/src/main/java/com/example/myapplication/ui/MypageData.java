package com.example.myapplication.login;

import com.google.gson.annotations.SerializedName;

public class MypageData {
    @SerializedName("email")
    String email;


    public MypageData(String email) {
        this.email = email;
    }
}

