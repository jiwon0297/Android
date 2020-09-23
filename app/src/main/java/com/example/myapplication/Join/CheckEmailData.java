package com.example.myapplication.Join;

import com.google.gson.annotations.SerializedName;

public class CheckEmailData {
    @SerializedName("email")
    String email;

    public CheckEmailData(String email){
        this.email = email;
    }
}
