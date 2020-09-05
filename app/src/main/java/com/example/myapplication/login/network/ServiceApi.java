package com.example.myapplication.login.network;

import com.example.myapplication.login.Data.JoinData;
import com.example.myapplication.login.Data.JoinResponse;
import com.example.myapplication.login.Data.LoginData;
import com.example.myapplication.login.Data.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ServiceApi {
    @POST("/member/login")
    Call<LoginResponse> userLogin(@Body LoginData data);

    @POST("/member/join")
    Call<JoinResponse> userJoin(@Body JoinData data);
}
