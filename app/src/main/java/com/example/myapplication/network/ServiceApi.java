package com.example.myapplication.network;

import com.example.myapplication.Join.JoinData;
import com.example.myapplication.Join.JoinResponse;
import com.example.myapplication.login.LoginData;
import com.example.myapplication.login.LoginResponse;
import com.example.myapplication.lost.LostData;
import com.example.myapplication.mate.MateData;
import com.example.myapplication.mate.MateResponse;
import com.example.myapplication.mate.MateWriteData;
import com.example.myapplication.mate.MateWriteResponse;
import com.example.myapplication.ui.MypageData;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ServiceApi {
    @POST("/member/login")
    Call<LoginResponse> userLogin(@Body LoginData data);

    @POST("/member/join")
    Call<JoinResponse> userJoin(@Body JoinData data);

    @POST("/member/mypage")
    Call<JoinResponse> userMypage(@Body MypageData data);

    @POST("/mate/list")
    Call<MateResponse> matelist(@Body MateData data);

    @FormUrlEncoded
    @POST("/mate/write")
    Call<MateWriteResponse> matewrite(@Body MateWriteData data);

    @POST("/lost/list")
    Call<JoinResponse> lostList(@Body LostData data);

}
