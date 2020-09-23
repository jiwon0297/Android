package com.example.myapplication.Join;

import com.google.gson.annotations.SerializedName;

public class CheckEmailResponse {
    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
