package com.example.myapplication.network;

import com.example.myapplication.Join.JoinData;
import com.example.myapplication.Join.JoinResponse;
import com.example.myapplication.login.LoginData;
import com.example.myapplication.login.LoginResponse;
import com.example.myapplication.lost.LostData;
import com.example.myapplication.lost.LostResponse;
import com.example.myapplication.lost.LostWriteData;
import com.example.myapplication.lost.LostWriteResponse;
import com.example.myapplication.mate.MateData;
import com.example.myapplication.mate.MateResponse;
import com.example.myapplication.mate.MateWriteData;
import com.example.myapplication.mate.MateWriteResponse;
import com.example.myapplication.ui.MypageData;
import com.example.myapplication.ui.MypageResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ServiceApi {
    @POST("/member/login")
    Call<LoginResponse> userLogin(@Body LoginData data);

    @POST("/member/join")
    Call<JoinResponse> userJoin(@Body JoinData data);

    @POST("/member/mypage")
    Call<MypageResponse> userMypage(@Body MypageData data);

    @POST("/mate/list")
    Call<MateResponse> matelist(@Body MateData data);

    @POST("/mate/write")
    Call<MateWriteResponse> matewrite(@Body MateWriteData data);

    @POST("/lost/list")
    Call<LostResponse> lostList(@Body LostData data);

    @POST("/lost/write")
    Call<LostWriteResponse> lostwrite(@Body LostWriteData data);
}
