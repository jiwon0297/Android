package com.example.myapplication.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.mate.AloneActivity;
import com.example.myapplication.mate.ContestActivity;
import com.example.myapplication.mate.HouseActivity;
import com.example.myapplication.mate.MateWriteActivity;
import com.example.myapplication.mate.MateWriteData;
import com.example.myapplication.mate.MateWriteResponse;
import com.example.myapplication.mate.StudyActivity;
import com.example.myapplication.network.RetrofitClient;
import com.example.myapplication.network.ServiceApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SendMessageActivity extends AppCompatActivity {
    private EditText ContentText;
    private TextView RecipText;
    private ProgressBar mProgressView;
    private ServiceApi service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_message);

        String recipient = getIntent().getStringExtra("RECIPIENT");

        RecipText = (TextView) findViewById(R.id.recipient);
        ContentText = (EditText) findViewById(R.id.content);
        mProgressView = (ProgressBar) findViewById(R.id.progressBar2);

        service = RetrofitClient.getClient().create(ServiceApi.class);

        RecipText.setText(recipient);

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
                attemptSend();
            }
        });
    }

    private void attemptSend() {
        ContentText.setError(null);

        String sender = getIntent().getStringExtra("SENDER");
        String content = ContentText.getText().toString();
        String recipient = getIntent().getStringExtra("RECIPIENT");

        boolean cancel = false;
        View focusView = null;

        // 패스워드의 유효성 검사
        if (content.isEmpty()) {
            ContentText.setError("내용을 입력해주세요.");
            focusView = ContentText;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            startMailSend(new MailWriteData(sender,recipient,content));
            showProgress(true);
        }
    }

    private void startMailSend(MailWriteData data) {
        service.mailwrite(data).enqueue(new Callback<MailWriteResponse>() {
            @Override
            public void onResponse(Call<MailWriteResponse> call, Response<MailWriteResponse> response) {
                MailWriteResponse result = response.body();
                Toast.makeText(SendMessageActivity.this, result.getMessage(), Toast.LENGTH_SHORT).show();
                showProgress(false);

                if(result.getCode() == 200){
                    Intent intent = new Intent(SendMessageActivity.this, MailActivity.class);
                    intent.putExtra("NICKNAME_EXTRA", getIntent().getStringExtra("SENDER"));
                    SendMessageActivity.this.startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<MailWriteResponse> call, Throwable t) {
                Toast.makeText(SendMessageActivity.this, "쪽지 에러 발생", Toast.LENGTH_SHORT).show();
                Log.e("쪽지 에러 발생", t.getMessage());
                showProgress(false);
            }
        });
    }

    private void showProgress(boolean show) {
        mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
    }
}