package com.example.myapplication.mypage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.R;
import com.example.myapplication.ui.MainActivity;
import com.example.myapplication.ui.frag_home;
import com.example.myapplication.ui.frag_mail;
import com.example.myapplication.ui.frag_mypage;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ContentActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private FragmentManager fm;
    private FragmentTransaction ft;
    private com.example.myapplication.ui.frag_home frag_home;
    private com.example.myapplication.ui.frag_mail frag_mail;
    private com.example.myapplication.ui.frag_mypage frag_mypage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);

        Button contentButton = (Button) findViewById(R.id.ContentManage);
        contentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ContentActivity.this, MypageActivity.class);
                ContentActivity.this.startActivity(intent);
            }
        });

        Button userButton = (Button) findViewById(R.id.UserManage);
        userButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ContentActivity.this, UserActivity.class);
                ContentActivity.this.startActivity(intent);
            }
        });

        Button logoutButton = (Button) findViewById(R.id.Logout);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ContentActivity.this, MainActivity.class);
                ContentActivity.this.startActivity(intent);
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
        setFrag(2); // 첫 프래그먼트 화면 지정

    }

    private void setFrag(int n) {
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        switch (n) {
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

