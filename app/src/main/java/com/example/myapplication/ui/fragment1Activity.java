package com.example.myapplication.ui;
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
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.mate.MateData;
import com.example.myapplication.mate.MateResponse;
import com.example.myapplication.mate.MateViewActivity;
import com.example.myapplication.mate.MyAdapter;
import com.example.myapplication.network.RetrofitClient;
import com.example.myapplication.network.ServiceApi;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class fragment1Activity extends AppCompatActivity{
    private ServiceApi service;
    private ProgressBar mProgressView;
    private ListView listView = null;
    private final String NICKNAME_EXTRA = "NICKNAME_EXTRA";
    private TextView nicknameText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_1_layout);
        nicknameText.setText(getIntent().getStringExtra("NICKNAME_EXTRA"));

        mProgressView = (ProgressBar) findViewById(R.id.progressBar);
        service = RetrofitClient.getClient().create(ServiceApi.class);
        attemptList();

    }


    private void attemptList() {
        String nickname = nicknameText.getText().toString();
        boolean cancel = false;
        View focusView = null;

        if (nickname.isEmpty()) {
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            startList(new MyMateData(nickname));
            showProgress(true);
        }
    }

    private void startList(MyMateData data) {
        List<MyMateData> oData = new ArrayList<>();

        service.userMatelist(data).enqueue(new Callback<MyMateResponse>() {
            @Override
            public void onResponse(Call<MyMateResponse> call, Response<MyMateResponse> response) {
                MyMateResponse result = response.body();
                Toast.makeText(fragment1Activity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                showProgress(false);

                if (result.getCode() == 200) {
                    MyMateResponse sample = result;
                    for (MyMateResponse a :sample.getResult() ){
                        MyMateData oItem = new MyMateData();
                        oItem.campus = "[" + a.getCampus() + "]";
                        oItem.title = a.getTitle();
                        oItem.nickname = a.getNickname();
                        oItem.date = a.getDate();
                        oItem.content = a.getContent();
                        oItem.number = a.getNumber();
                        oData.add(oItem);
                    }
                    listView = (ListView)findViewById(R.id.listView1);
                    MyMateAdapter oAdapter = new MyMateAdapter((ArrayList<MyMateData>) oData);
                    listView.setAdapter(oAdapter);

                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                        @Override
                        public void onItemClick(AdapterView parent, View v, int position, long id){
                            Intent intent = new Intent(getApplicationContext(), MateViewActivity.class);
                            intent.putExtra("NUMBER_EXTRA", oData.get(position).number);
                            intent.putExtra("TITLE_EXTRA", oData.get(position).title);
                            intent.putExtra("NICKNAME_EXTRA2", oData.get(position).nickname);
                            intent.putExtra("DATE_EXTRA", oData.get(position).date);
                            intent.putExtra("CONTENT_EXTRA", oData.get(position).content);
                            intent.putExtra(NICKNAME_EXTRA, getIntent().getStringExtra("NICKNAME_EXTRA"));
                            startActivity(intent);
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<MyMateResponse> call, Throwable t) {
                Toast.makeText(fragment1Activity.this, "에러 발생", Toast.LENGTH_SHORT).show();
                Log.e("에러 발생", t.getMessage());
                showProgress(false);
            }
        });
    }

    private void showProgress(boolean show) {
        mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
    }

}


