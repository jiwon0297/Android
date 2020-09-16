package com.example.myapplication.lost;

import androidx.annotation.IdRes;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.mate.AloneActivity;
import com.example.myapplication.mate.ContestActivity;
import com.example.myapplication.network.RetrofitClient;
import com.example.myapplication.network.ServiceApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LostEditActivity extends AppCompatActivity {
    private EditText titleText;
    private TextView nicknameText;
    private EditText contentText;
    private TextView typeText;
    private TextView campusText;
    private RadioGroup campusgroup;
    private RadioGroup typeGroup;
    private ProgressBar mProgressView;
    private ServiceApi service;
    private final String NICKNAME_EXTRA = "NICKNAME_EXTRA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lost_edit);

        titleText = (EditText) findViewById(R.id.title);
        titleText.setText(getIntent().getStringExtra("TITLE_EXTRA"));
        contentText = (EditText) findViewById(R.id.content);
        contentText.setText(getIntent().getStringExtra("CONTENT_EXTRA"));
        campusgroup = (RadioGroup) findViewById(R.id.campusgroup);
        campusgroup.setOnCheckedChangeListener(radioGroupButtonChangeListener);
        campusText = (TextView) findViewById(R.id.campus);
        campusText.setText(getIntent().getStringExtra("CAMPUS_EXTRA"));
        typeGroup = (RadioGroup) findViewById(R.id.typeGroup);
        typeGroup.setOnCheckedChangeListener(radioGroupButtonChangeListener);
        typeText = (TextView) findViewById(R.id.type);
        typeText.setText(getIntent().getStringExtra("TYPE_EXTRA"));
        mProgressView = (ProgressBar) findViewById(R.id.progressBar2);

        service = RetrofitClient.getClient().create(ServiceApi.class);

        ImageButton backbutton = (ImageButton) findViewById(R.id.imageButton1);
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        Button excuteButton = (Button) findViewById(R.id.excute);
        excuteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                attemptEdit();
            }
        });

    }

    RadioGroup.OnCheckedChangeListener radioGroupButtonChangeListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
            if(i == R.id.campusjj){
                campusText.setText("죽전캠");
            } else if(i == R.id.campusca){
                campusText.setText("천안캠");
            } else if(i == R.id.radio0){
                typeText.setText("찾아요");
            } else if(i == R.id.radio1){
                typeText.setText("주웠어요");
            }
        }
    };
    private void attemptEdit() {
        titleText.setError(null);
        contentText.setError(null);
        typeText.setError(null);
        campusText.setError(null);

        String title = titleText.getText().toString();
        String content = contentText.getText().toString();
        String campus = campusText.getText().toString();
        String type = typeText.getText().toString();
        int number = getIntent().getIntExtra("NUMBER_EXTRA",1);

        boolean cancel = false;
        View focusView = null;

        if (title.isEmpty()) {
            titleText.setError("제목을 입력해주세요.");
            focusView = titleText;
            cancel = true;
        } else if (!isTitleValid(title)) {
            titleText.setError("6자 이상의 제목을 입력해주세요.");
            focusView = titleText;
            cancel = true;
        }

        if (content.isEmpty()) {
            contentText.setError("내용을 입력해주세요.");
            focusView = contentText;
            cancel = true;
        } else if (!isContentValid(content)) {
            contentText.setError("10자 이상의 내용을 입력해주세요.");
            focusView = contentText;
            cancel = true;
        }

        if (campus.isEmpty()) {
            campusText.setError("캠퍼스를 선택해주세요.");
            focusView = campusgroup;
            cancel = true;
        }

        if (type.isEmpty()) {
            typeText.setError("분실물 유형을 선택해주세요.");
            focusView = typeGroup;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            staratLostEdit(new LostEditData(title, number, campus, type, content));
            showProgress(true);
        }
    }

    private void staratLostEdit(LostEditData data) {
        service.lostedit(data).enqueue(new Callback<LostEditResponse>(){
            @Override
            public void onResponse(Call<LostEditResponse> call, Response<LostEditResponse> response) {
                LostEditResponse result = response.body();
                Toast.makeText(LostEditActivity.this, result.getMessage(), Toast.LENGTH_SHORT).show();
                showProgress(false);

                if (result.getCode() == 200) {
                    if(typeText.getText().toString() == "찾아요"){
                        Intent intent = new Intent(LostEditActivity.this, AloneActivity.class);
                        intent.putExtra(NICKNAME_EXTRA, getIntent().getStringExtra("NICKNAME_EXTRA"));
                        LostEditActivity.this.startActivity(intent);
                    } else if(typeText.getText().toString() == "주웠어요"){
                        Intent intent = new Intent(LostEditActivity.this, ContestActivity.class);
                        intent.putExtra(NICKNAME_EXTRA, getIntent().getStringExtra("NICKNAME_EXTRA"));
                        LostEditActivity.this.startActivity(intent);
                    }
                }
            }

            @Override
            public void onFailure(Call<LostEditResponse> call, Throwable t) {
                Toast.makeText(LostEditActivity.this, "글 작성 에러 발생", Toast.LENGTH_SHORT).show();
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