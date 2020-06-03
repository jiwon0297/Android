package com.example.myapplication.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.myapplication.lost.LostActivity;
import com.example.myapplication.mate.MateActivity;
import com.example.myapplication.R;
import com.example.myapplication.restaurant.RestaurantActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class HomeActivity extends AppCompatActivity {
    private FragmentManager fragmentManager = getSupportFragmentManager();
    private FragHome fragHome = new FragHome();
    private FragMail fragMail = new FragMail();
    private FragMypage fragMypage = new FragMypage();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.Main_Frame, fragHome).commitAllowingStateLoss();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavi);
        bottomNavigationView.setOnNavigationItemSelectedListener(new ItemSelectedListener());

    }

    class ItemSelectedListener implements BottomNavigationView.OnNavigationItemSelectedListener{
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();

            switch(menuItem.getItemId())
            {
                case R.id.home:
                    transaction.replace(R.id.Main_Frame, fragHome).commitAllowingStateLoss();
                    break;
                case R.id.mail:
                    transaction.replace(R.id.Main_Frame, fragMail).commitAllowingStateLoss();
                    break;
                case R.id.mypage:
                    transaction.replace(R.id.Main_Frame, fragMypage).commitAllowingStateLoss();
                    break;
            }
            return true;
        }
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
    public void mate(View v) {
        Toast.makeText(this,"메이트 화면으로 이동합니다.",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, MateActivity.class);
        startActivity(intent);
    }
    public void lost(View v) {
        Toast.makeText(this,"분실물 화면으로 이동합니다.",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, LostActivity.class);
        startActivity(intent);
    }
    public void restaurant(View v){
        Toast.makeText(this,"식당 화면으로 이동합니다.",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, RestaurantActivity.class);
        startActivity(intent);

    }

}
