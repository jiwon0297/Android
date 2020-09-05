package com.example.myapplication.login.Data;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {
    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("id")
    private int id;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public int getid() {
        return id;
    }
}
