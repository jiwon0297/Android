package com.example.myapplication.ui;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class MailReceiveListData {
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

    public MailReceiveListData(String recipient) {
        this.recipient = recipient;
    }

    public MailReceiveListData(){

    }
}
