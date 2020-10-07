package com.example.myapplication.mate;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

public class MateCommentResponse {

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
    private List<MateCommentResponse> result;

    public List<MateCommentResponse> getResult() {
        return result;
    }

    private int number;
    private int postnumber;
    private String nickname;
    private Date date;
    private String content;

    public int getNumber() { return number;}
    public String getNickname() { return nickname;}
    public int getPostnumber() { return postnumber;}
    public Date getDate() { return date; }
    public String getContent() {return content;}
}
