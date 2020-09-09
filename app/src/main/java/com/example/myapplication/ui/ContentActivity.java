package com.example.myapplication.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.myapplication.R;
import com.example.myapplication.mate.MateViewActivity;

import java.util.ArrayList;

public class ContentActivity extends AppCompatActivity {

    ArrayList<SampleData> DataList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);

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

        DataList.add(new SampleData("기숙사 같이 사실 분 구해요.", "배정원","2020-05-27 오전 11:55","새내기인데 혹시 안좋은 룸메 걸릴까봐 걱정이예요. 알람소리가 크거나 더럽지만 않으면 좋을 것 같아요"));
        DataList.add(new SampleData("여기 근처에 자리 남은 쉐어하우스 있나요?", "이승미","2020-05-26 오전 11:55","쉐어하우스 들어가고 싶은데 혹시 2인실인데 혼자이신 분 계신가요?"));
        DataList.add(new SampleData("투룸 하우스메이트 구합니다.", "김진하","2020-05-27 오후 02:55","월세가 너무 비싸서 하우스메이트 구해요. 투룸으로 할 예정이예요"));
    }

}

