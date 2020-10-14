package com.example.myapplication.network;

import com.example.myapplication.Join.CheckEmailData;
import com.example.myapplication.Join.CheckEmailResponse;
import com.example.myapplication.Join.CheckNicknameData;
import com.example.myapplication.Join.CheckNicknameResponse;
import com.example.myapplication.Join.JoinData;
import com.example.myapplication.Join.JoinResponse;
import com.example.myapplication.login.LoginData;
import com.example.myapplication.login.LoginResponse;
import com.example.myapplication.lost.LostCommentData;
import com.example.myapplication.lost.LostCommentDeleteData;
import com.example.myapplication.lost.LostCommentDeleteResponse;
import com.example.myapplication.lost.LostCommentResponse;
import com.example.myapplication.lost.LostCommentWriteData;
import com.example.myapplication.lost.LostCommentWriteResponse;
import com.example.myapplication.lost.LostData;
import com.example.myapplication.lost.LostDeleteData;
import com.example.myapplication.lost.LostDeleteResponse;
import com.example.myapplication.lost.LostEditData;
import com.example.myapplication.lost.LostEditResponse;
import com.example.myapplication.lost.LostResponse;
import com.example.myapplication.lost.LostWriteData;
import com.example.myapplication.lost.LostWriteResponse;
import com.example.myapplication.mate.MateCommentData;
import com.example.myapplication.mate.MateCommentDeleteData;
import com.example.myapplication.mate.MateCommentDeleteResponse;
import com.example.myapplication.mate.MateCommentEditData;
import com.example.myapplication.mate.MateCommentEditResponse;
import com.example.myapplication.mate.MateCommentResponse;
import com.example.myapplication.mate.MateCommentWriteData;
import com.example.myapplication.mate.MateCommentWriteResponse;
import com.example.myapplication.mate.MateData;
import com.example.myapplication.mate.MateDeleteData;
import com.example.myapplication.mate.MateDeleteResponse;
import com.example.myapplication.mate.MateEditData;
import com.example.myapplication.mate.MateEditResponse;
import com.example.myapplication.mate.MateResponse;
import com.example.myapplication.mate.MateWriteData;
import com.example.myapplication.mate.MateWriteResponse;
import com.example.myapplication.ui.EditData;
import com.example.myapplication.ui.EditResponse;
import com.example.myapplication.ui.MyLostData;
import com.example.myapplication.ui.MyLostResponse;
import com.example.myapplication.ui.MyMateData;
import com.example.myapplication.ui.MyMateResponse;
import com.example.myapplication.ui.MypageData;
import com.example.myapplication.ui.MypageResponse;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ServiceApi {
    @POST("/member/login")
    Call<LoginResponse> userLogin(@Body LoginData data);

    @POST("/member/join")
    Call<JoinResponse> userJoin(@Body JoinData data);

    @POST("/member/mypage")
    Call<MypageResponse> userMypage(@Body MypageData data);

    @POST("/member/edit")
    Call<EditResponse> userEdit(@Body EditData data);

    @POST("/member/matelist")
    Call<MyMateResponse> userMatelist(@Body MyMateData data);

    @POST("/member/lostlist")
    Call<MyLostResponse> userLostlist(@Body MyLostData data);

    @POST("/mate/list")
    Call<MateResponse> matelist(@Body MateData data);

    @POST("/mate/write")
    Call<MateWriteResponse> matewrite(@Body MateWriteData data);

    @POST("/mate/edit")
    Call<MateEditResponse> mateedit(@Body MateEditData data);

    @POST("/mate/delete")
    Call<MateDeleteResponse> matedelete(@Body MateDeleteData data);

    @POST("/mate/commentlist")
    Call<MateCommentResponse> matecommentlist(@Body MateCommentData data);

    @POST("/mate/commentwrite")
    Call<MateCommentWriteResponse> matecommentwrite(@Body MateCommentWriteData data);

    @POST("/mate/commentedit")
    Call<MateCommentEditResponse> matecommentedit(@Body MateCommentEditData data);

    @POST("/mate/commentdelete")
    Call<MateCommentDeleteResponse> matecommentdelete(@Body MateCommentDeleteData data);

    @POST("/lost/list")
    Call<LostResponse> lostList(@Body LostData data);

    @POST("/lost/write")
    Call<LostWriteResponse> lostwrite(@Body LostWriteData data);

    @POST("/lost/edit")
    Call<LostEditResponse> lostedit(@Body LostEditData data);

    @POST("/lost/delete")
    Call<LostDeleteResponse> lostdelete(@Body LostDeleteData data);

    @POST("/check/checkEmail")
    Call<CheckEmailResponse> checkEmail(@Body CheckEmailData data);
    
    @POST("/check/checkNickname")
    Call<CheckNicknameResponse> checkNickname(@Body CheckNicknameData data);

    @POST("/lost/commentlist")
    Call<LostCommentResponse> lostcommentlist(@Body LostCommentData data);

    @POST("/lost/commentwrite")
    Call<LostCommentWriteResponse> lostcommentwrite(@Body LostCommentWriteData data);

    @POST("/lost/commentdelete")
    Call<LostCommentDeleteResponse> lostcommentdelete(@Body LostCommentDeleteData data);

    @Multipart
    @POST("/upload")
    Call<ResponseBody> upload(@Part MultipartBody.Part file, @Part("name") RequestBody description);
}
