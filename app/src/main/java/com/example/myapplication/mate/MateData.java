package com.example.myapplication.mate;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

public class MateData {
    @Expose
    @SerializedName("number") private int number;
    @Expose
    @SerializedName("title") private String userId;
    @Expose
    @SerializedName("nickname") private String nickname;
    @Expose
    @SerializedName("content") private String userRestaurant;
    @Expose
    @SerializedName("date") private Date date;
    @Expose
    @SerializedName("cate") private String restaurantTel;
    @Expose
    @SerializedName("campus") private String campus;

    @SerializedName("result")
    @Expose
    private List<MateData> result;

    public List<MateData> getResult() {
        return result;
    }

    public void setResult(List<MateData> result) {
        this.result = result;
    }
}
