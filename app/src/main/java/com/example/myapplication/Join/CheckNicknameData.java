package com.example.myapplication.Join;

import com.google.gson.annotations.SerializedName;

public class CheckNicknameData {
    @SerializedName("nickname")
    String nickname;

    public CheckNicknameData(String nickname) {
        this.nickname = nickname;
    }
}
