package com.example.myapplication.lost;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.myapplication.Join.JoinData;
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

public class FindActivity extends AppCompatActivity {
    private String type = "find";
    private ServiceApi service;
    private ProgressBar mProgressView;
    private ListView listView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find);
        mProgressView = (ProgressBar) findViewById(R.id.loading);

        service = RetrofitClient.getClient().create(ServiceApi.class);

        attemptList();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavi);
        bottomNavigationView.setOnNavigationItemSelectedListener(new FindActivity.ItemSelectedListener());

    }

    private void attemptList() {
        startList(new LostData(type));
    }

    private void startList(LostData lostData) {
        ArrayList<LostData> oData = new ArrayList<>();
        service.lostList(lostData).enqueue(new Callback<LostResponse>(){
            @Override
            public void onResponse(Call<LostResponse> call, Response<LostResponse> response) {
                LostResponse result = response.body();
                Toast.makeText(FindActivity.this, result.getMessage(), Toast.LENGTH_SHORT).show();
                showProgress(false);

                if (result.getCode() == 200) {
                    List<LostResponse> sample = (List<LostResponse>) result;
                    for (LostResponse a :sample ){
                        LostData oItem = new LostData();
                        oItem.campus = "[" + a.getCampus() + "]";
                        oItem.title = a.getTitle();
                        oItem.id = a.getId();
                        oData.add(oItem);
                    }
                    listView = (ListView)findViewById(R.id.listView);
                    ListAdapter oAdapter = new ListAdapter(oData);
                    listView.setAdapter(oAdapter);

                    finish();
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
                    startActivity(intent);
                    break;
                case R.id.mail:
                    Intent intent2 = new Intent(FindActivity.this, MailActivity.class);
                    startActivity(intent2);
                    break;
                case R.id.mypage:
                    Intent intent3 = new Intent(FindActivity.this, MypageActivity.class);
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
