package com.example.myapplication.lost;

import com.google.gson.annotations.SerializedName;

public class LostData {
    @SerializedName("campus")
    String campus;

    @SerializedName("title")
    String title;

    @SerializedName("id")
    String id;

    public LostData(String campus, String title, String id) {
        this.campus = campus;
        this.title = title;
        this.id = id;
    }
}
