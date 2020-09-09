package com.example.myapplication.ui;

import com.google.gson.annotations.SerializedName;

public class MypageData {
    @SerializedName("email")
    String email;
    @SerializedName("name")
    String name;
    @SerializedName("nickname")
    String nickname;
    @SerializedName("gender")
    String gender;

    public MypageData(String email,String name, String nickname, String gender) {
        this.email = email;
        this.name = name;
        this.nickname = nickname;
        this.gender = gender;
    }
}

