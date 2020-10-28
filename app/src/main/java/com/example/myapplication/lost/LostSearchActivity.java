package com.example.myapplication.lost;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.myapplication.R;
import com.example.myapplication.mate.AloneActivity;
import com.example.myapplication.mate.ContestActivity;
import com.example.myapplication.mate.HouseActivity;
import com.example.myapplication.mate.MateSearchActivity;
import com.example.myapplication.mate.StudyActivity;
import com.example.myapplication.network.RetrofitClient;
import com.example.myapplication.network.ServiceApi;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LostSearchActivity extends AppCompatActivity {
    private ServiceApi service;
    private ProgressBar mProgressView;
    private ListView listView = null;
    EditText searchtitle;
    SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lost_search);

        mProgressView = (ProgressBar) findViewById(R.id.progressBar);
        service = RetrofitClient.getClient().create(ServiceApi.class);

        searchtitle = (EditText) findViewById(R.id.search);
        searchtitle.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if ((keyEvent.getAction() == KeyEvent.ACTION_DOWN) && (i == KeyEvent.KEYCODE_ENTER)){
                    attemptList();
                    return true;
                }
                return false;
            }
        });
        ImageButton searchbtn = (ImageButton) findViewById(R.id.searchButton);
        searchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptList();
            }
        });
    }

    private void attemptList() {
        boolean cancel = false;
        View focusView = null;
        String title = searchtitle.getText().toString();
        String type = getIntent().getStringExtra("TYPE");

        if (type.isEmpty()) {
            searchtitle.setError("검색어를 입력해주세요.");
            focusView = searchtitle;
            cancel = true;
        } else if (!isSearchTitleValid(title)) {
            searchtitle.setError("두 글자 이상 입력해주세요.");
            focusView = searchtitle;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            startList(new LostSearchData(type,title));
            showProgress(true);
        }
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        if(getIntent().getStringExtra("TYPE").equals("혼밥")){
            Intent intent = new Intent(LostSearchActivity.this, AloneActivity.class);
            intent.putExtra("NICKNAME_EXTRA", getIntent().getStringExtra("NICKNAME_EXTRA"));
            LostSearchActivity.this.startActivity(intent);
        } else if(getIntent().getStringExtra("TYPE").equals("공모전")){
            Intent intent = new Intent(LostSearchActivity.this, ContestActivity.class);
            intent.putExtra("NICKNAME_EXTRA", getIntent().getStringExtra("NICKNAME_EXTRA"));
            LostSearchActivity.this.startActivity(intent);
        }
    }

    private void startList(LostSearchData lostData) {
        List<LostSearchData> oData = new ArrayList<>();

        service.lostsearch(lostData).enqueue(new Callback<LostSearchResponse>(){
            @Override
            public void onResponse(Call<LostSearchResponse> call, Response<LostSearchResponse> response) {
                LostSearchResponse result = response.body();

                Toast.makeText(LostSearchActivity.this, result.getMessage(), Toast.LENGTH_SHORT).show();
                showProgress(false);

                if (result.getCode() == 200) {
                    LostSearchResponse sample = result;
                    for (LostSearchResponse a :sample.getResult() ){
                        LostSearchData oItem = new LostSearchData();
                        oItem.campus = a.getCampus();
                        oItem.title = a.getTitle();
                        oItem.nickname = a.getNickname();
                        oItem.number = a.getNumber();
                        oItem.date = a.getDate();
                        oItem.content = a.getContent();
                        oItem.type = a.getType();
                        oItem.url = a.getUrl();
                        oData.add(oItem);
                    }
                    listView = (ListView)findViewById(R.id.listView1);
                    LostSearchAdapter oAdapter = new LostSearchAdapter((ArrayList<LostSearchData>) oData);
                    listView.setAdapter(oAdapter);

                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                        @Override
                        public void onItemClick(AdapterView parent, View v, int position, long id){
                            SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            String datetext = transFormat.format(oData.get(position).date);
                            Intent intent = new Intent(getApplicationContext(), LostViewActivity.class);
                            intent.putExtra("TYPE_EXTRA", oData.get(position).type);
                            intent.putExtra("NUMBER_EXTRA", oData.get(position).number);
                            intent.putExtra("TITLE_EXTRA", oData.get(position).title);
                            intent.putExtra("NICKNAME_EXTRA2", oData.get(position).nickname);
                            intent.putExtra("DATE_EXTRA", datetext);
                            intent.putExtra("CONTENT_EXTRA", oData.get(position).content);
                            intent.putExtra("CAMPUS_EXTRA", oData.get(position).campus);
                            intent.putExtra("URL_EXTRA", oData.get(position).url);
                            intent.putExtra("NICKNAME_EXTRA", getIntent().getStringExtra("NICKNAME_EXTRA"));
                            startActivity(intent);
                        }
                    });

                }
            }

            @Override
            public void onFailure(Call<LostSearchResponse> call, Throwable t) {
                Toast.makeText(LostSearchActivity.this, "분실물 에러 발생", Toast.LENGTH_SHORT).show();
                Log.e("분실물 에러 발생", t.getMessage());
                t.printStackTrace();
                showProgress(false);
            }
        });
    }

    private boolean isSearchTitleValid(String title) {
        return title.length() >= 2;
    }

    private void showProgress(boolean show) {
        mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
    }
}