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
        genderText = (TextView) findViewById(R.id.gender);
        editButton = (Button) findViewById(R.id.edit);
        cancelButton = (Button) findViewById(R.id.cancel);
        service = RetrofitClient.getClient().create(ServiceApi.class);
        mProgressView = (ProgressBar) findViewById(R.id.loading);
        nicknameText.setText(getIntent().getStringExtra("NICKNAME_EXTRA"));
        String nickname = nicknameText.getText().toString();
        editUser(new EditData(nickname));
        showProgress(true);


        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent editIntent = new Intent(UserActivity.this, HomeActivity.class);
                UserActivity.this.startActivity(editIntent);
            }
        });
        private void editUser(EditData data) {
            service.userEdit(data).enqueue(new Callback<MypageResponse>() {
                @Override
                public void onResponse(Call<MypageResponse> call, Response<MypageResponse> response) {
                    MypageResponse result = response.body();
                    Toast.makeText(UserActivity.this, result.getMessage(), Toast.LENGTH_SHORT).show();
                    showProgress(false);

                    if (result.getCode() == 200) {
                        emailText.setText(result.getEmail());
                        nameText.setText(result.getName());
                        genderText.setText(result.getGender());
                    }
                }

                @Override
                public void onFailure(Call<MypageResponse> call, Throwable t) {
                    Toast.makeText(UserActivity.this, "회원정보 수정 실패", Toast.LENGTH_SHORT).show();
                    Log.e("회원정보 수정 실패", t.getMessage());
                    showProgress(false);
                }

            });
        }

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent cancelIntent = new Intent(UserActivity.this, HomeActivity.class);
                UserActivity.this.startActivity(cancelIntent);
            }
        });
    }
    private void showProgress(boolean show) {
        mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
    }

}

