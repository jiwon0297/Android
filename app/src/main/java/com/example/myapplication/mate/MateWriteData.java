package com.example.myapplication.mate;

import com.google.gson.annotations.SerializedName;

public class MateWriteData {
    @SerializedName("title")
    String title;

    @SerializedName("nickname")
    String nickname;

    @SerializedName("content")
    String content;

    @SerializedName("cate")
    String cate;

    @SerializedName("campus")
    String campus;

    public MateWriteData(String title, String nickname, String content, String cate, String campus) {
        this.title = title;
        this.nickname = nickname;
        this.content = content;
        this.cate = cate;
        this.campus = campus;
    }
}
