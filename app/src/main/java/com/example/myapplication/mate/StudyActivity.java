package com.example.myapplication.mate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.myapplication.Main2Activity;
import com.example.myapplication.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class StudyActivity extends AppCompatActivity {
    ArrayList<HashMap<String, String>> ContentList;
    ListView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study);

        list=(ListView) findViewById(R.id.listView);
        ContentList = new ArrayList<HashMap<String, String>>();

        showList();
    }
    public void view(View v){
        Intent intent = new Intent(this, MateViewActivity.class);
        startActivity(intent);
    }

    protected void showList(){
        try{
                String title = "같이 역전우동 드실 분 구해요";
                String writer = "박지원";

                HashMap<String, String> contents = new HashMap<String, String>();

                contents.put("title", title);
                contents.put("writer", writer);

                boolean add = ContentList.add(contents);

            ListAdapter adapter = new SimpleAdapter(
                    StudyActivity.this, ContentList, R.layout.matelist_item,
                    new String[]{"title","writer"},
                    new int[]{R.id.title, R.id.writer}
            );

            list.setAdapter(adapter);
        } catch (JSONException e){
            e.printStackTrace();
        } catch (NullPointerException e){
            e.printStackTrace();
        }
    }

}
