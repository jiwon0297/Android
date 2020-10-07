package com.example.myapplication.mate;

import com.google.gson.annotations.SerializedName;

public class MateCommentWriteData {
    @SerializedName("postnumber")
    int postnumber;

    @SerializedName("nickname")
    String nickname;

    @SerializedName("content")
    String content;

    public int getPostnumber() {
        return postnumber;
    }

    public void setPostnumber(int postnumber) {
        this.postnumber = postnumber;
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

    public MateCommentWriteData(int postnumber, String nickname, String content) {
        this.postnumber = postnumber;
        this.nickname = nickname;
        this.content = content;
    }
}
