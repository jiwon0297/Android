package com.example.myapplication.lost;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.mate.AloneActivity;
import com.example.myapplication.mate.CommentAdapter;
import com.example.myapplication.mate.ContestActivity;
import com.example.myapplication.mate.HouseActivity;
import com.example.myapplication.mate.MateCommentData;
import com.example.myapplication.mate.MateCommentDeleteData;
import com.example.myapplication.mate.MateCommentDeleteResponse;
import com.example.myapplication.mate.MateCommentResponse;
import com.example.myapplication.mate.MateCommentWriteData;
import com.example.myapplication.mate.MateCommentWriteResponse;
import com.example.myapplication.mate.MateDeleteData;
import com.example.myapplication.mate.MateDeleteResponse;
import com.example.myapplication.mate.MateEditActivity;
import com.example.myapplication.mate.MateViewActivity;
import com.example.myapplication.mate.StudyActivity;
import com.example.myapplication.network.RetrofitClient;
import com.example.myapplication.network.ServiceApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LostViewActivity extends AppCompatActivity {
    private final String NICKNAME_EXTRA = "NICKNAME_EXTRA";
    private ServiceApi service;
    private ProgressBar mProgressView;
    private ListView listView = null;

    SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lost_view);

        TextView title = (TextView) findViewById(R.id.title);
        TextView writer = (TextView) findViewById(R.id.writer);
        TextView content = (TextView) findViewById(R.id.content);
        TextView date = (TextView) findViewById(R.id.date);
        EditText commentcontent = (EditText) findViewById(R.id.commenttext);

        mProgressView = (ProgressBar) findViewById(R.id.progressBar);
        service = RetrofitClient.getClient().create(ServiceApi.class);
        attemptList();

        title.setText(getIntent().getStringExtra("TITLE_EXTRA"));
        writer.setText(getIntent().getStringExtra("NICKNAME_EXTRA2"));
        content.setText(getIntent().getStringExtra("CONTENT_EXTRA"));
        date.setText(getIntent().getStringExtra("DATE_EXTRA"));

        ImageButton backButton = (ImageButton) findViewById(R.id.imageButton1);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        Button commentButton = (Button) findViewById(R.id.commentbutton);
        commentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = getIntent().getStringExtra("NICKNAME_EXTRA");
                int postnumber = getIntent().getIntExtra("NUMBER_EXTRA",0);

                commentcontent.setError(null);
                String content = commentcontent.getText().toString();
                boolean cancel = false;
                View focusView = null;

                if (content.isEmpty()) {
                    commentcontent.setError("댓글을 입력해주세요.");
                    focusView = commentcontent;
                    cancel = true;
                }

                if (cancel) {
                    focusView.requestFocus();
                } else {
                    startCommentWrite(new LostCommentWriteData(postnumber, user, content));
                    showProgress(true);
                    commentcontent.setText(null);
                }
            }
        });

        Button editButton = (Button) findViewById(R.id.edit);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                String writer = getIntent().getStringExtra("NICKNAME_EXTRA2");
                String user = getIntent().getStringExtra("NICKNAME_EXTRA");
                if(writer.equals(user)){
                    Intent intent = new Intent(LostViewActivity.this, LostEditActivity.class);
                    intent.putExtra("NUMBER_EXTRA", title.getText().toString());
                    intent.putExtra("TITLE_EXTRA", title.getText().toString());
                    intent.putExtra("TYPE_EXTRA", getIntent().getStringExtra("TYPE_EXTRA"));
                    intent.putExtra("CAMPUS_EXTRA", getIntent().getStringExtra("CAMPUS_EXTRA"));
                    intent.putExtra("CONTENT_EXTRA", content.getText().toString());
                    intent.putExtra("NUMBER_EXTRA", getIntent().getIntExtra("NUMBER_EXTRA",1));
                    intent.putExtra(NICKNAME_EXTRA, getIntent().getStringExtra("NICKNAME_EXTRA"));
                    LostViewActivity.this.startActivity(intent);
                } else {
                    new AlertDialog.Builder(LostViewActivity.this)
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
                    new AlertDialog.Builder(LostViewActivity.this)
                            .setTitle("글 삭제 여부")
                            .setMessage("정말 삭제하시겠습니까?")
                            .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which){
                                    int number = getIntent().getIntExtra("NUMBER_EXTRA",1);
                                    startDelete(new LostDeleteData(number));
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
                    new AlertDialog.Builder(LostViewActivity.this)
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

        ImageButton refreshbutton = (ImageButton) findViewById(R.id.refreshbtn);
        refreshbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onRefresh();
            }
        });

    }

    public void onRefresh(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                service = RetrofitClient.getClient().create(ServiceApi.class);
                attemptList();
            }
        }, 1000);
    }

    private void startCommentDelete(LostCommentDeleteData data){
        service.lostcommentdelete(data).enqueue(new Callback<LostCommentDeleteResponse>() {
            @Override
            public void onResponse(Call<LostCommentDeleteResponse> call, Response<LostCommentDeleteResponse> response) {
                LostCommentDeleteResponse result = response.body();
                Toast.makeText(LostViewActivity.this, result.getMessage(), Toast.LENGTH_SHORT).show();
                showProgress(false);

                if (result.getCode() == 200) {
                    attemptList();
                }
            }

            @Override
            public void onFailure(Call<LostCommentDeleteResponse> call, Throwable t) {
                Toast.makeText(LostViewActivity.this, "에러 발생", Toast.LENGTH_SHORT).show();
                Log.e("에러 발생", t.getMessage());
                showProgress(false);
            }
        });
    }

    private void startDelete(LostDeleteData data) {
        service.lostdelete(data).enqueue(new Callback<LostDeleteResponse>() {
            @Override
            public void onResponse(Call<LostDeleteResponse> call, Response<LostDeleteResponse> response) {
                LostDeleteResponse result = response.body();
                Toast.makeText(LostViewActivity.this, result.getMessage(), Toast.LENGTH_SHORT).show();
                showProgress(false);

                if (result.getCode() == 200) {
                    String cate = getIntent().getStringExtra("TYPE_EXTRA");
                    if(cate.equals("찾아요")){
                        Intent intent = new Intent(LostViewActivity.this, FindActivity.class);
                        intent.putExtra(NICKNAME_EXTRA, getIntent().getStringExtra("NICKNAME_EXTRA"));
                        LostViewActivity.this.startActivity(intent);
                    } else if(cate.equals("공모전")){
                        Intent intent = new Intent(LostViewActivity.this, GetActivity.class);
                        intent.putExtra(NICKNAME_EXTRA, getIntent().getStringExtra("NICKNAME_EXTRA"));
                        LostViewActivity.this.startActivity(intent);
                    }
                }
            }

            @Override
            public void onFailure(Call<LostDeleteResponse> call, Throwable t) {
                Toast.makeText(LostViewActivity.this, "에러 발생", Toast.LENGTH_SHORT).show();
                Log.e("에러 발생", t.getMessage());
                showProgress(false);
            }
        });
    }

    private void attemptList() {
        int postnumber = getIntent().getIntExtra("NUMBER_EXTRA",1);

        boolean cancel = false;
        View focusView = null;

        if (postnumber==0) {
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            startCommentList(new LostCommentData(postnumber));
            showProgress(true);
        }
    }

    private void startCommentList(LostCommentData data) {
        List<LostCommentData> oData = new ArrayList<>();

        service.lostcommentlist(data).enqueue(new Callback<LostCommentResponse>() {
            @Override
            public void onResponse(Call<LostCommentResponse> call, Response<LostCommentResponse> response) {
                LostCommentResponse result = response.body();
                Toast.makeText(LostViewActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                showProgress(false);

                if (result.getCode() == 200) {
                    LostCommentResponse sample = result;
                    for (LostCommentResponse a :sample.getResult() ){
                        LostCommentData oItem = new LostCommentData();
                        oItem.nickname = a.getNickname();
                        oItem.date = a.getDate();
                        oItem.content = a.getContent();
                        oItem.number = a.getNumber();
                        oItem.postnumber = a.getPostnumber();
                        oData.add(oItem);
                    }
                    listView = (ListView)findViewById(R.id.listView1);
                    ListCommentAdapter oAdapter = new ListCommentAdapter((ArrayList<LostCommentData>) oData);
                    int totalHeight = 0;
                    for (int i=0; i<oAdapter.getCount(); i++){
                        View listItem = oAdapter.getView(i,null,listView);
                        listItem.measure(0,0);
                        totalHeight += listItem.getMeasuredHeight();
                    }

                    ViewGroup.LayoutParams params = listView.getLayoutParams();
                    params.height = totalHeight + (listView.getDividerHeight() * (oAdapter.getCount() - 1));
                    listView.setLayoutParams(params);

                    listView.setAdapter(oAdapter);

                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                        @Override
                        public void onItemClick(AdapterView parent, View v, int position, long id){
                            String writer = oData.get(position).nickname;
                            String user = getIntent().getStringExtra("NICKNAME_EXTRA");
                            if(writer.equals(user)){
                                new AlertDialog.Builder(LostViewActivity.this)
                                        .setTitle("댓글 삭제 여부")
                                        .setMessage("정말 삭제하시겠습니까?")
                                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which){
                                                int number = oData.get(position).number;
                                                startCommentDelete(new LostCommentDeleteData(number));
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
                                new AlertDialog.Builder(LostViewActivity.this)
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
            }

            @Override
            public void onFailure(Call<LostCommentResponse> call, Throwable t) {
                Toast.makeText(LostViewActivity.this, "에러 발생", Toast.LENGTH_SHORT).show();
                Log.e("에러 발생", t.getMessage());
                showProgress(false);
            }
        });
    }

    private void startCommentWrite(LostCommentWriteData data) {
        service.lostcommentwrite(data).enqueue(new Callback<LostCommentWriteResponse>() {
            @Override
            public void onResponse(Call<LostCommentWriteResponse> call, Response<LostCommentWriteResponse> response) {
                LostCommentWriteResponse result = response.body();
                Toast.makeText(LostViewActivity.this, result.getMessage(), Toast.LENGTH_SHORT).show();
                showProgress(false);

                if (result.getCode() == 200) {
                    attemptList();
                }
            }

            @Override
            public void onFailure(Call<LostCommentWriteResponse> call, Throwable t) {
                Toast.makeText(LostViewActivity.this, "에러 발생", Toast.LENGTH_SHORT).show();
                Log.e("에러 발생", t.getMessage());
                showProgress(false);
            }
        });
    }

    private void showProgress(boolean show) {
        mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
    }
}
