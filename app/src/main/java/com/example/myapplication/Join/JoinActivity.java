package com.example.myapplication.Join;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.login.LoginActivity;
import com.example.myapplication.network.RetrofitClient;
import com.example.myapplication.network.ServiceApi;
import com.example.myapplication.Join.JoinData;
import com.example.myapplication.Join.JoinResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JoinActivity extends AppCompatActivity {
    private EditText emailText;
    private EditText passwordText;
    private EditText passwordconfirmText;
    private EditText nameText;
    private EditText nicknameText;
    private RadioGroup genderRadio;
    private int id;
    private Button registerButton;
    private Button cancelButton;
    private RadioButton radioselect;
    private ProgressBar mProgressView;
    private ServiceApi service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        emailText = (EditText) findViewById(R.id.username);
        passwordText = (EditText) findViewById(R.id.password);
        passwordconfirmText = (EditText) findViewById(R.id.passwordconfirm);
        nameText = (EditText) findViewById(R.id.name);
        nicknameText = (EditText) findViewById(R.id.nickname);
        genderRadio = (RadioGroup) findViewById(R.id.genderGroup);
        id = genderRadio.getCheckedRadioButtonId();
        radioselect = (RadioButton) findViewById(id);
        registerButton = (Button) findViewById(R.id.join);
        cancelButton = (Button) findViewById(R.id.cancel);
        mProgressView = (ProgressBar) findViewById(R.id.loading);

        service = RetrofitClient.getClient().create(ServiceApi.class);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                attemptJoin();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent cancelIntent = new Intent(JoinActivity.this, LoginActivity.class);
                JoinActivity.this.startActivity(cancelIntent);
            }
        });
    }

    private void attemptJoin() {
        emailText.setError(null);
        passwordText.setError(null);
        passwordconfirmText.setError(null);
        nameText.setError(null);
        nicknameText.setError(null);
        radioselect.setError(null);

        String name = nameText.getText().toString();
        String email = emailText.getText().toString();
        String password = passwordText.getText().toString();
        String passwordconfirm = passwordconfirmText.getText().toString();
        String nickname = nicknameText.getText().toString();
        String gender = radioselect.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // 패스워드의 유효성 검사
        if (password.isEmpty()) {
            emailText.setError("비밀번호를 입력해주세요.");
            focusView = emailText;
            cancel = true;
        } else if (!isPasswordValid(password)) {
            passwordText.setError("6자 이상의 비밀번호를 입력해주세요.");
            focusView = passwordText;
            cancel = true;
        } else if (!passwordconfirm.equals(password)){
            passwordconfirmText.setError("비밀번호가 다릅니다. 다시 확인해주세요.");
            focusView = passwordconfirmText;
            cancel = true;
        }

        // 이메일의 유효성 검사
        if (email.isEmpty()) {
            emailText.setError("이메일을 입력해주세요.");
            focusView = emailText;
            cancel = true;
        } else if (!isEmailValid(email)) {
            emailText.setError("단국대 이메일을 입력해주세요.");
            focusView = emailText;
            cancel = true;
        }

        // 이름의 유효성 검사
        if (name.isEmpty()) {
            nameText.setError("이름을 입력해주세요.");
            focusView = nameText;
            cancel = true;
        }

        // 닉네임의 유효성 검사
        if (nickname.isEmpty()) {
            nicknameText.setError("닉네임을 입력해주세요.");
            focusView = nicknameText;
            cancel = true;
        }

        if (gender.isEmpty()) {
            radioselect.setError("성별을 선택해주세요.");
            focusView = radioselect;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            startJoin(new JoinData(email, password, name, nickname, gender));
            showProgress(true);
        }
    }

    private void startJoin(JoinData data) {
        service.userJoin(data).enqueue(new Callback<JoinResponse>() {
            @Override
            public void onResponse(Call<JoinResponse> call, Response<JoinResponse> response) {
                JoinResponse result = response.body();
                Toast.makeText(JoinActivity.this, result.getMessage(), Toast.LENGTH_SHORT).show();
                showProgress(false);

                if (result.getCode() == 200) {
                    finish();
                }
            }

            @Override
            public void onFailure(Call<JoinResponse> call, Throwable t) {
                Toast.makeText(JoinActivity.this, "회원가입 에러 발생", Toast.LENGTH_SHORT).show();
                Log.e("회원가입 에러 발생", t.getMessage());
                showProgress(false);
            }
        });
    }

    private boolean isEmailValid(String email) {
        return email.contains("@dankook.ac.kr");
    }

    private boolean isPasswordValid(String password) {
        return password.length() >= 6;
    }

    private void showProgress(boolean show) {
        mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
    }
}
