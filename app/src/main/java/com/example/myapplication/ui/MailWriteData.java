package com.example.myapplication.ui;

import com.google.gson.annotations.SerializedName;

public class MailWriteData {
    @SerializedName("sender")
    String sender;

    @SerializedName("recipient")
    String recipient;

    @SerializedName("content")
    String content;


    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public MailWriteData(String sender, String recipient, String content) {
        this.sender = sender;
        this.recipient = recipient;
        this.content = content;
    }
}
