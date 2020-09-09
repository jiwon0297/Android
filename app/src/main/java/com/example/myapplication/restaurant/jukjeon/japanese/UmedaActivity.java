package com.example.myapplication.restaurant.jukjeon.japanese;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.ui.HomeActivity;
import com.example.myapplication.ui.MailActivity;
import com.example.myapplication.ui.MypageActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class UmedaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_umeda);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavi);
        bottomNavigationView.setOnNavigationItemSelectedListener(new UmedaActivity.ItemSelectedListener());
    }

    class ItemSelectedListener implements BottomNavigationView.OnNavigationItemSelectedListener{
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

            switch(menuItem.getItemId())
            {
                case R.id.home:
                    Intent intent = new Intent(UmedaActivity.this, HomeActivity.class);
                    startActivity(intent);
                    break;
                case R.id.mail:
                    Intent intent2 = new Intent(UmedaActivity.this, MailActivity.class);
                    startActivity(intent2);
                    break;
                case R.id.mypage:
                    Intent intent3 = new Intent(UmedaActivity.this, MypageActivity.class);
                    startActivity(intent3);
                    break;
            }
            return true;
        }
    }
}