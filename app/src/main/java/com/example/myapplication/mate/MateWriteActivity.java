package com.example.myapplication.mate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Join.JoinActivity;
import com.example.myapplication.Join.JoinData;
import com.example.myapplication.Join.JoinResponse;
import com.example.myapplication.R;
import com.example.myapplication.login.LoginActivity;
import com.example.myapplication.network.RetrofitClient;
import com.example.myapplication.network.ServiceApi;
import com.example.myapplication.ui.HomeActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MateWriteActivity extends AppCompatActivity {
    private EditText titleText;
    private TextView nicknameText;
    private EditText contentText;
    private TextView cateText;
    private RadioGroup campusgroup;
    private int id;
    private RadioButton radioselect;
    private ProgressBar mProgressView;
    private ServiceApi service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mate_write);

        LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.activity_mypage, null);

        titleText = (EditText) findViewById(R.id.title);
        nicknameText = (TextView)view.findViewById(R.id.textNickname);
        contentText = (EditText) findViewById(R.id.content);
        campusgroup = (RadioGroup) findViewById(R.id.campusgroup);
        id = campusgroup.getCheckedRadioButtonId();
        radioselect = (RadioButton) findViewById(id);
        mProgressView = (ProgressBar) findViewById(R.id.progressBar2);

        service = RetrofitClient.getClient().create(ServiceApi.class);

        ImageButton loginButton = (ImageButton) findViewById(R.id.imageButton1);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        Button excuteButton = (Button) findViewById(R.id.excute);
        excuteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                attemptWrite();
            }
        });

        Spinner s = (Spinner)findViewById(R.id.spinner);
        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                cateText = (TextView)s.getSelectedView();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

    }

    private void attemptWrite() {
        titleText.setError(null);
        contentText.setError(null);
        cateText.setError(null);
        radioselect.setError(null);

        String title = titleText.getText().toString();
        String nickname = nicknameText.getText().toString();
        String content = contentText.getText().toString();
        String cate = cateText.getText().toString();
        String campus = radioselect.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // 패스워드의 유효성 검사
        if (title.isEmpty()) {
            titleText.setError("제목을 입력해주세요.");
            focusView = titleText;
            cancel = true;
        } else if (!isTitleValid(title)) {
            titleText.setError("6자 이상의 제목을 입력해주세요.");
            focusView = titleText;
            cancel = true;
        }

        // 이메일의 유효성 검사
        if (content.isEmpty()) {
            contentText.setError("내용을 입력해주세요.");
            focusView = contentText;
            cancel = true;
        } else if (!isContentValid(content)) {
            contentText.setError("10자 이상의 내용을 입력해주세요.");
            focusView = contentText;
            cancel = true;
        }


        // 닉네임의 유효성 검사
        if (cate.isEmpty()) {
            cateText.setError("카테고리를 선택해주세요.");
            focusView = cateText;
            cancel = true;
        }

        if (campus.isEmpty()) {
            radioselect.setError("캠퍼스를 선택해주세요.");
            focusView = radioselect;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            startMateWrite(new MateWriteData(title, nickname, content, cate, campus));
            showProgress(true);
        }
    }

    private void startMateWrite(MateWriteData data) {
        service.matewrite(data).enqueue(new Callback<MateWriteResponse>() {
            @Override
            public void onResponse(Call<MateWriteResponse> call, Response<MateWriteResponse> response) {
                MateWriteResponse result = response.body();
                Toast.makeText(MateWriteActivity.this, result.getMessage(), Toast.LENGTH_SHORT).show();
                showProgress(false);

                if (result.getCode() == 200) {
                    finish();
                }
            }

            @Override
            public void onFailure(Call<MateWriteResponse> call, Throwable t) {
                Toast.makeText(MateWriteActivity.this, "글 작성 에러 발생", Toast.LENGTH_SHORT).show();
                Log.e("글 작성 에러 발생", t.getMessage());
                showProgress(false);
            }
        });
    }

    private boolean isTitleValid(String title) {
        return title.length()>=6;
    }

    private boolean isContentValid(String content) {
        return content.length() >= 10;
    }

    private void showProgress(boolean show) {
        mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
    }
}
