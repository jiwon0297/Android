package com.example.myapplication.mate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
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

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AloneActivity extends AppCompatActivity {
    private String cate = "혼밥";
    private ServiceApi service;
    private ProgressBar mProgressView;
    private ListView listView = null;

    private static final String NUMBER_EXTRA = "NUMBER_EXTRA";
    private static final String TITLE_EXTRA = "TITLE_EXTRA";
    private static final String NICKNAME_EXTRA2 = "NICKNAME_EXTRA2";
    private final String NICKNAME_EXTRA = "NICKNAME_EXTRA";
    private static final String CONTENT_EXTRA = "CONTENT_EXTRA";
    private static final String DATE_EXTRA = "DATE_EXTRA";
    private static final String CATE_EXTRA = "CATE_EXTRA";
    private static final String CAMPUS_EXTRA = "CAMPUS_EXTRA";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alone);

        mProgressView = (ProgressBar) findViewById(R.id.progressBar);
        service = RetrofitClient.getClient().create(ServiceApi.class);
        attemptList();

        /* ListView listView = (ListView)findViewById(R.id.listView1);
        final MyAdapter myAdapter = new MyAdapter(this,matelist);

        listView.setAdapter(myAdapter);

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
        bottomNavigationView.setOnNavigationItemSelectedListener(new AloneActivity.ItemSelectedListener());

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
        List<MateWriteData> oData = new ArrayList<>();
        service.matelist(data).enqueue(new Callback<MateResponse>() {
            @Override
            public void onResponse(Call<MateResponse> call, Response<MateResponse> response) {
                MateResponse result = response.body();
                Toast.makeText(AloneActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                showProgress(false);

                if(response.body().getCode()==200 && response.body() != null) {
                    List<MateWriteData> sample = (List<MateWriteData>) result;
                   /* for (MateWriteData a :sample ){
                        MateWriteData oItem = new MateWriteData();
                        oItem.campus = "[" + a.getCampus() + "]";
                        oItem.title = a.getTitle();
                        oItem.nickname = a.getNickname();
                        oData.add(oItem);
                    }

                    */
                    listView = (ListView)findViewById(R.id.listView);
                    MyAdapter oAdapter = new MyAdapter((ArrayList<MateWriteData>) oData);
                    listView.setAdapter(oAdapter);
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
