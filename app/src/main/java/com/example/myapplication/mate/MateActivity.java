package com.example.myapplication.mate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.ui.HomeActivity;
import com.example.myapplication.ui.MailActivity;
import com.example.myapplication.ui.MainActivity;
import com.example.myapplication.ui.MypageActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mate);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavi);
        bottomNavigationView.setOnNavigationItemSelectedListener(new MateActivity.ItemSelectedListener());
    }

    class ItemSelectedListener implements BottomNavigationView.OnNavigationItemSelectedListener{
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

            switch(menuItem.getItemId())
            {
                case R.id.home:
                    Intent intent = new Intent(MateActivity.this, HomeActivity.class);
                    startActivity(intent);
                    break;
                case R.id.mail:
                    Intent intent2 = new Intent(MateActivity.this, MailActivity.class);
                    startActivity(intent2);
                    break;
                case R.id.mypage:
                    Intent intent3 = new Intent(MateActivity.this, MypageActivity.class);
                    startActivity(intent3);
                    break;
            }
            return true;
        }
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
