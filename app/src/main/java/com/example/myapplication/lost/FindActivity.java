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
    private ServiceApi service;
    private ListView listView = null;
    private Call<LostResponse> mLostList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find);

        service = RetrofitClient.getClient().create(ServiceApi.class);

        startList();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavi);
        bottomNavigationView.setOnNavigationItemSelectedListener(new FindActivity.ItemSelectedListener());

    }


    private void startList() {
        Call<LostResponse> call = service.lostList("find");

        call.enqueue(new Callback<LostResponse>() {
            @Override
            public void onResponse(Call<LostResponse> call, Response<LostResponse> response) {
                LostResponse LData = response.body();
                ArrayList<LostData> oData = new ArrayList<>();
                for (LostResponse lost : LData.getResult()){
                    LostData oItem = new LostData();
                    oItem.campus = "[" + lost.getCampus() +  "]";
                    oItem.title = lost.getTitle();
                    oItem.id = lost.getId();
                    oData.add(oItem);
                }
                listView = (ListView)findViewById(R.id.listView);
                ListAdapter oAdapter = new ListAdapter(oData);
                listView.setAdapter(oAdapter);
            }

            @Override
            public void onFailure(Call<LostResponse> call, Throwable t) {
                Toast.makeText(FindActivity.this, "분실물 에러 발생", Toast.LENGTH_SHORT).show();
                Log.e("분실물 에러 발생", t.getMessage());
                t.printStackTrace();
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

}
