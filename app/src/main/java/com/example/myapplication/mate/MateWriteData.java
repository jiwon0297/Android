package com.example.myapplication.mate;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class MateWriteData {
    @SerializedName("number")
    int number;

    @SerializedName("title")
    String title;

    @SerializedName("nickname")
    String nickname;

    @SerializedName("content")
    String content;

    @SerializedName("date")
    String date;

    @SerializedName("cate")
    String cate;

    @SerializedName("campus")
    String campus;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCate() {
        return cate;
    }

    public void setCate(String cate) {
        this.cate = cate;
    }

    public String getCampus() {
        return campus;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

    public MateWriteData(int number, String title, String nickname, String content, String date, String cate, String campus) {
        this.number = number;
        this.title = title;
        this.nickname = nickname;
        this.date = date;
        this.content = content;
        this.cate = cate;
        this.campus = campus;
    }
}
