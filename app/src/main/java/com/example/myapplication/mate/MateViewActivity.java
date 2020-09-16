package com.example.myapplication.mate;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Join.JoinActivity;
import com.example.myapplication.Join.JoinData;
import com.example.myapplication.Join.JoinResponse;
import com.example.myapplication.R;
import com.example.myapplication.network.RetrofitClient;
import com.example.myapplication.network.ServiceApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MateViewActivity extends AppCompatActivity {
    private final String NICKNAME_EXTRA = "NICKNAME_EXTRA";
    private ServiceApi service;
    private ProgressBar mProgressView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mate_view);

        TextView title = (TextView) findViewById(R.id.title);
        TextView writer = (TextView) findViewById(R.id.writer);
        TextView date = (TextView) findViewById(R.id.date);
        TextView content = (TextView) findViewById(R.id.content);

        mProgressView = (ProgressBar) findViewById(R.id.progressBar);
        service = RetrofitClient.getClient().create(ServiceApi.class);

        title.setText(getIntent().getStringExtra("TITLE_EXTRA"));
        writer.setText(getIntent().getStringExtra("NICKNAME_EXTRA2"));
        date.setText(getIntent().getStringExtra("DATE_EXTRA"));
        content.setText(getIntent().getStringExtra("CONTENT_EXTRA"));

        ImageButton loginButton = (ImageButton) findViewById(R.id.imageButton1);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        Button editButton = (Button) findViewById(R.id.edit);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                String writer = getIntent().getStringExtra("NICKNAME_EXTRA2");
                String user = getIntent().getStringExtra("NICKNAME_EXTRA");
                if(writer.equals(user)){
                    Intent intent = new Intent(MateViewActivity.this, MateEditActivity.class);
                    intent.putExtra("CATE_EXTRA", getIntent().getStringExtra("CATE_EXTRA"));
                    intent.putExtra("CAMPUS_EXTRA", getIntent().getStringExtra("CAMPUS_EXTRA"));
                    intent.putExtra("NUMBER_EXTRA", title.getText().toString());
                    intent.putExtra("TITLE_EXTRA", title.getText().toString());
                    intent.putExtra("CONTENT_EXTRA", content.getText().toString());
                    intent.putExtra("NUMBER_EXTRA", getIntent().getIntExtra("NUMBER_EXTRA",1));
                    intent.putExtra(NICKNAME_EXTRA, getIntent().getStringExtra("NICKNAME_EXTRA"));
                    MateViewActivity.this.startActivity(intent);
                } else {
                    new AlertDialog.Builder(MateViewActivity.this)
                            .setMessage("수정 권한이 없습니다.")
                            .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which){
                                    dialog.cancel();
                                }
                            })
                            .show();
                }
            }
        });

        Button deleteButton = (Button) findViewById(R.id.delete);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                String writer = getIntent().getStringExtra("NICKNAME_EXTRA2");
                String user = getIntent().getStringExtra("NICKNAME_EXTRA");
                if(writer.equals(user)){
                    new AlertDialog.Builder(MateViewActivity.this)
                            .setTitle("글 삭제 여부")
                            .setMessage("정말 삭제하시겠습니까?")
                            .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which){
                                    int number = getIntent().getIntExtra("NUMBER_EXTRA",1);
                                    startDelete(new MateDeleteData(number));
                                    showProgress(true);
                                }
                            })
                            .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which){
                                    dialog.cancel();
                                }
                            })
                            .show();
                } else {
                    new AlertDialog.Builder(MateViewActivity.this)
                            .setMessage("삭제 권한이 없습니다.")
                            .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which){
                                    dialog.cancel();
                                }
                            })
                            .show();
                }
            }
        });
    }

    private void startDelete(MateDeleteData data) {
        service.matedelete(data).enqueue(new Callback<MateDeleteResponse>() {
            @Override
            public void onResponse(Call<MateDeleteResponse> call, Response<MateDeleteResponse> response) {
                MateDeleteResponse result = response.body();
                Toast.makeText(MateViewActivity.this, result.getMessage(), Toast.LENGTH_SHORT).show();
                showProgress(false);

                if (result.getCode() == 200) {
                    String cate = getIntent().getStringExtra("CATE_EXTRA");
                    if(cate.equals("혼밥")){
                        Intent intent = new Intent(MateViewActivity.this, AloneActivity.class);
                        intent.putExtra(NICKNAME_EXTRA, getIntent().getStringExtra("NICKNAME_EXTRA"));
                        MateViewActivity.this.startActivity(intent);
                    } else if(cate.equals("공모전")){
                        Intent intent = new Intent(MateViewActivity.this, ContestActivity.class);
                        intent.putExtra(NICKNAME_EXTRA, getIntent().getStringExtra("NICKNAME_EXTRA"));
                        MateViewActivity.this.startActivity(intent);
                    } else if(cate.equals("스터디")){
                        Intent intent = new Intent(MateViewActivity.this, StudyActivity.class);
                        intent.putExtra(NICKNAME_EXTRA, getIntent().getStringExtra("NICKNAME_EXTRA"));
                        MateViewActivity.this.startActivity(intent);
                    } else if(cate.equals("하우스")){
                        Intent intent = new Intent(MateViewActivity.this, HouseActivity.class);
                        intent.putExtra(NICKNAME_EXTRA, getIntent().getStringExtra("NICKNAME_EXTRA"));
                        MateViewActivity.this.startActivity(intent);
                    }
                }
            }

            @Override
            public void onFailure(Call<MateDeleteResponse> call, Throwable t) {
                Toast.makeText(MateViewActivity.this, "에러 발생", Toast.LENGTH_SHORT).show();
                Log.e("에러 발생", t.getMessage());
                showProgress(false);
            }
        });
    }

    private void showProgress(boolean show) {
        mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
    }
}
