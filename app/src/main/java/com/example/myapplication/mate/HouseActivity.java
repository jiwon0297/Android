package com.example.myapplication.mate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.network.RetrofitClient;
import com.example.myapplication.network.ServiceApi;
import com.example.myapplication.ui.HomeActivity;
import com.example.myapplication.ui.MailActivity;
import com.example.myapplication.ui.MypageActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HouseActivity extends AppCompatActivity {
    private String cate = "하우스";
    private ServiceApi service;
    private ProgressBar mProgressView;
    private ListView listView = null;

    private static final String NUMBER_EXTRA = "NUMBER_EXTRA";
    private static final String TITLE_EXTRA = "TITLE_EXTRA";
    private final String NICKNAME_EXTRA = "NICKNAME_EXTRA";
    private static final String NICKNAME_EXTRA2 = "NICKNAME_EXTRA";
    private static final String CONTENT_EXTRA = "CONTENT_EXTRA";
    private static final String DATE_EXTRA = "DATE_EXTRA";
    private static final String CATE_EXTRA = "CATE_EXTRA";
    private static final String CAMPUS_EXTRA = "CAMPUS_EXTRA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house);

        mProgressView = (ProgressBar) findViewById(R.id.progressBar);
        service = RetrofitClient.getClient().create(ServiceApi.class);
        attemptList();

        /*
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id){
                Intent intent = new Intent(getApplicationContext(), MateViewActivity.class);
                intent.putExtra("TITLE_EXTRA", matelist.get(position).getTitle());
                intent.putExtra("NICKNAME_EXTRA2", matelist.get(position).getNickname());
                intent.putExtra("DATE_EXTRA", matelist.get(position).getDate());
                intent.putExtra("CONTENT_EXTRA", matelist.get(position).getContent());
                startActivity(intent);
            }
        });

         */

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavi);
        bottomNavigationView.setOnNavigationItemSelectedListener(new HouseActivity.ItemSelectedListener());

    }

    class ItemSelectedListener implements BottomNavigationView.OnNavigationItemSelectedListener{
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

            switch(menuItem.getItemId())
            {
                case R.id.home:
                    Intent intent = new Intent(HouseActivity.this, HomeActivity.class);
                    intent.putExtra(NICKNAME_EXTRA, getIntent().getStringExtra("NICKNAME_EXTRA"));
                    startActivity(intent);
                    break;
                case R.id.mail:
                    Intent intent2 = new Intent(HouseActivity.this, MailActivity.class);
                    intent2.putExtra(NICKNAME_EXTRA, getIntent().getStringExtra("NICKNAME_EXTRA"));
                    startActivity(intent2);
                    break;
                case R.id.mypage:
                    Intent intent3 = new Intent(HouseActivity.this, MypageActivity.class);
                    intent3.putExtra(NICKNAME_EXTRA, getIntent().getStringExtra("NICKNAME_EXTRA"));
                    startActivity(intent3);
                    break;
            }
            return true;
        }
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
                Toast.makeText(HouseActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                showProgress(false);

                if (result.getCode() == 200) {
                    MateResponse sample = result;
                    for (MateResponse a :sample.getResult() ){
                        MateData oItem = new MateData();
                        oItem.campus = "[" + a.getCampus() + "]";
                        oItem.title = a.getTitle();
                        oItem.nickname = a.getNickname();
                        oItem.date = a.getDate();
                        oItem.content = a.getContent();
                        oData.add(oItem);
                    }
                    listView = (ListView)findViewById(R.id.listView1);
                    MyAdapter oAdapter = new MyAdapter((ArrayList<MateData>) oData);
                    listView.setAdapter(oAdapter);

                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                        @Override
                        public void onItemClick(AdapterView parent, View v, int position, long id){
                            Intent intent = new Intent(getApplicationContext(), MateViewActivity.class);
                            intent.putExtra("TITLE_EXTRA", oData.get(position).title);
                            intent.putExtra("NICKNAME_EXTRA2", oData.get(position).nickname);
                            intent.putExtra("DATE_EXTRA", oData.get(position).date);
                            intent.putExtra("CONTENT_EXTRA", oData.get(position).content);
                            startActivity(intent);
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<MateResponse> call, Throwable t) {
                Toast.makeText(HouseActivity.this, "에러 발생", Toast.LENGTH_SHORT).show();
                Log.e("에러 발생", t.getMessage());
                showProgress(false);
            }
        });
    }

    private void showProgress(boolean show) {
        mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    public void write(View v){
        Intent intent = new Intent(this, MateWriteActivity.class);
        intent.putExtra(NICKNAME_EXTRA, getIntent().getStringExtra("NICKNAME_EXTRA"));
        startActivity(intent);
    }


}
