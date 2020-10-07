package com.example.myapplication.mate;

import com.google.gson.annotations.SerializedName;

public class MateCommentWriteResponse {
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
