package com.example.myapplication.Join;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.IdRes;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.login.LoginActivity;
import com.example.myapplication.network.RetrofitClient;
import com.example.myapplication.network.ServiceApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JoinActivity extends AppCompatActivity {
    Button mailButton = null;
    EditText emailText = null;
    private EditText passwordText;
    private EditText passwordconfirmText;
    private EditText nameText;
    private TextView genderText;
    private EditText nicknameText;
    private RadioGroup genderRadio;
    private Button registerButton;
    private Button cancelButton;
    private Button emailCkButton;
    private Button nicknameCkButton;
    private ProgressBar mProgressView;
    private ServiceApi service;
    private boolean emailValidate = false;
    private boolean nicknameValidate = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .permitDiskReads()
                .permitDiskWrites()
                .permitNetwork().build());

        mailButton = (Button) findViewById(R.id.mailButton);
        emailText = (EditText) findViewById(R.id.username);
        passwordText = (EditText) findViewById(R.id.password);
        passwordconfirmText = (EditText) findViewById(R.id.passwordconfirm);
        nameText = (EditText) findViewById(R.id.name);
        genderText = (TextView) findViewById(R.id.genderText);
        nicknameText = (EditText) findViewById(R.id.nickname);
        genderRadio = (RadioGroup) findViewById(R.id.genderGroup);
        genderRadio.setOnCheckedChangeListener(radioGroupButtonChangeListener);
        registerButton = (Button) findViewById(R.id.join);
        cancelButton = (Button) findViewById(R.id.cancel);
        emailCkButton = (Button) findViewById(R.id.emailCheck);
        nicknameCkButton = (Button) findViewById(R.id.nicknameCheck);
        mProgressView = (ProgressBar) findViewById(R.id.loading);


        service = RetrofitClient.getClient().create(ServiceApi.class);

        mailButton.setOnClickListener(new View.OnClickListener() {
            String email = emailText.getText().toString();
            @Override
            public void onClick(View v){
                if (email.isEmpty()) {
                emailText.setError("이메일을 입력해주세요.");
            }
                SendMail mailServer = new SendMail();
                mailServer.sendSecurityCode(getApplicationContext(), emailText.getText().toString());
            }
        });



        emailCkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(emailValidate){
                    return;
                }
                emailText.setError(null);
                String email = emailText.getText().toString();
                boolean cancel = false;
                View focusView = null;

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
                if (cancel) {
                    focusView.requestFocus();
                } else {
                    startCheckEmail(new CheckEmailData(email));
                    showProgress(true);
                }

            }
        });

        nicknameCkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nicknameText.setError(null);
                String nickname = nicknameText.getText().toString();

                boolean cancel = false;
                View focusView = null;

                if(nicknameValidate){
                    return;
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
                    startCheckNickname(new CheckNicknameData(nickname));
                    showProgress(true);
                }

            }
        });

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

    RadioGroup.OnCheckedChangeListener radioGroupButtonChangeListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
            if(i == R.id.genderWoman){
                genderText.setText("여성");
            } else if(i == R.id.genderMan){
                genderText.setText("남성");
            }
        }
    };

    private void attemptJoin() {
        emailText.setError(null);
        passwordText.setError(null);
        passwordconfirmText.setError(null);
        nameText.setError(null);
        genderText.setError(null);

        String name = nameText.getText().toString();
        String email = emailText.getText().toString();
        String password = passwordText.getText().toString();
        String passwordconfirm = passwordconfirmText.getText().toString();
        String nickname = nicknameText.getText().toString();
        String gender = genderText.getText().toString();

        boolean cancel = false;
        View focusView = null;

        if (emailValidate == false) {
            emailText.setError("이메일 중복확인을 해주세요.");
            focusView = emailText;
            cancel = true;
        }
        if (nicknameValidate == false) {
            nicknameText.setError("닉네임 중복확인을 해주세요.");
            focusView = nicknameText;
            cancel = true;
        }

        // 패스워드의 유효성 검사
        if (password.isEmpty()) {
            emailText.setError("비밀번호를 입력해주세요.");
            focusView = passwordText;
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

        // 이름의 유효성 검사
        if (name.isEmpty()) {
            nameText.setError("이름을 입력해주세요.");
            focusView = nameText;
            cancel = true;
        }

        if (gender.isEmpty()) {
            genderText.setError("성별을 선택해주세요.");
            focusView = genderRadio;
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

    private void startCheckEmail(CheckEmailData data) {
        service.checkEmail(data).enqueue(new Callback<CheckEmailResponse>() {
            @Override
            public void onResponse(Call<CheckEmailResponse> call, Response<CheckEmailResponse> response) {
                CheckEmailResponse result = response.body();
                Toast.makeText(JoinActivity.this, result.getMessage(), Toast.LENGTH_SHORT).show();
                showProgress(false);

                if (result.getCode() == 200) {
                    emailValidate = true;
                    emailCkButton.setText("확인완료");
                }
                else {
                    emailValidate = false;
                }
            }

            @Override
            public void onFailure(Call<CheckEmailResponse> call, Throwable t) {
                Toast.makeText(JoinActivity.this, "이메일 중복 확인 에러 발생", Toast.LENGTH_SHORT).show();
                Log.e("이메일 중복 확인 에러 발생", t.getMessage());
                showProgress(false);
            }

        });
    }

    private void startCheckNickname(CheckNicknameData data) {
        service.checkNickname(data).enqueue(new Callback<CheckNicknameResponse>() {
            @Override
            public void onResponse(Call<CheckNicknameResponse> call, Response<CheckNicknameResponse> response) {
                CheckNicknameResponse result = response.body();
                Toast.makeText(JoinActivity.this, result.getMessage(), Toast.LENGTH_SHORT).show();
                showProgress(false);

                if (result.getCode() == 200) {
                    nicknameValidate = true;
                    nicknameCkButton.setText("확인완료");
                }
                else {
                    nicknameValidate = false;
                }
            }

            @Override
            public void onFailure(Call<CheckNicknameResponse> call, Throwable t) {
                Toast.makeText(JoinActivity.this, "닉네임 중복 확인 에러 발생", Toast.LENGTH_SHORT).show();
                Log.e("닉네임 중복 확인 에러 발생", t.getMessage());
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
