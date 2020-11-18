package com.example.myapplication.restaurant.cheonan;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.restaurant.RestaurantwhereActivity;
import com.example.myapplication.restaurant.cheonan.cafe.CafeActivity2;
import com.example.myapplication.restaurant.cheonan.chinese.ChineseActivity2;
import com.example.myapplication.restaurant.cheonan.dessert.DessertActivity2;
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

        ViewGroup cafe = (ViewGroup) findViewById(R.id.cafe2);
        cafe.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                Intent intent = new Intent(RestaurantActivity2.this, CafeActivity2.class);
                startActivity(intent);
            }
        });

        ViewGroup korean = (ViewGroup) findViewById(R.id.korean2);
        korean.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                Intent intent = new Intent(RestaurantActivity2.this, KoreanActivity2.class);
                startActivity(intent);
            }
        });
        ViewGroup japanese = (ViewGroup) findViewById(R.id.japanese2);
        japanese.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                Intent intent = new Intent(RestaurantActivity2.this, JapaneseActivity2.class);
                startActivity(intent);
            }
        });
        ViewGroup chinese = (ViewGroup) findViewById(R.id.chinese2);
        chinese.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                Intent intent = new Intent(RestaurantActivity2.this, ChineseActivity2.class);
                startActivity(intent);
            }
        });
        ViewGroup western = (ViewGroup) findViewById(R.id.western2);
        western.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                Intent intent = new Intent(RestaurantActivity2.this, WesternActivity2.class);
                startActivity(intent);
            }
        });

        ViewGroup dessert = (ViewGroup) findViewById(R.id.dessert2);
        dessert.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                Intent intent = new Intent(RestaurantActivity2.this, DessertActivity2.class);
                startActivity(intent);
            }
        });
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

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        Intent intent = new Intent(RestaurantActivity2.this, RestaurantwhereActivity.class);
        intent.putExtra("NICKNAME_EXTRA", getIntent().getStringExtra("NICKNAME_EXTRA"));
        startActivity(intent);
    }

}