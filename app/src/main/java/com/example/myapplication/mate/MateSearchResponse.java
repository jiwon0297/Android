package com.example.myapplication.mate;

import com.example.myapplication.lost.LostSearchResponse;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

public class MateSearchResponse {
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
    private List<MateSearchResponse> result;

    public List<MateSearchResponse> getResult() {
        return result;
    }

    private int number;
    private String title;
    private String nickname;
    private String campus;
    private Date date;
    private String content;
    private String cate;

    public String getCate() {return cate;}
    public int getNumber() { return number;}
    public String getTitle() { return title;}
    public String getNickname() { return nickname;}
    public String getCampus() { return campus;}
    public Date getDate() { return date; }
    public String getContent() {return content;}
}
