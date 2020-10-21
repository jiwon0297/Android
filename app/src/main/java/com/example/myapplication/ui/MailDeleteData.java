package com.example.myapplication.ui;

import com.google.gson.annotations.SerializedName;

public class MailDeleteData {
    @SerializedName("number")
    int number;

    public MailDeleteData(int number) {
        this.number = number;
    }
}
