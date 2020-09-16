package com.example.myapplication.lost;

import com.google.gson.annotations.SerializedName;

public class LostEditData {
    @SerializedName("number")
    int number;

    @SerializedName("title")
    String title;

    @SerializedName("campus")
    String campus;

    @SerializedName("type")
    String type;

    @SerializedName("content")
    String content;

    public int getNumber() {
        return number;
    }
    public String getTitle() { return title; }
    public String getCampus() { return campus; }
    public String getType() { return type; }
    public String getContent() { return content; }

    public void setNumber(int number) {
        this.number = number;
    }
    public void setTitle(String title) { this.title = title; }
    public void setCampus(String campus) { this.campus = campus; }
    public void setType(String type) { this.type = type; }
    public void setContent(String content) { this.content = content; }

    public LostEditData(String title, int number, String campus, String type, String content) {
        this.campus = campus;
        this.number = number;
        this.title = title;
        this.type = type;
        this.content = content;
    }

    public LostEditData(){}


}
