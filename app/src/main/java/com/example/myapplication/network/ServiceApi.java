package com.example.myapplication.network;

import com.example.myapplication.Join.JoinData;
import com.example.myapplication.Join.JoinResponse;
import com.example.myapplication.login.LoginData;
import com.example.myapplication.login.LoginResponse;
import com.example.myapplication.lost.LostData;
import com.example.myapplication.lost.LostDeleteData;
import com.example.myapplication.lost.LostDeleteResponse;
import com.example.myapplication.lost.LostEditData;
import com.example.myapplication.lost.LostEditResponse;
import com.example.myapplication.lost.LostResponse;
import com.example.myapplication.lost.LostWriteData;
import com.example.myapplication.lost.LostWriteResponse;
import com.example.myapplication.mate.MateData;
import com.example.myapplication.mate.MateDeleteData;
import com.example.myapplication.mate.MateDeleteResponse;
import com.example.myapplication.mate.MateEditData;
import com.example.myapplication.mate.MateEditResponse;
import com.example.myapplication.mate.MateResponse;
import com.example.myapplication.mate.MateWriteData;
import com.example.myapplication.mate.MateWriteResponse;
import com.example.myapplication.ui.MypageData;
import com.example.myapplication.ui.MypageResponse;
import com.example.myapplication.ui.EditResponse;
import com.example.myapplication.ui.EditData;

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

    @POST("/member/edit")
    Call<EditResponse> userEdit(@Body EditData data);

    @POST("/mate/list")
    Call<MateResponse> matelist(@Body MateData data);

    @POST("/mate/write")
    Call<MateWriteResponse> matewrite(@Body MateWriteData data);

    @POST("/mate/edit")
    Call<MateEditResponse> mateedit(@Body MateEditData data);

    @POST("/mate/delete")
    Call<MateDeleteResponse> matedelete(@Body MateDeleteData data);

    @POST("/lost/list")
    Call<LostResponse> lostList(@Body LostData data);

    @POST("/lost/write")
    Call<LostWriteResponse> lostwrite(@Body LostWriteData data);

    @POST("/lost/edit")
    Call<LostEditResponse> lostedit(@Body LostEditData data);

    @POST("/lost/delte")
    Call<LostDeleteResponse> lostdelete(@Body LostDeleteData data);
}
