package com.example.myapplication.ui;

import com.google.gson.annotations.SerializedName;

public class MypageResponse {
    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("email")
    private String email;

    @SerializedName("name")
    private String name;

    @SerializedName("nickname")
    private String nickname;

    @SerializedName("gender")
    private String gender;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }
    public String getNickname() {
        return nickname;
    }
    public String getGender() {
        return gender;
    }
}
