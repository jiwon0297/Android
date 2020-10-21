package com.example.myapplication.ui;

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
import com.example.myapplication.mate.HouseActivity;
import com.example.myapplication.mate.MateDeleteData;
import com.example.myapplication.mate.MateDeleteResponse;
import com.example.myapplication.mate.MateViewActivity;
import com.example.myapplication.mate.StudyActivity;
import com.example.myapplication.network.RetrofitClient;
import com.example.myapplication.network.ServiceApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MailViewActivity extends AppCompatActivity {
    private TextView dateText;
    private TextView person;
    private TextView contentText;
    private TextView personText;
    private TextView date;
    private ProgressBar mProgressView;
    private ServiceApi service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mail_view);

        dateText = (TextView) findViewById(R.id.date);
        personText = (TextView) findViewById(R.id.person);
        contentText = (TextView) findViewById(R.id.content);

        person = (TextView) findViewById(R.id.personText);
        date = (TextView) findViewById(R.id.dateText);

        service = RetrofitClient.getClient().create(ServiceApi.class);

        String nickname = getIntent().getStringExtra("NICKNAME_EXTRA");
        String sender = getIntent().getStringExtra("SENDER");

        Button resendbtn = (Button) findViewById(R.id.resend);
        Button deletebtn = (Button) findViewById(R.id.delete);

        ImageButton backbutton = (ImageButton) findViewById(R.id.imageButton1);
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MailViewActivity.this, HomeActivity.class);
                intent.putExtra("NICKNAME_EXTRA", getIntent().getStringExtra("NICKNAME_EXTRA"));
                MailViewActivity.this.startActivity(intent);
            }
        });

        if (nickname.equals(sender)) {
            dateText.setText(getIntent().getStringExtra("DATE_EXTRA"));
            personText.setText(getIntent().getStringExtra("RECIPIENT"));
            contentText.setText(getIntent().getStringExtra("CONTENT_EXTRA"));

            person.setText("받는 사람 : ");
            date.setText("보낸 시각 : ");

            resendbtn.setVisibility(View.GONE);
            deletebtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    new AlertDialog.Builder(MailViewActivity.this)
                            .setTitle("쪽지 삭제 여부")
                            .setMessage("정말 삭제하시겠습니까?")
                            .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    int number = getIntent().getIntExtra("NUMBER_EXTRA", 0);
                                    startDelete(new MailDeleteData(number));
                                }
                            })
                            .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            })
                            .show();
                }
            });
        } else{
            dateText.setText(getIntent().getStringExtra("DATE_EXTRA"));
            personText.setText(getIntent().getStringExtra("SENDER"));
            contentText.setText(getIntent().getStringExtra("CONTENT_EXTRA"));

            resendbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MailViewActivity.this, SendMessageActivity.class);
                    intent.putExtra("SENDER", getIntent().getStringExtra("NICKNAME_EXTRA"));
                    intent.putExtra("RECIPIENT", getIntent().getStringExtra("SENDER"));
                    startActivity(intent);
                }
            });

            deletebtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    new AlertDialog.Builder(MailViewActivity.this)
                            .setTitle("쪽지 삭제 여부")
                            .setMessage("정말 삭제하시겠습니까?")
                            .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    int number = getIntent().getIntExtra("NUMBER_EXTRA", 0);
                                    startDelete(new MailDeleteData(number));
                                }
                            })
                            .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            })
                            .show();
                }
            });
        }
    }

    private void startDelete(MailDeleteData data) {
        service.maildelete(data).enqueue(new Callback<MailDeleteResponse>() {
            @Override
            public void onResponse(Call<MailDeleteResponse> call, Response<MailDeleteResponse> response) {
                MailDeleteResponse result = response.body();
                Toast.makeText(MailViewActivity.this, result.getMessage(), Toast.LENGTH_SHORT).show();

                if (result.getCode() == 200) {
                    Intent intent = new Intent(MailViewActivity.this, MailActivity.class);
                    intent.putExtra("NICKNAME_EXTRA", getIntent().getStringExtra("NICKNAME_EXTRA"));
                    MailViewActivity.this.startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<MailDeleteResponse> call, Throwable t) {
                Toast.makeText(MailViewActivity.this, "에러 발생", Toast.LENGTH_SHORT).show();
                Log.e("에러 발생", t.getMessage());
            }
        });
    }

    public void onBackPressed() {
        //super.onBackPressed();
    }
}