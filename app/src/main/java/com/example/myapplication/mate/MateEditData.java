package com.example.myapplication.mate;

import com.google.gson.annotations.SerializedName;

public class MateEditData {
    @SerializedName("number")
    int number;

    @SerializedName("title")
    String title;

    @SerializedName("content")
    String content;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public MateEditData(String title, int number, String content, String cate, String campus) {
        this.title = title;
        this.number = number;
        this.content = content;
        this.cate = cate;
        this.campus = campus;
    }
}
