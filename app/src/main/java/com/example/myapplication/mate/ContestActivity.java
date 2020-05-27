package com.example.myapplication.mate;


import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.myapplication.R;

import java.util.ArrayList;



public class ContestActivity extends AppCompatActivity {
    ArrayList<SampleData> DataList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contest);

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
    }
    public void InitializeDataList()
    {
        DataList = new ArrayList<SampleData>();

        DataList.add(new SampleData("한글공모전 모집원 1명 구합니다.", "배정원","2020-05-27 오전 11:55","한글탐정이라는 게임을 구상중이예요. 한명이 부족해서 구해봅니다."));
        DataList.add(new SampleData("공모전 같이 나가실 분?", "이승미","2020-05-26 오전 11:55","취업은 해야겠고.. 뭐라도 하고싶은데 같이 공모전 하실 분 ㅠㅠ"));
        DataList.add(new SampleData("공모전 메이트 구합니다.", "김진하","2020-05-27 오후 02:55","공모전 나갈건데 혼자 나가기에 조금 그래서ㅠㅠ 쪽지 주시면 공모전내용 말씀드릴게요"));
    }

    public void write(View v){
        Intent intent = new Intent(this, MateWriteActivity.class);
        startActivity(intent);
    }


}
