package com.example.myapplication.lost;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.myapplication.Join.JoinData;
import com.example.myapplication.R;
import com.example.myapplication.mate.MateData;
import com.example.myapplication.mate.MateViewActivity;
import com.example.myapplication.mate.MateWriteActivity;
import com.example.myapplication.network.RetrofitClient;
import com.example.myapplication.network.ServiceApi;
import com.example.myapplication.ui.HomeActivity;
import com.example.myapplication.ui.MailActivity;
import com.example.myapplication.ui.MypageActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FindActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    private String type = "찾아요";
    private ServiceApi service;
    private ProgressBar mProgressView;
    private ListView listView = null;
    SwipeRefreshLayout mSwipeRefreshLayout;

    private final String NICKNAME_EXTRA = "NICKNAME_EXTRA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find);
        mProgressView = (ProgressBar) findViewById(R.id.loading);

        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefresh);
        mSwipeRefreshLayout.setOnRefreshListener(this);

        service = RetrofitClient.getClient().create(ServiceApi.class);

        attemptList();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavi);
        bottomNavigationView.setOnNavigationItemSelectedListener(new FindActivity.ItemSelectedListener());

    }

    @Override
    public void onRefresh() {
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

        if (type.isEmpty()) {
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            startList(new LostData(type));
            showProgress(true);
        }
    }

    private void startList(LostData lostData) {
        List<LostData> oData = new ArrayList<>();

        service.lostList(lostData).enqueue(new Callback<LostResponse>(){
            @Override
            public void onResponse(Call<LostResponse> call, Response<LostResponse> response) {
                LostResponse result = response.body();
                Toast.makeText(FindActivity.this, result.getMessage(), Toast.LENGTH_SHORT).show();
                showProgress(false);

                if (result.getCode() == 200) {
                    LostResponse sample = result;
                    for (LostResponse a :sample.getResult() ){
                        LostData oItem = new LostData();
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
                    listView = (ListView)findViewById(R.id.listView);
                    ListAdapter oAdapter = new ListAdapter((ArrayList<LostData>) oData);
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
                            intent.putExtra(NICKNAME_EXTRA, getIntent().getStringExtra("NICKNAME_EXTRA"));
                            startActivity(intent);
                        }
                    });

                }
            }

            @Override
            public void onFailure(Call<LostResponse> call, Throwable t) {
                Toast.makeText(FindActivity.this, "분실물 에러 발생", Toast.LENGTH_SHORT).show();
                Log.e("분실물 에러 발생", t.getMessage());
                t.printStackTrace();
                showProgress(false);
            }
        });

    }


    class ItemSelectedListener implements BottomNavigationView.OnNavigationItemSelectedListener{
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

            switch(menuItem.getItemId())
            {
                case R.id.home:
                    Intent intent = new Intent(FindActivity.this, HomeActivity.class);
                    intent.putExtra(NICKNAME_EXTRA, getIntent().getStringExtra("NICKNAME_EXTRA"));
                    startActivity(intent);
                    break;
                case R.id.mail:
                    Intent intent2 = new Intent(FindActivity.this, MailActivity.class);
                    intent2.putExtra(NICKNAME_EXTRA, getIntent().getStringExtra("NICKNAME_EXTRA"));
                    startActivity(intent2);
                    break;
                case R.id.mypage:
                    Intent intent3 = new Intent(FindActivity.this, MypageActivity.class);
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

    public void write(View v){
        Intent intent = new Intent(this, LostWriteActivity.class);
        intent.putExtra(NICKNAME_EXTRA, getIntent().getStringExtra("NICKNAME_EXTRA"));
        startActivity(intent);
    }

}
