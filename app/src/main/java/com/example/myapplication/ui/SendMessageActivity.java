package com.example.myapplication.ui;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import com.example.myapplication.R;
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
                NotificationManager mNotificationManager =
                        (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                //알림(Notification)을 관리하는 관리자 객체를 운영체제(Context)로부터 소환하기
                NotificationManager notificationManager=(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

                //Notification 객체를 생성해주는 건축가객체 생성(AlertDialog 와 비슷)
                NotificationCompat.Builder builder= null;

                //Oreo 버전(API26 버전)이상에서는 알림시에 NotificationChannel 이라는 개념이 필수 구성요소가 됨.
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){

                    String channelID="channel_01"; //알림채널 식별자
                    String channelName="MyChannel01"; //알림채널의 이름(별명)

                    //알림채널 객체 만들기
                    NotificationChannel channel= new NotificationChannel(channelID,channelName,NotificationManager.IMPORTANCE_DEFAULT);

                    //알림매니저에게 채널 객체의 생성을 요청
                    notificationManager.createNotificationChannel(channel);

                    //알림건축가 객체 생성
                    builder=new NotificationCompat.Builder(SendMessageActivity.this, channelID);


                }else{
                    //알림 건축가 객체 생성
                    builder= new NotificationCompat.Builder(SendMessageActivity.this, null);
                }

                //건축가에게 원하는 알림의 설정작업
                builder.setSmallIcon(android.R.drawable.ic_menu_view);

                //상태바를 드래그하여 아래로 내리면 보이는
                //알림창(확장 상태바)의 설정
                builder.setContentTitle("쪽지 알림");//알림창 제목
                builder.setContentText("[this진하]님께 쪽지가 도착하였습니다.");//알림창 내용
                //알림창의 큰 이미지
                Bitmap bm= BitmapFactory.decodeResource(getResources(),R.drawable.bell);
                builder.setLargeIcon(bm);//매개변수가 Bitmap을 줘야한다.

                //건축가에게 알림 객체 생성하도록
                Notification notification=builder.build();

                //알림매니저에게 알림(Notify) 요청
                notificationManager.notify(1, notification);

                //알림 요청시에 사용한 번호를 알림제거 할 수 있음.
                //notificationManager.cancel(1);

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
                    RecipText = (TextView) findViewById(R.id.recipient);
                    String content = RecipText.getText().toString();
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