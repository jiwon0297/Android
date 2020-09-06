package com.example.myapplication.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.myapplication.Join.JoinActivity;
import com.example.myapplication.ui.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.network.RetrofitClient;
import com.example.myapplication.network.ServiceApi;
import com.example.myapplication.login.LoginData;
import com.example.myapplication.login.LoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private EditText emailText;
    private EditText passwordText;
    private ServiceApi service;
    private ProgressBar mProgressView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailText = (EditText) findViewById(R.id.username);
        passwordText = (EditText) findViewById(R.id.password);

        Button loginButton = (Button) findViewById(R.id.signin);
        Button registerButton = (Button) findViewById(R.id.register);
        Button cancelButton = (Button) findViewById(R.id.cancel);

        service = RetrofitClient.getClient().create(ServiceApi.class);

        //loginButton.setOnClickListener(new View.OnClickListener() {
            //@Override
            //public void onClick(View v){
                //Intent loginIntent = new Intent(LoginActivity.this, HomeActivity.class);
                //LoginActivity.this.startActivity(loginIntent);
            //}
        //});
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptLogin();
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent registerIntent = new Intent(LoginActivity.this, JoinActivity.class);
                LoginActivity.this.startActivity(registerIntent);
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent cancelIntent = new Intent(LoginActivity.this, MainActivity.class);
                LoginActivity.this.startActivity(cancelIntent);
            }
        });
    }

    private void attemptLogin() {
        emailText.setError(null);
        passwordText.setError(null);

        String email = emailText.getText().toString();
        String password = passwordText.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // 패스워드의 유효성 검사
        if (password.isEmpty()) {
            passwordText.setError("비밀번호를 입력해주세요.");
            focusView = passwordText;
            cancel = true;
        } else if (!isPasswordValid(password)) {
            passwordText.setError("6자 이상의 비밀번호를 입력해주세요.");
            focusView = passwordText;
            cancel = true;
        }

        // 이메일의 유효성 검사
        if (email.isEmpty()) {
            emailText.setError("이메일을 입력해주세요.");
            focusView = emailText;
            cancel = true;
        } else if (!isEmailValid(email)) {
            emailText.setError("@를 포함한 유효한 이메일을 입력해주세요.");
            focusView = emailText;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            startLogin(new LoginData(email, password));
            showProgress(true);
        }
    }

    private void startLogin(LoginData data) {
        service.userLogin(data).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                LoginResponse result = response.body();
                Toast.makeText(LoginActivity.this, result.getMessage(), Toast.LENGTH_SHORT).show();
                showProgress(false);
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "로그인 에러 발생", Toast.LENGTH_SHORT).show();
                Log.e("로그인 에러 발생", t.getMessage());
                showProgress(false);
            }
        });
    }

    private boolean isEmailValid(String email) {
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        return password.length() >= 6;
    }

    private void showProgress(boolean show) {
        mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
    }
}
