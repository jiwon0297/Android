package com.example.myapplication.ui;

import com.google.gson.annotations.SerializedName;

public class MailWriteResponse {
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
