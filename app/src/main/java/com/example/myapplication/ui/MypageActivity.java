package com.example.myapplication.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.login.LoginData;
import com.example.myapplication.network.ServiceApi;
import com.example.myapplication.network.RetrofitClient;
import com.example.myapplication.network.ServiceApi;
import com.example.myapplication.ui.MypageActivity;
import com.example.myapplication.ui.MypageResponse;
import com.example.myapplication.ui.MypageData;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MypageActivity extends AppCompatActivity {
    private TextView nicknameText;
    private TextView emailText;
    private TextView nameText;
    private TextView genderText;
    private ServiceApi service;
    private ProgressBar mProgressView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage);
        emailText = (TextView) findViewById(R.id.email);
        nicknameText = (TextView) findViewById(R.id.nickname);
        nameText = (TextView) findViewById(R.id.name);
        genderText = (TextView) findViewById(R.id.gender);
        service = RetrofitClient.getClient().create(ServiceApi.class);
        mProgressView = (ProgressBar) findViewById(R.id.loading);
        String gender = nicknameText.getText().toString();
        String email = emailText.getText().toString();
        startMypage(new MypageData(email));
        showProgress(true);

    }
    private void startMypage(MypageData data) {
        service.userMypage(data).enqueue(new Callback<MypageResponse>() {
            @Override
            public void onResponse(Call<MypageResponse> call, Response<MypageResponse> response) {
                MypageResponse result = response.body();
                Toast.makeText(MypageActivity.this, result.getMessage(), Toast.LENGTH_SHORT).show();
                showProgress(false);

                if (result.getCode() == 200) {
                    Intent loginIntent = new Intent(MypageActivity.this, HomeActivity.class);
                    MypageActivity.this.startActivity(loginIntent);
                }
            }

            @Override
            public void onFailure(Call<MypageResponse> call, Throwable t) {
                Toast.makeText(MypageActivity.this, "내 정보 불러오기 실패", Toast.LENGTH_SHORT).show();
                Log.e("내 정보 불러오기 실패", t.getMessage());
                showProgress(false);
            }

        });
        
    }



    public void content(View v) {
        Toast.makeText(this,"내 목록 화면으로 이동합니다.",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, ContentActivity.class);
        startActivity(intent);
    }

    public void logout(View v) {
        Toast.makeText(this,"로그아웃 되어 홈화면으로 이동합니다.",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void user(View v) {
        Toast.makeText(this,"정보 수정 화면으로 이동합니다.",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, UserActivity.class);
        startActivity(intent);
    }

    private void showProgress(boolean show) {
        mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
    }

}
