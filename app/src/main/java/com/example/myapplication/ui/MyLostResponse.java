package com.example.myapplication.ui;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MyLostResponse {
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

    @SerializedName("result")
    @Expose
    private List<MyLostResponse> result;

    public List<MyLostResponse> getResult() {
        return result;
    }

    private int number;
    private String title;
    private String nickname;
    private String campus;
    private String content;
    private String type;

    public int getNumber() { return number;}
    public String getTitle() { return title;}
    public String getNickname() { return nickname;}
    public String getCampus() { return campus;}
    public String getContent() {return content;}
    public String getType(){return type;}

}
