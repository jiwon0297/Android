package com.example.myapplication.Join;

import com.google.gson.annotations.SerializedName;

public class JoinData {
    @SerializedName("name")
    private String name;

    @SerializedName("email")
    private String email;

    @SerializedName("password")
    private String password;

    @SerializedName("nickname")
    private String nickname;

    @SerializedName("gender")
    private String gender;

    public JoinData(String name, String email, String password, String nickname, String gender) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.gender = gender;
    }
}
