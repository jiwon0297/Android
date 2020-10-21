package com.example.myapplication.mate;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Join.JoinActivity;
import com.example.myapplication.Join.JoinData;
import com.example.myapplication.Join.JoinResponse;
import com.example.myapplication.R;
import com.example.myapplication.network.RetrofitClient;
import com.example.myapplication.network.ServiceApi;
import com.example.myapplication.ui.MainActivity;
import com.example.myapplication.ui.SendMessageActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MateViewActivity extends AppCompatActivity{
    private final String NICKNAME_EXTRA = "NICKNAME_EXTRA";
    private ServiceApi service;
    private ProgressBar mProgressView;
    private ListView listView = null;

    SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mate_view);

        TextView title = (TextView) findViewById(R.id.title);
        TextView writer = (TextView) findViewById(R.id.writer);
        TextView date = (TextView) findViewById(R.id.date);
        TextView content = (TextView) findViewById(R.id.content);
        EditText commentcontent = (EditText) findViewById(R.id.commenttext);

        mProgressView = (ProgressBar) findViewById(R.id.progressBar);
        service = RetrofitClient.getClient().create(ServiceApi.class);
        attemptList();

        title.setText(getIntent().getStringExtra("TITLE_EXTRA"));
        writer.setText(getIntent().getStringExtra("NICKNAME_EXTRA2"));
        date.setText(getIntent().getStringExtra("DATE_EXTRA"));
        content.setText(getIntent().getStringExtra("CONTENT_EXTRA"));

        ImageButton backbutton = (ImageButton) findViewById(R.id.imageButton1);
        backbutton.setOnClickListener(new View.OnClickListener() {
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
                    startCommentWrite(new MateCommentWriteData(postnumber, user, content));
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
                    Intent intent = new Intent(MateViewActivity.this, MateEditActivity.class);
                    intent.putExtra("CATE_EXTRA", getIntent().getStringExtra("CATE_EXTRA"));
                    intent.putExtra("CAMPUS_EXTRA", getIntent().getStringExtra("CAMPUS_EXTRA"));
                    intent.putExtra("DATE_EXTRA", date.getText().toString());
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

        writer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                String writer = getIntent().getStringExtra("NICKNAME_EXTRA2");
                String user = getIntent().getStringExtra("NICKNAME_EXTRA");
                if(writer.equals(user)){
                    new AlertDialog.Builder(MateViewActivity.this)
                            .setMessage("본인에게는 쪽지를 보낼 수 없습니다.")
                            .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which){
                                    dialog.cancel();
                                }
                            })
                            .show();
                } else {
                    new AlertDialog.Builder(MateViewActivity.this)
                            .setTitle("쪽지보내기")
                            .setMessage("작성자에게 쪽지를 보내시겠습니까?")
                            .setPositiveButton("예", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which){
                                    Intent intent = new Intent(MateViewActivity.this, SendMessageActivity.class);
                                    intent.putExtra("SENDER", user);
                                    intent.putExtra("RECIPIENT", writer);
                                    startActivity(intent);
                                }
                            })
                            .setNegativeButton("아니요", new DialogInterface.OnClickListener() {
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

    private void startCommentDelete(MateCommentDeleteData data){
        service.matecommentdelete(data).enqueue(new Callback<MateCommentDeleteResponse>() {
            @Override
            public void onResponse(Call<MateCommentDeleteResponse> call, Response<MateCommentDeleteResponse> response) {
                MateCommentDeleteResponse result = response.body();
                Toast.makeText(MateViewActivity.this, result.getMessage(), Toast.LENGTH_SHORT).show();
                showProgress(false);

                if (result.getCode() == 200) {
                    attemptList();
                }
            }

            @Override
            public void onFailure(Call<MateCommentDeleteResponse> call, Throwable t) {
                Toast.makeText(MateViewActivity.this, "에러 발생", Toast.LENGTH_SHORT).show();
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
            startCommentList(new MateCommentData(postnumber));
            showProgress(true);
        }
    }

    private void startCommentList(MateCommentData data) {
        List<MateCommentData> oData = new ArrayList<>();

        service.matecommentlist(data).enqueue(new Callback<MateCommentResponse>() {
            @Override
            public void onResponse(Call<MateCommentResponse> call, Response<MateCommentResponse> response) {
                MateCommentResponse result = response.body();
                Toast.makeText(MateViewActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                showProgress(false);

                if (result.getCode() == 200) {
                    MateCommentResponse sample = result;
                    for (MateCommentResponse a :sample.getResult() ){
                        MateCommentData oItem = new MateCommentData();
                        oItem.nickname = a.getNickname();
                        oItem.date = a.getDate();
                        oItem.content = a.getContent();
                        oItem.number = a.getNumber();
                        oItem.postnumber = a.getPostnumber();
                        oData.add(oItem);
                    }
                    listView = (ListView)findViewById(R.id.listView1);
                    CommentAdapter oAdapter = new CommentAdapter((ArrayList<MateCommentData>) oData);
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
                        public void onItemClick(AdapterView parent, View view, int position, long id){
                            {
                                //PopupMenu객체 생성.
                                //생성자함수의 첫번재 파라미터 : Context
                                //생성자함수의 두번째 파라미터 : Popup Menu를 붙일 anchor 뷰
                                PopupMenu popup= new PopupMenu(MateViewActivity.this, view);//view는 오래 눌러진 뷰를 의미
                                //Popup Menu에 들어갈 MenuItem 추가.
                                //이전 포스트의 컨텍스트 메뉴(Context menu)처럼 xml 메뉴 리소스 사용
                                //첫번재 파라미터 : res폴더>>menu폴더>>menu_listview.xml파일 리소스
                                //두번재 파라미터 : Menu 객체->Popup Menu 객체로 부터 Menu 객체 얻어오기
                                getMenuInflater().inflate(R.menu.menu_commentmenu, popup.getMenu());
                                //Popup menu의 메뉴아이템을 눌렀을  때 보여질 ListView 항목의 위치
                                //Listener 안에서 사용해야 하기에 final로 선언
                                final int index= position;
                                //Popup Menu의 MenuItem을 클릭하는 것을 감지하는 listener 설정

                                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                                    @Override
                                    public boolean onMenuItemClick(MenuItem item) {
                                        //선택된 Popup Menu의  아이템아이디를 구별하여 원하는 작업 수행
                                        //예제에서는 선택된 ListView의 항목(String 문자열) data와 해당 메뉴이름을 출력함
                                        switch( item.getItemId() ){
                                            case R.id.mail:
                                                String recipient = oData.get(position).nickname;
                                                String sender = getIntent().getStringExtra("NICKNAME_EXTRA");

                                                if(recipient.equals(sender)){
                                                    Toast.makeText(MateViewActivity.this,"본인에게 쪽지를 보낼 수는 없습니다.",Toast.LENGTH_SHORT).show();
                                                    break;

                                                } else{
                                                    Intent intent = new Intent(MateViewActivity.this, SendMessageActivity.class);
                                                    intent.putExtra("SENDER", sender);
                                                    intent.putExtra("RECIPIENT", recipient);
                                                    startActivity(intent);
                                                    break;
                                                }

                                            case R.id.delete:
                                                String writer = oData.get(position).nickname;
                                                String user = getIntent().getStringExtra("NICKNAME_EXTRA");
                                                if(writer.equals(user)){
                                                    new AlertDialog.Builder(MateViewActivity.this)
                                                            .setTitle("댓글 삭제 여부")
                                                            .setMessage("정말 삭제하시겠습니까?")
                                                            .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                                                public void onClick(DialogInterface dialog, int which){
                                                                    int number = oData.get(position).number;
                                                                    startCommentDelete(new MateCommentDeleteData(number));
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
                                                break;
                                        }
                                        return false;
                                    }
                                });
                                popup.show();//Popup Menu 보이기
                            }
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<MateCommentResponse> call, Throwable t) {
                Toast.makeText(MateViewActivity.this, "에러 발생", Toast.LENGTH_SHORT).show();
                Log.e("에러 발생", t.getMessage());
                showProgress(false);
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

    private void startCommentWrite(MateCommentWriteData data) {
        service.matecommentwrite(data).enqueue(new Callback<MateCommentWriteResponse>() {
            @Override
            public void onResponse(Call<MateCommentWriteResponse> call, Response<MateCommentWriteResponse> response) {
                MateCommentWriteResponse result = response.body();
                Toast.makeText(MateViewActivity.this, result.getMessage(), Toast.LENGTH_SHORT).show();
                showProgress(false);

                if (result.getCode() == 200) {
                    attemptList();
                }
            }

            @Override
            public void onFailure(Call<MateCommentWriteResponse> call, Throwable t) {
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
