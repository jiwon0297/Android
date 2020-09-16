package com.example.myapplication.lost;

import com.google.gson.annotations.SerializedName;

public class LostWriteData {
    @SerializedName("title")
    String title;

    @SerializedName("type")
    String type;

    @SerializedName("nickname")
    String nickname;

    @SerializedName("content")
    String content;

    @SerializedName("campus")
    String campus;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCampus() {
        return campus;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

    public LostWriteData(String title, String nickname, String content, String type, String campus) {
        this.title = title;
        this.nickname = nickname;
        this.content = content;
        this.type = type;
        this.campus = campus;
    }
}
