package com.example.myapplication.mate;

import androidx.appcompat.app.AppCompatActivity;

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

import com.example.myapplication.R;
import com.example.myapplication.network.RetrofitClient;
import com.example.myapplication.network.ServiceApi;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MateSearchActivity extends AppCompatActivity {

    EditText searchtitle;
    private ServiceApi service;
    private ProgressBar mProgressView;
    private ListView listView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mate_search);

        mProgressView = (ProgressBar) findViewById(R.id.progressBar);
        service = RetrofitClient.getClient().create(ServiceApi.class);

        searchtitle = (EditText) findViewById(R.id.searchtitle);
        searchtitle.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                switch (i){
                    case KeyEvent.KEYCODE_ENTER:
                        //event
                        attemptList();
                }
                return true;
            }
        });

        ImageButton searchbtn = (ImageButton) findViewById(R.id.search);
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
        String cate = getIntent().getStringExtra("CATE");

        if (title.isEmpty()) {
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            startList(new MateSearchData(cate,title));
            showProgress(true);
        }
    }

    private void startList(MateSearchData data) {
        List<MateSearchData> oData = new ArrayList<>();

        service.matesearch(data).enqueue(new Callback<MateSearchResponse>() {
            @Override
            public void onResponse(Call<MateSearchResponse> call, Response<MateSearchResponse> response) {
                MateSearchResponse result = response.body();
                Toast.makeText(MateSearchActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                showProgress(false);

                if (result.getCode() == 200) {
                    MateSearchResponse sample = result;
                    for (MateSearchResponse a : sample.getResult()) {
                        MateSearchData oItem = new MateSearchData();
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
                    MateSearchAdapter oAdapter = new MateSearchAdapter((ArrayList<MateSearchData>) oData);
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
                            intent.putExtra("NICKNAME_EXTRA", getIntent().getStringExtra("NICKNAME_EXTRA"));
                            startActivity(intent);
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<MateSearchResponse> call, Throwable t) {
                Toast.makeText(MateSearchActivity.this, "에러 발생", Toast.LENGTH_SHORT).show();
                Log.e("에러 발생", t.getMessage());
                showProgress(false);
            }
        });
    }

    private void showProgress(boolean show) {
        mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
    }
}