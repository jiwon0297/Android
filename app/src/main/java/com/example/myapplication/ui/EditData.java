package com.example.myapplication.ui;

import com.google.gson.annotations.SerializedName;

public class EditData {

    @SerializedName("password")
    String password;

    @SerializedName("nickname")
    String nickname;

    public EditData(String password, String nickname) {
        this.password = password;
        this.nickname = nickname;
    }
}