package com.example.myapplication.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.myapplication.login.LoginActivity;
import com.example.myapplication.lost.LostActivity;
import com.example.myapplication.mate.MateActivity;
import com.example.myapplication.R;
import com.example.myapplication.restaurant.jukjeon.RestaurantActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class HomeActivity extends AppCompatActivity {
    private final String NICKNAME_EXTRA = "NICKNAME_EXTRA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavi);
        bottomNavigationView.setOnNavigationItemSelectedListener(new ItemSelectedListener());

    }

    class ItemSelectedListener implements BottomNavigationView.OnNavigationItemSelectedListener{
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

            switch(menuItem.getItemId())
            {
                case R.id.home:
                    Intent intent = new Intent(HomeActivity.this, HomeActivity.class);
                    intent.putExtra(NICKNAME_EXTRA, getIntent().getStringExtra("NICKNAME_EXTRA"));
                    startActivity(intent);
                    break;
                case R.id.mail:
                    Intent intent2 = new Intent(HomeActivity.this, MailActivity.class);
                    intent2.putExtra(NICKNAME_EXTRA, getIntent().getStringExtra("NICKNAME_EXTRA"));
                    startActivity(intent2);
                    break;
                case R.id.mypage:
                    Intent intent3 = new Intent(HomeActivity.this, MypageActivity.class);
                    intent3.putExtra(NICKNAME_EXTRA, getIntent().getStringExtra("NICKNAME_EXTRA"));
                    startActivity(intent3);
                    break;
            }
            return true;
        }
    }

    public void mate(View v) {
        Toast.makeText(this,"메이트 화면으로 이동합니다.",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, MateActivity.class);
        intent.putExtra(NICKNAME_EXTRA, getIntent().getStringExtra("NICKNAME_EXTRA"));
        startActivity(intent);
    }
    public void lost(View v) {
        Toast.makeText(this,"분실물 화면으로 이동합니다.",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, LostActivity.class);
        intent.putExtra(NICKNAME_EXTRA, getIntent().getStringExtra("NICKNAME_EXTRA"));
        startActivity(intent);
    }
    public void restaurant(View v){
        Toast.makeText(this,"식당 화면으로 이동합니다.",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, RestaurantActivity.class);
        intent.putExtra(NICKNAME_EXTRA, getIntent().getStringExtra("NICKNAME_EXTRA"));
        startActivity(intent);

    }

}
