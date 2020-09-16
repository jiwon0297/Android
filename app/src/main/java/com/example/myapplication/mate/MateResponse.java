package com.example.myapplication.mate;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MateResponse {

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
    private List<MateResponse> result;

    public List<MateResponse> getResult() {
        return result;
    }

    private int number;
    private String title;
    private String nickname;
    private String campus;
    private String date;
    private String content;

    public int getNumber() { return number;}
    public String getTitle() { return title;}
    public String getNickname() { return nickname;}
    public String getCampus() { return campus;}
    public String getDate() { return date; }
    public String getContent() {return content;}

}
