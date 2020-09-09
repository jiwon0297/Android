package com.example.myapplication.mate;

import com.google.gson.annotations.SerializedName;


public class MateData {

    @SerializedName("cate")
    private String cate;

    public MateData(String cate) {
        this.cate = cate;
    }

}
