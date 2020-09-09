package com.example.myapplication.lost;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
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

import com.example.myapplication.R;
import com.example.myapplication.mate.MateWriteActivity;
import com.example.myapplication.mate.MateWriteResponse;
import com.example.myapplication.network.RetrofitClient;
import com.example.myapplication.network.ServiceApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LostWriteActivity extends AppCompatActivity {
    private EditText titleText;
    private TextView nicknameText;
    private EditText contentText;
    private TextView typeText;
    private RadioGroup campusgroup;
    private RadioGroup typeGroup;
    private int id1;
    private int id2;
    private RadioButton radioselect1;
    private RadioButton radioselect2;
    private ProgressBar mProgressView;
    private ServiceApi service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lost_write);

        titleText = (EditText) findViewById(R.id.title);
        nicknameText = (TextView) findViewById(R.id.nickname);
        nicknameText.setText(getIntent().getStringExtra("NICKNAME_EXTRA"));
        contentText = (EditText) findViewById(R.id.content);
        campusgroup = (RadioGroup) findViewById(R.id.campusgroup);
        typeGroup = (RadioGroup) findViewById(R.id.typeGroup);
        id1 = campusgroup.getCheckedRadioButtonId();
        id2 = typeGroup.getCheckedRadioButtonId();
        radioselect1 = (RadioButton) findViewById(id1);
        radioselect2 = (RadioButton) findViewById(id2);
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
                typeText = (TextView)s.getSelectedView();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

    private void attemptWrite() {
        titleText.setError(null);
        contentText.setError(null);
        radioselect1.setError(null);
        radioselect2.setError(null);

        String title = titleText.getText().toString();
        String nickname = nicknameText.getText().toString();
        String content = contentText.getText().toString();
        String campus = radioselect1.getText().toString();
        String type = radioselect2.getText().toString();

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
            radioselect1.setError("캠퍼스를 선택해주세요.");
            focusView = radioselect1;
            cancel = true;
        }

        if (campus.isEmpty()) {
            radioselect2.setError("카테고리를 선택해주세요.");
            focusView = radioselect2;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            startLostWrite(new LostWriteData(4, title, nickname, content, type, campus));
            showProgress(true);
        }
    }

    private void startLostWrite(LostWriteData data) {
        service.lostwrite(data).enqueue(new Callback<LostWriteResponse>(){
            @Override
            public void onResponse(Call<LostWriteResponse> call, Response<LostWriteResponse> response) {
                LostWriteResponse result = response.body();
                Toast.makeText(LostWriteActivity.this, result.getMessage(), Toast.LENGTH_SHORT).show();
                showProgress(false);

                if (result.getCode() == 200) {

                }
            }

            @Override
            public void onFailure(Call<LostWriteResponse> call, Throwable t) {
                Toast.makeText(LostWriteActivity.this, "글 작성 에러 발생", Toast.LENGTH_SHORT).show();
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
