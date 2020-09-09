package com.example.myapplication.lost;

import com.google.gson.annotations.SerializedName;

public class LostData {
    @SerializedName("strTitle")
    String strTitle;

    @SerializedName("strDate")
    String strDate;

    public LostData(String strTitle, String strDate) {
        this.strTitle = strTitle;
        this.strDate = strDate;
    }
}
