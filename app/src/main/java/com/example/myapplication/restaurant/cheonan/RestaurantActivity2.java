package com.example.myapplication.restaurant.cheonan;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.restaurant.cheonan.cafe.CafeActivity2;
import com.example.myapplication.restaurant.cheonan.chinese.ChineseActivity2;
import com.example.myapplication.restaurant.cheonan.japanese.JapaneseActivity2;
import com.example.myapplication.restaurant.cheonan.korean.KoreanActivity2;
import com.example.myapplication.restaurant.cheonan.western.WesternActivity2;
import com.example.myapplication.ui.HomeActivity;
import com.example.myapplication.ui.MailActivity;
import com.example.myapplication.ui.MypageActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class RestaurantActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant2);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavi);
        bottomNavigationView.setOnNavigationItemSelectedListener(new RestaurantActivity2.ItemSelectedListener());
    }

    class ItemSelectedListener implements BottomNavigationView.OnNavigationItemSelectedListener{
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

            switch(menuItem.getItemId())
            {
                case R.id.home:
                    Intent intent = new Intent(RestaurantActivity2.this, HomeActivity.class);
                    startActivity(intent);
                    break;
                case R.id.mail:
                    Intent intent2 = new Intent(RestaurantActivity2.this, MailActivity.class);
                    startActivity(intent2);
                    break;
                case R.id.mypage:
                    Intent intent3 = new Intent(RestaurantActivity2.this, MypageActivity.class);
                    startActivity(intent3);
                    break;
            }
            return true;
        }
    }

    public void cafe2(View view) {
        Toast.makeText(this,"카페 목록",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, CafeActivity2.class);
        startActivity(intent);
    }

    public void Korean2(View view) {
        Toast.makeText(this,"한식 목록",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, KoreanActivity2.class);
        startActivity(intent);
    }

    public void Chinese2(View view) {
        Toast.makeText(this,"중식 목록",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, ChineseActivity2.class);
        startActivity(intent);
    }

    public void Japanese2(View view) {
        Toast.makeText(this,"일식 목록",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, JapaneseActivity2.class);
        startActivity(intent);
    }

    public void Western2(View view) {
        Toast.makeText(this,"양식 목록",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, WesternActivity2.class);
        startActivity(intent);
    }
}