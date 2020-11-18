package com.example.myapplication.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.login.LoginActivity;
import com.example.myapplication.mate.AloneActivity;
import com.example.myapplication.mate.MateActivity;
import com.example.myapplication.network.ServiceApi;
import com.example.myapplication.network.RetrofitClient;
import com.example.myapplication.ui.MypageActivity;
import com.example.myapplication.ui.MypageResponse;
import com.example.myapplication.ui.MypageData;
import com.google.android.material.bottomnavigation.BottomNavigationView;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MypageActivity extends AppCompatActivity {
    private final String NICKNAME_EXTRA = "NICKNAME_EXTRA";
    private TextView nicknameText;
    private TextView emailText;
    private TextView nameText;
    private TextView genderText;
    private ServiceApi service;
    private ProgressBar mProgressView;
    ImageView ImageView_woman = null;
    ImageView ImageView_man = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavi);
        bottomNavigationView.setOnNavigationItemSelectedListener(new MypageActivity.ItemSelectedListener());
        emailText = (TextView) findViewById(R.id.email);
        nicknameText = (TextView) findViewById(R.id.nickname);
        nameText = (TextView) findViewById(R.id.name);
        genderText = (TextView) findViewById(R.id.gender);
        service = RetrofitClient.getClient().create(ServiceApi.class);
        mProgressView = (ProgressBar) findViewById(R.id.loading);
        nicknameText.setText(getIntent().getStringExtra("NICKNAME_EXTRA"));
        ImageView_woman = (ImageView)  findViewById(R.id.genderImage_woman);
        ImageView_man = (ImageView)  findViewById(R.id.genderImage_man);
        String nickname = nicknameText.getText().toString();
        startMypage(new MypageData(nickname));
        showProgress(true);

    }
    class ItemSelectedListener implements BottomNavigationView.OnNavigationItemSelectedListener{
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

            switch(menuItem.getItemId())
            {
                case R.id.home:
                    Intent intent = new Intent(MypageActivity.this, HomeActivity.class);
                    intent.putExtra(NICKNAME_EXTRA, getIntent().getStringExtra("NICKNAME_EXTRA"));
                    startActivity(intent);
                    break;
                case R.id.mail:
                    Intent intent2 = new Intent(MypageActivity.this, MailActivity.class);
                    intent2.putExtra(NICKNAME_EXTRA, getIntent().getStringExtra("NICKNAME_EXTRA"));
                    startActivity(intent2);
                    break;
                case R.id.mypage:
                    Intent intent3 = new Intent(MypageActivity.this, MypageActivity.class);
                    intent3.putExtra(NICKNAME_EXTRA, getIntent().getStringExtra("NICKNAME_EXTRA"));
                    startActivity(intent3);
                    break;
            }
            return true;
        }
    }
    private void startMypage(MypageData data) {
        service.userMypage(data).enqueue(new Callback<MypageResponse>() {
            @Override
            public void onResponse(Call<MypageResponse> call, Response<MypageResponse> response) {
                MypageResponse result = response.body();
                Toast.makeText(MypageActivity.this, result.getMessage(), Toast.LENGTH_SHORT).show();
                showProgress(false);

                if (result.getCode() == 200) {
                    emailText.setText(result.getEmail());
                    nameText.setText(result.getName());
                    genderText.setText(result.getGender());
                    String gender = genderText.getText().toString();
                    System.out.println(gender);
                    if (gender.equals("여성")) {
                        ImageView_woman.setVisibility(View.VISIBLE);
                        ImageView_man.setVisibility(View.INVISIBLE);
                    }else{
                        ImageView_woman.setVisibility(View.INVISIBLE);
                        ImageView_man.setVisibility(View.VISIBLE);
                    }
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

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        Intent intent = new Intent(MypageActivity.this, HomeActivity.class);
        intent.putExtra(NICKNAME_EXTRA, getIntent().getStringExtra("NICKNAME_EXTRA"));
        startActivity(intent);
    }



    public void content(View v) {
        Toast.makeText(this,"내 목록 화면으로 이동합니다.",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, ContentActivity.class);
        intent.putExtra(NICKNAME_EXTRA, getIntent().getStringExtra("NICKNAME_EXTRA"));
        startActivity(intent);
    }

    public void logout(View v) {
        Intent intent = new Intent(MypageActivity.this, LoginActivity.class);
        startActivity(intent);
        SharedPreferences setting = getSharedPreferences("setting", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = setting.edit();
        editor.clear();
        editor.commit();
        finish();
        Toast.makeText(this,"로그아웃 되었습니다.",Toast.LENGTH_LONG).show();
        startActivity(intent);
    }
    public void user(View v) {
        Toast.makeText(this,"정보 수정 화면으로 이동합니다.",Toast.LENGTH_LONG).show();
        Intent intent3 = new Intent(this, UserActivity.class);
        intent3.putExtra(NICKNAME_EXTRA, getIntent().getStringExtra("NICKNAME_EXTRA"));
        startActivity(intent3);
    }

    private void showProgress(boolean show) {
        mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
    }

}
