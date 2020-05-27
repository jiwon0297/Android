package com.example.myapplication.mate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.myapplication.R;


public class MateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mate);
    }

    public void alone(View v) {
        Toast.makeText(this,"혼밥 메이트 구하기 화면으로 이동합니다.",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, AloneActivity.class);
        startActivity(intent);
    }
    public void study(View v) {
        Toast.makeText(this,"스터디 메이트 구하기 화면으로 이동합니다.",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, StudyActivity.class);
        startActivity(intent);
    }
    public void contest(View v){
        Toast.makeText(this,"공모전 메이트 구하기 화면으로 이동합니다.",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, ContestActivity.class);
        startActivity(intent);
    }
    public void house(View v){
        Toast.makeText(this,"하우스 메이트 구하기 화면으로 이동합니다.",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, HouseActivity.class);
        startActivity(intent);
    }
}
