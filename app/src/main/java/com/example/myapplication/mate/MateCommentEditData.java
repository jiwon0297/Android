package com.example.myapplication.mate;

import com.google.gson.annotations.SerializedName;

public class MateCommentEditData {
    @SerializedName("number")
    int number;

    @SerializedName("postnumber")
    int postnumber;

    @SerializedName("content")
    String content;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getPostnumber() {
        return postnumber;
    }

    public void setPostnumber(int postnumber) {
        this.postnumber = postnumber;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public MateCommentEditData(int postnumber, int number, String content) {
        this.postnumber = postnumber;
        this.number = number;
        this.content = content;
    }
}
