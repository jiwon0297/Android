package com.example.myapplication.mate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.login.LoginActivity;
import com.example.myapplication.login.LoginData;
import com.example.myapplication.login.LoginResponse;
import com.example.myapplication.lost.ListAdapter;
import com.example.myapplication.lost.LostData;
import com.example.myapplication.lost.LostResponse;
import com.example.myapplication.network.RetrofitClient;
import com.example.myapplication.network.ServiceApi;
import com.example.myapplication.ui.HomeActivity;
import com.example.myapplication.ui.MailActivity;
import com.example.myapplication.ui.MypageActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AloneActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    private String cate = "혼밥";
    private ServiceApi service;
    private ProgressBar mProgressView;
    private ListView listView = null;

    SwipeRefreshLayout mSwipeRefreshLayout;

    private final String NICKNAME_EXTRA = "NICKNAME_EXTRA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alone);

        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefresh);
        mSwipeRefreshLayout.setOnRefreshListener(this);

        mProgressView = (ProgressBar) findViewById(R.id.progressBar);
        service = RetrofitClient.getClient().create(ServiceApi.class);
        attemptList();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavi);
        bottomNavigationView.setOnNavigationItemSelectedListener(new AloneActivity.ItemSelectedListener());

        ImageButton backbutton = (ImageButton) findViewById(R.id.imageButton);
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AloneActivity.this, MateActivity.class);
                intent.putExtra(NICKNAME_EXTRA, getIntent().getStringExtra("NICKNAME_EXTRA"));
                startActivity(intent);
            }
        });

        ImageButton searchbtn = (ImageButton) findViewById(R.id.search);
        searchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AloneActivity.this, MateSearchActivity.class);
                intent.putExtra(NICKNAME_EXTRA, getIntent().getStringExtra("NICKNAME_EXTRA"));
                intent.putExtra("CATE","혼밥");
                startActivity(intent);
            }
        });

    }
    @Override
    public void onRefresh(){
        mSwipeRefreshLayout.setRefreshing(true);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                service = RetrofitClient.getClient().create(ServiceApi.class);
                attemptList();
                mSwipeRefreshLayout.setRefreshing(false);
            }
        }, 1000);
    }

    private void attemptList() {

        boolean cancel = false;
        View focusView = null;

        if (cate.isEmpty()) {
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            startList(new MateData(cate));
            showProgress(true);
        }
    }

    private void startList(MateData data) {
        List<MateData> oData = new ArrayList<>();

        service.matelist(data).enqueue(new Callback<MateResponse>() {
            @Override
            public void onResponse(Call<MateResponse> call, Response<MateResponse> response) {
                MateResponse result = response.body();
                Toast.makeText(AloneActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                showProgress(false);

                if (result.getCode() == 200) {
                    MateResponse sample = result;
                    for (MateResponse a : sample.getResult()) {
                        MateData oItem = new MateData();
                        oItem.campus = a.getCampus();
                        oItem.title = a.getTitle();
                        oItem.nickname = a.getNickname();
                        oItem.date = a.getDate();
                        oItem.content = a.getContent();
                        oItem.number = a.getNumber();
                        oItem.cate = a.getCate();
                        oData.add(oItem);
                    }
                    listView = (ListView) findViewById(R.id.listView1);
                    MyAdapter oAdapter = new MyAdapter((ArrayList<MateData>) oData);
                    listView.setAdapter(oAdapter);

                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView parent, View v, int position, long id) {
                            SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            String datetext = transFormat.format(oData.get(position).date);
                            Intent intent = new Intent(getApplicationContext(), MateViewActivity.class);
                            intent.putExtra("CATE_EXTRA", oData.get(position).cate);
                            intent.putExtra("NUMBER_EXTRA", oData.get(position).number);
                            intent.putExtra("TITLE_EXTRA", oData.get(position).title);
                            intent.putExtra("NICKNAME_EXTRA2", oData.get(position).nickname);
                            intent.putExtra("DATE_EXTRA", datetext);
                            intent.putExtra("CONTENT_EXTRA", oData.get(position).content);
                            intent.putExtra("CAMPUS_EXTRA", oData.get(position).campus);
                            intent.putExtra(NICKNAME_EXTRA, getIntent().getStringExtra("NICKNAME_EXTRA"));
                            startActivity(intent);
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<MateResponse> call, Throwable t) {
                Toast.makeText(AloneActivity.this, "에러 발생", Toast.LENGTH_SHORT).show();
                Log.e("에러 발생", t.getMessage());
                showProgress(false);
            }
        });
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        Intent intent = new Intent(AloneActivity.this, MateActivity.class);
        intent.putExtra(NICKNAME_EXTRA, getIntent().getStringExtra("NICKNAME_EXTRA"));
        startActivity(intent);
    }

    public void write(View v){
        Intent intent = new Intent(this, MateWriteActivity.class);
        intent.putExtra(NICKNAME_EXTRA, getIntent().getStringExtra("NICKNAME_EXTRA"));
        startActivity(intent);
    }

    class ItemSelectedListener implements BottomNavigationView.OnNavigationItemSelectedListener{
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

            switch(menuItem.getItemId())
            {
                case R.id.home:
                    Intent intent = new Intent(AloneActivity.this, HomeActivity.class);
                    intent.putExtra(NICKNAME_EXTRA, getIntent().getStringExtra("NICKNAME_EXTRA"));
                    startActivity(intent);
                    break;
                case R.id.mail:
                    Intent intent2 = new Intent(AloneActivity.this, MailActivity.class);
                    intent2.putExtra(NICKNAME_EXTRA, getIntent().getStringExtra("NICKNAME_EXTRA"));
                    startActivity(intent2);
                    break;
                case R.id.mypage:
                    Intent intent3 = new Intent(AloneActivity.this, MypageActivity.class);
                    intent3.putExtra(NICKNAME_EXTRA, getIntent().getStringExtra("NICKNAME_EXTRA"));
                    startActivity(intent3);
                    break;
            }
            return true;
        }
    }

    private void showProgress(boolean show) {
        mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
    }

}
