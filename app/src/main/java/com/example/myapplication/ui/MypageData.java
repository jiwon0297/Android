package com.example.myapplication.ui;

import com.google.gson.annotations.SerializedName;

public class MypageData {
    @SerializedName("nickname")
    String nickname;

    public MypageData(String nickname) {
        this.nickname = nickname;
    }
}

