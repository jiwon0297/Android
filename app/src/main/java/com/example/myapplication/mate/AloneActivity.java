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



public class AloneActivity extends AppCompatActivity {
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
        setContentView(R.layout.activity_alone);

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

        DataList.add(new SampleData("역전우동에서 밥 드실분", "배정원","2020-05-27 오전 11:55","오늘따라 카레가 너무 먹고싶은데 혼자 먹기는 조금 부끄러워서ㅠㅠ"));
        DataList.add(new SampleData("배터지는 생돈가스 양 너무 많은데 같이 가실 분 있나요ㅠㅠ", "이승미","2020-05-26 오전 11:55","혼자 먹기엔 양이 너무 많아요. 오늘 저녁에 먹을 예정입니다."));
        DataList.add(new SampleData("오늘 점심은 혼자 먹기 싫은데 같이 드실 분", "김진하","2020-05-27 오후 02:55","메뉴는 아무거나 다 좋아요! 너무 비싸지만 않았으면 좋겠어요. 쪽지 주세요."));
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
