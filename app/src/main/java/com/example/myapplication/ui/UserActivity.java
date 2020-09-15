package com.example.myapplication.ui;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Join.JoinData;
import com.example.myapplication.login.LoginActivity;
import com.example.myapplication.login.LoginData;
import com.example.myapplication.login.LoginResponse;
import com.example.myapplication.ui.EditResponse;
import com.example.myapplication.ui.EditData;

import com.example.myapplication.R;
import com.example.myapplication.network.ServiceApi;
import com.example.myapplication.network.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import com.example.myapplication.R;

public class UserActivity extends AppCompatActivity {

    private TextView emailText;
    private EditText nicknameText;
    private TextView nameText;
    private EditText passwordText;
    private TextView genderText;
    private EditText passwordconfirmText;
    private ServiceApi service;
    private ProgressBar mProgressView;
    private Button editButton;
    private Button cancelButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        emailText = (TextView) findViewById(R.id.email);
        nicknameText = (EditText) findViewById(R.id.nickname);
        nameText = (TextView) findViewById(R.id.name);
        passwordText = (EditText) findViewById(R.id.password);
        passwordconfirmText = (EditText) findViewById(R.id.passwordconfirm);
        genderText = (TextView) findViewById(R.id.gender);
        editButton = (Button) findViewById(R.id.edit);
        cancelButton = (Button) findViewById(R.id.cancel);
        service = RetrofitClient.getClient().create(ServiceApi.class);
        mProgressView = (ProgressBar) findViewById(R.id.loading);
        nicknameText.setText(getIntent().getStringExtra("NICKNAME_EXTRA"));
        String nickname = nicknameText.getText().toString();
        startMypage(new MypageData(nickname));
        showProgress(true);


        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                attemptJoin();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cancelIntent = new Intent(UserActivity.this, HomeActivity.class);
                UserActivity.this.startActivity(cancelIntent);
            }
        });
    }
    private void attemptJoin() {
        nicknameText.setError(null);
        passwordText.setError(null);
        passwordconfirmText.setError(null);

        String nickname = nicknameText.getText().toString();
        String password = passwordText.getText().toString();
        String passwordconfirm = passwordconfirmText.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // 패스워드의 유효성 검사
        if (password.isEmpty()) {
            emailText.setError("비밀번호를 입력해주세요.");
            focusView = emailText;
            cancel = true;
        } else if (!isPasswordValid(password)) {
            passwordText.setError("비밀번호는 6자리 이상이여야 합니다.");
            focusView = passwordText;
            cancel = true;
        } else if (!passwordconfirm.equals(password)){
            passwordconfirmText.setError("비밀번호가 다릅니다. 다시 확인해주세요.");
            focusView = passwordconfirmText;
            cancel = true;
        }

        // 닉네임의 유효성 검사
        if (nickname.isEmpty()) {
            nicknameText.setError("닉네임을 입력해주세요.");
            focusView = nicknameText;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            /*update 들어갈자리, login 참고하기*/
            showProgress(true);
        }
    }


    private void startMypage(MypageData data) {
        service.userMypage(data).enqueue(new Callback<MypageResponse>() {
            @Override
            public void onResponse(Call<MypageResponse> call, Response<MypageResponse> response) {
                MypageResponse result = response.body();
                Toast.makeText(UserActivity.this, result.getMessage(), Toast.LENGTH_SHORT).show();
                showProgress(false);

                if (result.getCode() == 200) {
                    emailText.setText(result.getEmail());
                    nameText.setText(result.getName());
                    genderText.setText(result.getGender());
                    passwordText.setText(result.getPassword());
                }
            }

            @Override
            public void onFailure(Call<MypageResponse> call, Throwable t) {
                Toast.makeText(UserActivity.this, "내 정보 수정하기 ", Toast.LENGTH_SHORT).show();
                Log.e("내 정보 수정하기", t.getMessage());
                showProgress(false);
            }

        });
    }

    private void showProgress(boolean show) {
        mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
    }
    private boolean isPasswordValid(String password) {
        return password.length() >= 6;
    }
}

