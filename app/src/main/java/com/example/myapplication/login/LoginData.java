package com.example.myapplication.login;

import com.google.gson.annotations.SerializedName;

public class LoginData {
    @SerializedName("emailText")
    String emailText;

    @SerializedName("userPwd")
    String userPwd;

    public LoginData(String emailText, String userPwd) {
        this.emailText = emailText;
        this.userPwd = userPwd;
    }
}
