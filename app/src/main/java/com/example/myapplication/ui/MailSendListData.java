package com.example.myapplication.ui;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class MailSendListData {
    @SerializedName("recipient")
    String recipient;

    @SerializedName("sender")
    String sender;

    @SerializedName("date")
    Date date;

    @SerializedName("number")
    int number;

    @SerializedName("content")
    String content;

    public MailSendListData(String sender) {
        this.sender = sender;
    }

    public MailSendListData(){

    }
}
