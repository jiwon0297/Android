package com.example.myapplication.mate;
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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

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


public class ContestActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    private String cate = "공모전";
    private ServiceApi service;
    private ProgressBar mProgressView;
    private List<String> list;          // 데이터를 넣은 리스트변수
    private ListView listView;          // 검색을 보여줄 리스트변수
    private EditText editSearch;        // 검색어를 입력할 Input 창
    private MyAdapter adapter;      // 리스트뷰에 연결할 아답터
    private ArrayList<String> arraylist;
    SwipeRefreshLayout mSwipeRefreshLayout;
    private final String NICKNAME_EXTRA = "NICKNAME_EXTRA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contest);

        editSearch = (EditText) findViewById(R.id.search);
        listView = (ListView) findViewById(R.id.listView);
        // 리스트를 생성한다.
        list = new ArrayList<String>();

        // 리스트의 모든 데이터를 arraylist에 복사한다.// list 복사본을 만든다.
        arraylist = new ArrayList<String>();
        arraylist.addAll(list);
        

        // 리스트뷰에 아답터를 연결한다.
        listView.setAdapter(adapter);
        // input창에 검색어를 입력시 "addTextChangedListener" 이벤트 리스너를 정의한다.
        editSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                // input창에 문자를 입력할때마다 호출된다.
                // search 메소드를 호출한다.
                String text = editSearch.getText().toString();
                search(text);
            }
        });

        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefresh);
        mSwipeRefreshLayout.setOnRefreshListener(this);

        mProgressView = (ProgressBar) findViewById(R.id.progressBar);
        service = RetrofitClient.getClient().create(ServiceApi.class);
        attemptList();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavi);
        bottomNavigationView.setOnNavigationItemSelectedListener(new ContestActivity.ItemSelectedListener());

        ImageButton backbutton = (ImageButton) findViewById(R.id.imageButton);
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ContestActivity.this, MateActivity.class);
                startActivity(intent);
            }
        });
    }

    // 검색을 수행하는 메소드
    public void search(String charText) {

        // 문자 입력시마다 리스트를 지우고 새로 뿌려준다.
        list.clear();

        // 문자 입력이 없을때는 모든 데이터를 보여준다.
        if (charText.length() == 0) {
            list.addAll(arraylist);
        }
        // 문자 입력을 할때..
        else
        {
            // 리스트의 모든 데이터를 검색한다.
            for(int i = 0;i < arraylist.size(); i++)
            {
                // arraylist의 모든 데이터에 입력받은 단어(charText)가 포함되어 있으면 true를 반환한다.
                if (arraylist.get(i).toLowerCase().contains(charText))
                {
                    // 검색된 데이터를 리스트에 추가한다.
                    list.add(arraylist.get(i));
                }
            }
        }
        // 리스트 데이터가 변경되었으므로 아답터를 갱신하여 검색된 데이터를 화면에 보여준다.
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
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
                Toast.makeText(ContestActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                showProgress(false);

                if (result.getCode() == 200) {
                    MateResponse sample = result;
                    for (MateResponse a :sample.getResult() ){
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
                    listView = (ListView)findViewById(R.id.listView);
                    MyAdapter oAdapter = new MyAdapter((ArrayList<MateData>) oData);
                    listView.setAdapter(oAdapter);

                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                        @Override
                        public void onItemClick(AdapterView parent, View v, int position, long id){
                            Intent intent = new Intent(getApplicationContext(), MateViewActivity.class);
                            intent.putExtra("CATE_EXTRA", oData.get(position).cate);
                            intent.putExtra("NUMBER_EXTRA", oData.get(position).number);
                            intent.putExtra("TITLE_EXTRA", oData.get(position).title);
                            intent.putExtra("NICKNAME_EXTRA2", oData.get(position).nickname);
                            intent.putExtra("DATE_EXTRA", oData.get(position).date);
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
                Toast.makeText(ContestActivity.this, "에러 발생", Toast.LENGTH_SHORT).show();
                Log.e("에러 발생", t.getMessage());
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
                    Intent intent = new Intent(ContestActivity.this, HomeActivity.class);
                    intent.putExtra(NICKNAME_EXTRA, getIntent().getStringExtra("NICKNAME_EXTRA"));
                    startActivity(intent);
                    break;
                case R.id.mail:
                    Intent intent2 = new Intent(ContestActivity.this, MailActivity.class);
                    intent2.putExtra(NICKNAME_EXTRA, getIntent().getStringExtra("NICKNAME_EXTRA"));
                    startActivity(intent2);
                    break;
                case R.id.mypage:
                    Intent intent3 = new Intent(ContestActivity.this, MypageActivity.class);
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
        Intent intent = new Intent(this, MateWriteActivity.class);
        intent.putExtra(NICKNAME_EXTRA, getIntent().getStringExtra("NICKNAME_EXTRA"));
        startActivity(intent);
    }



}
