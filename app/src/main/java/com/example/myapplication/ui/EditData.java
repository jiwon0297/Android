package com.example.myapplication.ui;

import com.google.gson.annotations.SerializedName;

public class EditData {
    @SerializedName("email")
    String email;

    @SerializedName("password")
    String password;

    @SerializedName("name")
    String name;

    @SerializedName("nickname")
    String nickname;

    @SerializedName("gender")
    String gender;

    public EditData(String email, String password, String name, String nickname, String gender) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.nickname = nickname;
        this.gender = gender;
    }
}