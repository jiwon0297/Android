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
import com.example.myapplication.ui.frag_home;
import com.example.myapplication.ui.frag_mail;
import com.example.myapplication.ui.frag_mypage;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MateActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private FragmentManager fm;
    private FragmentTransaction ft;
    private com.example.myapplication.ui.frag_home frag_home;
    private com.example.myapplication.ui.frag_mail frag_mail;
    private com.example.myapplication.ui.frag_mypage frag_mypage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mate);

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
