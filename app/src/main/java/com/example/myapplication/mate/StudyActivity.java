package com.example.myapplication.mate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.myapplication.R;
import com.example.myapplication.ui.frag_home;
import com.example.myapplication.ui.frag_mail;
import com.example.myapplication.ui.frag_mypage;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;



public class StudyActivity extends AppCompatActivity {
    ArrayList<SampleData> DataList;
    private BottomNavigationView bottomNavigationView;
    private FragmentManager fm;
    private FragmentTransaction ft;
    private com.example.myapplication.ui.frag_home frag_home;
    private com.example.myapplication.ui.frag_mail frag_mail;
    private com.example.myapplication.ui.frag_mypage frag_mypage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study);

        this.InitializeDataList();

        ListView listView = (ListView)findViewById(R.id.listView1);
        final MyAdapter myAdapter = new MyAdapter(this,DataList);

        listView.setAdapter(myAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id){
                Intent intent = new Intent(getApplicationContext(), MateViewActivity.class);
                intent.putExtra("title", DataList.get(position).getTitle());
                intent.putExtra("writer", DataList.get(position).getWriter());
                intent.putExtra("date", DataList.get(position).getDate());
                intent.putExtra("content", DataList.get(position).getContent());
                startActivity(intent);
            }
        });

        bottomNavigationView = findViewById(R.id.bottomNavi);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener()
        {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem)
            {
                switch (menuItem.getItemId())
                {
                    case R.id.mail:
                        setFrag(0);
                        break;
                    case R.id.home:
                        setFrag(1);
                        break;
                    case R.id.mypage:
                        setFrag(2);
                        break;
                }
                return true;
            }
        });

        frag_mail=new frag_mail();
        frag_home=new frag_home();
        frag_mypage=new frag_mypage();
    }
    public void InitializeDataList()
    {
        DataList = new ArrayList<SampleData>();

        DataList.add(new SampleData("자료구조 과제 같이 하실 분", "배정원","2020-05-27 오전 11:55","나연묵 교수님 자료구조 너무 어려운데 같이 공부하실 분 계신가요? 도서관에서 하고싶어요."));
        DataList.add(new SampleData("코딩테스트 스터디 구해요", "이승미","2020-05-26 오전 11:55","취업관련하여 코딩테스트 같이 공부하실 분 구해요 인원은 4명정도로 생각하고 있어요."));
        DataList.add(new SampleData("토익 스터디 모집합니다.", "김진하","2020-05-27 오후 02:55","현재까지 4명 모였고, 8명까지 받을 수 있을 것 같아요. 장소는 학교에서 할 예정입니다."));
    }

    public void write(View v){
        Intent intent = new Intent(this, MateWriteActivity.class);
        startActivity(intent);
    }

    private void setFrag(int n)
    {
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        switch(n) {
            case 0:
                ft.replace(R.id.Main_Frame, frag_mail);
                ft.commit();
                break;
            case 1:
                ft.replace(R.id.Main_Frame, frag_home);
                ft.commit();
                break;
            case 2:
                ft.replace(R.id.Main_Frame, frag_mypage);
                ft.commit();
                break;
        }
    }

}
