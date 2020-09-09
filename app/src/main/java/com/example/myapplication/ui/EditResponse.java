package com.example.myapplication.ui;

import com.google.gson.annotations.SerializedName;

public class EditResponse {
    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("userId")
    private String userId;

    @SerializedName("nickname")
    private String nickname;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getUserId() {
        return userId;
    }

    public String getNickname(){
        return nickname;
    }
}
