package com.example.myapplication.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.mate.MateViewActivity;
import com.example.myapplication.mate.MyAdapter;
import com.example.myapplication.mate.SampleData;

import java.util.ArrayList;

public class MypageActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage);

    }
    public void content(View v) {
        Toast.makeText(this,"내 목록 화면으로 이동합니다.",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, ContentActivity.class);
        startActivity(intent);
    }

    public void logout(View v) {
        Toast.makeText(this,"로그아웃 되어 홈화면으로 이동합니다.",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void user(View v) {
        Toast.makeText(this,"정보 수정 화면으로 이동합니다.",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, UserActivity.class);
        startActivity(intent);
    }

}
