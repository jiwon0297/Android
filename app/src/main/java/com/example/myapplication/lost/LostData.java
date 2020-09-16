package com.example.myapplication.lost;

import com.google.gson.annotations.SerializedName;

public class LostData {
    @SerializedName("campus")
    String campus;

    @SerializedName("title")
    String title;

    @SerializedName("id")
    String id;

    @SerializedName("type")
    String type;

    @SerializedName("content")
    String content;

    @SerializedName("number")
    String number;

    public LostData(String campus, String title, String id) {
        this.campus = campus;
        this.title = title;
        this.id = id;
    }

    public LostData(String type) {
        this.type = type;
    }

    public LostData(){}

}
